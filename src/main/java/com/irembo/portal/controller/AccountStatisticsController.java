package com.irembo.portal.controller;

import java.util.UUID;
import com.irembo.portal.dto.BalanceProjection;
import com.irembo.portal.dto.CountProjection;
import com.irembo.portal.dto.PaymentAccountBalance;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.service.AccountStatisticsService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/account-statistics")
public class AccountStatisticsController {

    @Autowired
    private AccountStatisticsService accountStatisticsService;

    @GetMapping("/balance/{accountId}")
    public List<BalanceProjection> getAccountBalance(@PathVariable UUID accountId) {
        return accountStatisticsService.getAccountBalance(accountId);
    }

    @GetMapping("/balance/payment-account/{accountId}/{accountNumber}")
    public Page<PaymentAccountBalance> getAccountBalance(@PathVariable UUID accountId, @PathVariable UUID accountNumber,
            Pageable pageable) {
        return accountStatisticsService.getAccountBalanceForPaymentAccount(accountId, accountNumber, pageable);
    }

    @GetMapping("/projected-balance/{accountId}")
    public List<Map<String, Object>> getProjectedBalanceAfter7Days(@PathVariable UUID accountId) {
        return accountStatisticsService.getProjectedBalanceAfter7Days(accountId);
    }

    @GetMapping("/total-paid-invoices/{accountId}")
    public long getTotalPaidInvoicesLast7Days(@PathVariable UUID accountId) {
        return accountStatisticsService.getTotalPaidInvoicesLast7Days(accountId);
    }

    @GetMapping("/total-paid-invoices-for-payment-account/{accountId}/{accountNumber}")
    public List<CountProjection> getTotalPaidInvoicesLast7DaysForPaymentAccount(@PathVariable UUID accountId,
            @PathVariable UUID accountNumber) {
        return accountStatisticsService.getTotalPaidInvoicesLast7DaysForPaymentAccount(accountId, accountNumber);
    }

    @GetMapping("/total-transaction-value-last-7-days/{accountNumber}")
    public List<CountProjection> getTotalValueOfTransactionsLast7Days(
            @PathVariable UUID accountNumber) {
        return accountStatisticsService.getTotalValueOfTransactionsLast7Days(accountNumber);
    }

    @GetMapping("/total-transaction-value-last-30-days/{accountNumber}")
    public List<CountProjection> getTotalValueOfTransactionsLast30Days(
            @PathVariable UUID accountNumber) {
        return accountStatisticsService.getTotalValueOfTransactionsLast30Days(accountNumber);
    }

    @GetMapping("/total-transcations/{accountId}/{cycle}")
    public List<Map<String, Object>> getTotalTransactions(@PathVariable UUID accountId, @PathVariable int cycle) {
        return accountStatisticsService.getTotalDailyPaidInvoices(accountId, cycle);
    }

    @GetMapping("/total-settled-transcations/{accountId}/{cycle}")
    public List<Map<String, Object>> getTotalSettledTransactions(@PathVariable UUID accountId,
            @PathVariable int cycle) {
        return accountStatisticsService.getTotalDailySettledTransactions(accountId, cycle);
    }
}
