package com.irembo.portal.controller;

import java.util.UUID;
import com.irembo.portal.dto.BalanceProjection;
import com.irembo.portal.dto.CountProjection;
import com.irembo.portal.dto.PaymentAccountBalance;
import com.irembo.portal.dto.PaymentStatusProjection;
import com.irembo.portal.exception.ApiException;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.service.AccountStatisticsService;

@CrossOrigin(origins = { "http://localhost:3000", "https://irembo-customer-portal.vercel.app" })
@RestController
@RequestMapping("/api/account-statistics")
public class AccountStatisticsController {

    @Autowired
    private AccountStatisticsService accountStatisticsService;

    @GetMapping("/balance")
    public List<BalanceProjection> getAccountBalance(@RequestParam(required = false) UUID accountId) {
        return accountStatisticsService.getAccountBalance(accountId);
    }

    @GetMapping("/balance/payment-account")
    public Page<PaymentAccountBalance> getAccountBalance(@RequestParam(required = false) UUID accountId, @RequestParam(required = false) UUID accountNumber,
            Pageable pageable) {
        return accountStatisticsService.getAccountBalanceForPaymentAccount(accountId, accountNumber, pageable);
    }

    @GetMapping("/projected-balance")
    public List<Map<String, Object>> getProjectedBalanceAfter7Days(@RequestParam(required = false) UUID accountId) {
        return accountStatisticsService.getProjectedBalanceAfter7Days(accountId);
    }

    @GetMapping("/total-paid-invoices")
    public long getTotalPaidInvoicesLast7Days(@RequestParam(required = false) UUID accountId) {
        return accountStatisticsService.getTotalPaidInvoicesLast7Days(accountId);
    }

    @GetMapping("/total-paid-invoices-for-payment-account")
    public List<CountProjection> getTotalPaidInvoicesLast7DaysForPaymentAccount(@RequestParam(required = false) UUID accountId,
            @RequestParam(required = false) UUID accountNumber) {
        return accountStatisticsService.getTotalPaidInvoicesLast7DaysForPaymentAccount(accountId, accountNumber);
    }

    @GetMapping("/total-transaction-value-last-7-days")
    public List<CountProjection> getTotalValueOfTransactionsLast7Days(
            @RequestParam(required = false) UUID accountNumber) {
        try {
            if (accountNumber == null) {
                throw new IllegalArgumentException("Account number is required");
            }
            return accountStatisticsService.getTotalValueOfTransactionsLast7Days(accountNumber);
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @GetMapping("/total-transaction-value-last-30-days")
    public List<CountProjection> getTotalValueOfTransactionsLast30Days(
            @RequestParam(required = false) UUID accountNumber) {
        return accountStatisticsService.getTotalValueOfTransactionsLast30Days(accountNumber);
    }

    @GetMapping("/total-transcations")
    public List<Map<String, Object>> getTotalTransactions(@RequestParam(required = false) UUID accountId, @RequestParam(required = false) int cycle) {
        return accountStatisticsService.getTotalDailyPaidInvoices(accountId, cycle);
    }

    @GetMapping("/total-settled-transcations")
    public List<Map<String, Object>> getTotalSettledTransactions(@RequestParam(required = false) UUID accountId,
            @RequestParam(required = false) int cycle) {
        return accountStatisticsService.getTotalDailySettledTransactions(accountId, cycle);
    }

    @GetMapping("/payment-status")
    public List<PaymentStatusProjection> getPaymentStatusGrouped(@RequestParam(required = false) UUID accountId) {
        return accountStatisticsService.getTotalInvoicesByStatus(accountId);
    }
}
