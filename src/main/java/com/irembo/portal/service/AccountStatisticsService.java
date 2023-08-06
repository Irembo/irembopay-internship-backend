package com.irembo.portal.service;

import org.springframework.stereotype.Service;

import com.irembo.portal.dto.BalanceProjection;
import com.irembo.portal.dto.CountProjection;
import com.irembo.portal.dto.PaymentAccountBalance;
import com.irembo.portal.dto.PaymentStatusProjection;
import com.irembo.portal.exception.ApiException;
import com.irembo.portal.repository.PaymentAccountRepository;
import com.irembo.portal.repository.PaymentInvoiceRepository;
import com.irembo.portal.repository.SettlementTransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

@Service
public class AccountStatisticsService {

    @Autowired
    private PaymentInvoiceRepository paymentInvoiceRepository;

    @Autowired
    private PaymentAccountRepository paymentAccountRepository;

    @Autowired
    private SettlementTransactionRepository settlementTransactionRepository;

    public List<BalanceProjection> getAccountBalance(UUID accountId) {
        try {
            if (accountId == null) {
                throw new IllegalArgumentException("'accountId' cannot be null");
            }

            List<BalanceProjection> settledInvoices = settlementTransactionRepository
                    .sumTransactionAmountByAccountIdAndDestinationAccountId(accountId);

            return settledInvoices;
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }
    }

    public Page<PaymentAccountBalance> getAccountBalanceForPaymentAccount(UUID accountId, UUID accountNumber,
            Pageable pageable) {
        try {
            if (accountId == null || accountNumber == null) {
                throw new IllegalArgumentException("'accountId' or 'accountNumber' cannot be null");
            }

            return paymentAccountRepository.getBalanceForPaymentAccount(accountId, accountNumber, pageable);
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }
    }

    public List<Map<String, Object>> getProjectedBalanceAfter7Days(UUID accountId) {
        try {
            if (accountId == null) {
                throw new IllegalArgumentException("'accountId' cannot be null");
            }
            List<BalanceProjection> currentBalance = getAccountBalance(accountId);

            // check settlement transcaction logs for latest payout date

            LocalDateTime lastPayoutDate = LocalDateTime.now().minusDays(356);
            LocalDateTime sevenDaysFromNow = LocalDateTime.now().plusDays(7);

            List<BalanceProjection> paidInvoices = paymentInvoiceRepository
                    .sumInvoiceAmountByAppAccountIdAndPaymentStatusAndPaymentMadeAtAfter(
                            accountId,
                            lastPayoutDate,
                            sevenDaysFromNow);

            List<Map<String, Object>> balanceArray = new ArrayList<>();

            for (BalanceProjection balance : currentBalance) {
                Map<String, Object> balanceMap = new HashMap<>();
                balanceMap.put("currency", balance.getCurrency());
                balanceMap.put("totalAmount", balance.getTotalAmount());
                balanceArray.add(balanceMap);
            }

            // print out balances for each currency
            for (BalanceProjection balance : paidInvoices) {
                // we check if currency already exists in balanceArray
                // if it does, we add the totalAmount to the existing balance
                // if it doesn't, we add the currency and totalAmount to the balanceArray

                boolean currencyExists = false;

                for (Map<String, Object> balanceMap : balanceArray) {
                    if (balanceMap.get("currency").equals(balance.getCurrency())) {
                        currencyExists = true;
                        BigDecimal currentTotalAmount = (BigDecimal) balanceMap.get("totalAmount");
                        BigDecimal newTotalAmount = currentTotalAmount.add(balance.getTotalAmount());
                        balanceMap.put("totalAmount", newTotalAmount);
                    }
                }

                if (!currencyExists) {
                    Map<String, Object> balanceMap = new HashMap<>();
                    balanceMap.put("currency", balance.getCurrency());
                    balanceMap.put("totalAmount", balance.getTotalAmount());
                    balanceArray.add(balanceMap);
                }
            }

            return balanceArray;
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }

    }

    public long getTotalPaidInvoicesLast7Days(UUID accountId) {
        try {
            if (accountId == null) {
                throw new IllegalArgumentException("'accountId' cannot be null");
            }
            LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(365 + 7);

            return paymentInvoiceRepository.countByAppAccountIdAndPaymentStatusAndPaymentMadeAtAfter(
                    accountId,
                    PaymentStatus.PAID,
                    sevenDaysAgo);
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }

    }

    public List<CountProjection> getTotalPaidInvoicesLast7DaysForPaymentAccount(UUID accountId, UUID accountNumber) {
        try {
            if (accountId == null || accountNumber == null) {
                throw new IllegalArgumentException("'accountId' or 'accountNumber' cannot be null");
            }
            LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(365 + 30);

            // filter merchantAccountId
            return paymentInvoiceRepository
                    .countByAppAccountIdAndPaymentStatusAndPaymentMadeAtAfterAndMerchantAccountId(
                            sevenDaysAgo, accountNumber);
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }

    }

    public List<CountProjection> getTotalValueOfTransactionsLast7Days(UUID accountNumber) {
        try {
            if (accountNumber == null) {
                throw new IllegalArgumentException("'accountNumber' cannot be null");
            }
            LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(365 + 7);

            return settlementTransactionRepository.sumTransactionAmountByAccountIdAndSettlementDateAfter(

                    sevenDaysAgo, accountNumber);
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }

    }

    public List<CountProjection> getTotalValueOfTransactionsLast30Days(UUID accountNumber) {
        try {
            if (accountNumber == null) {
                throw new IllegalArgumentException("'accountNumber' cannot be null");
            }
            LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(365 + 30);

            return settlementTransactionRepository.sumTransactionAmountByAccountIdAndSettlementDateAfter(

                    thirtyDaysAgo, accountNumber);
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }

    }

    // get total daily paid invoices for the past N cycle (1 week, 1 month, 1 year)
    public List<Map<String, Object>> getTotalDailyPaidInvoices(UUID accountId, int cycle) {
        try {
            if (accountId == null) {
                throw new IllegalArgumentException("'accountId' cannot be null");
            }
            LocalDateTime cycleAgo = LocalDateTime.now().minusDays(350 + cycle);
            LocalDateTime now = LocalDateTime.now().minusDays(350);
            long daysBetween = ChronoUnit.DAYS.between(cycleAgo, now);

            List<Map<String, Object>> dailyPaidInvoices = new ArrayList<>();

            for (int i = 0; i < daysBetween; i++) {
                LocalDateTime date = cycleAgo.plusDays(i);
                long totalPaidInvoices = paymentInvoiceRepository
                        .countByAppAccountIdAndPaymentStatusAndPaymentMadeAtAfter(
                                accountId,
                                PaymentStatus.PAID,
                                date);

                Map<String, Object> dailyPaidInvoicesMap = new HashMap<>();
                dailyPaidInvoicesMap.put("date", date);
                dailyPaidInvoicesMap.put("value", totalPaidInvoices);
                dailyPaidInvoices.add(dailyPaidInvoicesMap);
            }

            System.out.println(dailyPaidInvoices.size());

            return dailyPaidInvoices;
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }

    }

    // get a count of all invoices group by status. Using payment status field.
    // Returns a count for each status
    public List<PaymentStatusProjection> getTotalInvoicesByStatus(UUID accountId) {
        try {
            if (accountId == null) {
                throw new IllegalArgumentException("'accountId' cannot be null");
            }
            return paymentInvoiceRepository.countByAppAccountIdAndPaymentStatus(accountId);
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }

    }

    // get total daily settled settlement_transactions for the past N cycle (1 week,
    // 1 month, 1 year). where settlementStatus = "SETTLED"
    public List<Map<String, Object>> getTotalDailySettledTransactions(UUID accountId, int cycle) {
        try {
            if (accountId == null) {
                throw new IllegalArgumentException("'accountId' cannot be null");
            }
            LocalDateTime cycleAgo = LocalDateTime.now().minusDays(280 + cycle);
            LocalDateTime now = LocalDateTime.now().minusDays(280);
            long daysBetween = ChronoUnit.DAYS.between(cycleAgo, now);

            List<Map<String, Object>> dailySettledTransactions = new ArrayList<>();

            for (int i = 0; i < daysBetween; i++) {
                LocalDateTime date = cycleAgo.plusDays(i);
                BigDecimal totalSettledTransactions = settlementTransactionRepository
                        .countTransactionAmountByAccountIdAndSettlementDateAfterCycle(
                                accountId,
                                date);

                Map<String, Object> dailySettledTransactionsMap = new HashMap<>();
                dailySettledTransactionsMap.put("date", date);
                dailySettledTransactionsMap.put("value", totalSettledTransactions);
                dailySettledTransactions.add(dailySettledTransactionsMap);
            }

            return dailySettledTransactions;
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }

    }
}

interface PaymentStatus {
    String NEW = "NEW";
    String PAID = "PAID";
}