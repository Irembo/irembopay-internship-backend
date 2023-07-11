package com.irembo.portal.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.dto.SettlementTransactionProjection;
import com.irembo.portal.service.SettlementTransactionService;

@RestController
@RequestMapping("/api/settlement-transaction")
public class SettlementTransactionController {

    @Autowired
    SettlementTransactionService settlementTransactionService;

    @GetMapping
    public Page<SettlementTransactionProjection> getAllPaymentInvoices(Pageable pageable,
            @RequestParam(required = true) UUID appAccountId) {
        return settlementTransactionService.getAllSettlementTrasncation(pageable, appAccountId);
    }
}
