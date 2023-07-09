package com.irembo.portal.service;

import org.springframework.stereotype.Service;

import com.irembo.portal.model.PaymentAccount;
import com.irembo.portal.model.PaymentInvoice;
import com.irembo.portal.repository.PaymentAccountRepository;
import com.irembo.portal.repository.PaymentInvoiceRepository;
import com.irembo.portal.repository.SettlementTransactionRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AccountStatisticsService {

    @Autowired
    private PaymentAccountRepository paymentAccountRepository;

    @Autowired
    private PaymentInvoiceRepository paymentInvoiceRepository;

    @Autowired
    private SettlementTransactionRepository settlementTransactionRepository;

    public BigDecimal getAccountBalance(UUID accountId) {
        List<PaymentAccount> paymentAccount = paymentAccountRepository.findByAppIdCustomQuery(accountId);

        if (paymentAccount.isEmpty()) {
            throw new NoSuchElementException("Account not found");
        }

        PaymentAccount account = paymentAccount.get(0);

        // logic for getting balance

        // to get the value of settled invoices for a merchant, we check settlement
        // transactions
        // we find all settlement transactions where appAccountId is the merchant's id
        // AND destinationAccountId is same as (get paymentAccount using
        // accountId).getAccountNumber
        // we sum the transaction amounts of all the settlement transactions we found
        System.out.println(account.getAccountNumber());
        System.out.println(accountId);
        BigDecimal settledInvoices = settlementTransactionRepository
                .sumTransactionAmountByAccountIdAndDestinationAccountId(
                        accountId,
                        account.getAccountNumber());

        return settledInvoices;
    }

    public BigDecimal getProjectedBalanceAfter7Days(UUID accountId) {
        BigDecimal currentBalance = getAccountBalance(accountId);

        LocalDateTime sevenDaysFromNow = LocalDateTime.now().plusDays(365 + 7);

        // we check PAID invoices for last 7 days for the merchant
        // then we sum the amounts of all the invoices we found
        // then we add the sum to the current balance

        BigDecimal paidInvoices = paymentInvoiceRepository
                .sumInvoiceAmountByAppAccountIdAndPaymentStatusAndPaymentMadeAtAfter(
                        accountId,
                        PaymentStatus.PAID,
                        sevenDaysFromNow);

        return currentBalance.add(paidInvoices);
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