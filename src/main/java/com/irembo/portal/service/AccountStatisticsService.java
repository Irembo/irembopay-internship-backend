package com.irembo.portal.service;

import org.springframework.stereotype.Service;

import com.irembo.portal.dto.BalanceProjection;
import com.irembo.portal.model.PaymentAccount;
import com.irembo.portal.model.PaymentInvoice;
import com.irembo.portal.repository.PaymentAccountRepository;
import com.irembo.portal.repository.PaymentInvoiceRepository;
import com.irembo.portal.repository.SettlementTransactionRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AccountStatisticsService {

    @Autowired
    private PaymentAccountRepository paymentAccountRepository;

    @Autowired
    private PaymentInvoiceRepository paymentInvoiceRepository;

    @Autowired
    private SettlementTransactionRepository settlementTransactionRepository;

    public List<BalanceProjection> getAccountBalance(UUID accountId) {

        List<BalanceProjection> settledInvoices = settlementTransactionRepository
                .sumTransactionAmountByAccountIdAndDestinationAccountId(
                        accountId);

        return settledInvoices;
    }

    public List<Map<String, Object>> getProjectedBalanceAfter7Days(UUID accountId) {
        List<BalanceProjection> currentBalance = getAccountBalance(accountId);

        // TODO: get last payout date
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

    }

    public long getTotalPaidInvoicesLast7Days(UUID accountId) {
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(365 + 7);

        return paymentInvoiceRepository.countByAppAccountIdAndPaymentStatusAndPaymentMadeAtAfter(
                accountId,
                PaymentStatus.PAID,
                sevenDaysAgo);
    }

    public BigDecimal getTotalValueOfTransactionsLast7Days(UUID accountId) {
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(365 + 7);

        return settlementTransactionRepository.sumTransactionAmountByAccountIdAndSettlementDateAfter(
                accountId,
                sevenDaysAgo);
    }

    public BigDecimal getTotalValueOfTransactionsLast30Days(UUID accountId) {
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(365 + 30);

        return settlementTransactionRepository.sumTransactionAmountByAccountIdAndSettlementDateAfter(
                accountId,
                thirtyDaysAgo);
    }

    public BigDecimal getAverageDailyTransactionValue(UUID accountId) {
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(365 + 30);
        LocalDateTime now = LocalDateTime.now();
        long daysBetween = ChronoUnit.DAYS.between(thirtyDaysAgo, now);
        BigDecimal totalTransactionValue = getTotalValueOfTransactionsLast30Days(accountId);

        return totalTransactionValue.divide(BigDecimal.valueOf(daysBetween), 2, RoundingMode.HALF_UP);
    }
}

interface PaymentStatus {
    String NEW = "NEW";
    String PAID = "PAID";
}