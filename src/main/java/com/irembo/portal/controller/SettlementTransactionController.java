package com.irembo.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.model.SettlementTransaction;
import com.irembo.portal.repository.SettlementTransactionRepository;

@RestController
@RequestMapping("/api")
public class SettlementTransactionController {
    private final SettlementTransactionRepository settlementTransactionRepository;

    @Autowired
    public SettlementTransactionController(SettlementTransactionRepository settlementTransactionRepository) {
        this.settlementTransactionRepository = settlementTransactionRepository;
    }

    @GetMapping("/settlement-transactions")
    public Page<SettlementTransaction> getAllSettlementTransactions(Pageable pageable) {
        return settlementTransactionRepository.findAll(pageable);
    }
}

