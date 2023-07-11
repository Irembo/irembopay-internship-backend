package com.irembo.portal.controller;

import java.math.BigDecimal;
import java.util.UUID;
import com.irembo.portal.dto.BalanceProjection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.service.AccountStatisticsService;

@RestController
@RequestMapping("/api/account-statistics")
public class AccountStatisticsController {

    @Autowired
    private AccountStatisticsService accountStatisticsService;

    @GetMapping("/balance/{accountId}")
    public List<BalanceProjection> getAccountBalance(@PathVariable UUID accountId) {
        return accountStatisticsService.getAccountBalance(accountId);
    }

    @GetMapping("/projected-balance/{accountId}")
    public List<Map<String, Object>> getProjectedBalanceAfter7Days(@PathVariable UUID accountId) {
        return accountStatisticsService.getProjectedBalanceAfter7Days(accountId);
    }

    @GetMapping("/total-paid-invoices/{accountId}")
    public long getTotalPaidInvoicesLast7Days(@PathVariable UUID accountId) {
        return accountStatisticsService.getTotalPaidInvoicesLast7Days(accountId);
    }

    @GetMapping("/total-transaction-value-last-7-days/{accountId}")
    public BigDecimal getTotalValueOfTransactionsLast7Days(@PathVariable UUID accountId) {
        return accountStatisticsService.getTotalValueOfTransactionsLast7Days(accountId);
    }

    @GetMapping("/total-transaction-value-last-30-days/{accountId}")
    public BigDecimal getTotalValueOfTransactionsLast30Days(@PathVariable UUID accountId) {
        return accountStatisticsService.getTotalValueOfTransactionsLast30Days(accountId);
    }

    @GetMapping("/average-daily-transaction-value/{accountId}")
    public BigDecimal getAverageDailyTransactionValue(@PathVariable UUID accountId) {
        return accountStatisticsService.getAverageDailyTransactionValue(accountId);
    }
}
