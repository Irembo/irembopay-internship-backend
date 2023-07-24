package com.irembo.portal.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.dto.SettlementTransactionProjection;
import com.irembo.portal.service.SettlementTransactionService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/settlement-transaction")
public class SettlementTransactionController {

    @Autowired
    SettlementTransactionService settlementTransactionService;

    @GetMapping
    public Page<SettlementTransactionProjection> getAllPaymentInvoices(@RequestParam UUID accountId,
            Pageable pageable) {
        return settlementTransactionService.getAllSettlementTrasncation(accountId, pageable);
    }
}
