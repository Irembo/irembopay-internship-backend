package com.irembo.portal.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.dto.SettlementTransactionProjection;
import com.irembo.portal.exception.ApiException;
import com.irembo.portal.service.SettlementTransactionService;

@CrossOrigin(origins = { "http://localhost:3000", "https://irembo-customer-portal.vercel.app" })
@RestController
@RequestMapping("/api/settlement-transaction")
public class SettlementTransactionController {

    @Autowired
    SettlementTransactionService settlementTransactionService;

    @GetMapping
    public Page<SettlementTransactionProjection> getAllPaymentInvoices(@RequestParam(required = false) UUID accountId,
            Pageable pageable) {
        return settlementTransactionService.getAllSettlementTrasncation(accountId, pageable);
    }

    @GetMapping("/destination-account")
    public Page<SettlementTransactionProjection> getAllSettlementTransactionsForDestinationAccountId(
            @RequestParam(required = false) UUID destinationAccountId, Pageable pageable) {
        try {
            if (destinationAccountId == null) {
                throw new IllegalArgumentException("'destinationAccountId' is required");
            }
            return settlementTransactionService.getAllSettlementTransactionsForDestinationAccountId(
                    destinationAccountId,
                    pageable);
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }

    }
}
