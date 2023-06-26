package com.irembo.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.model.SettlementTransactionLog;
import com.irembo.portal.repository.SettlementTransactionLogRepository;

@RestController
@RequestMapping("/api")
public class SettlementTransactionLogController {
    private final SettlementTransactionLogRepository settlementTransactionLogRepository;

    @Autowired
    public SettlementTransactionLogController(SettlementTransactionLogRepository settlementTransactionLogRepository) {
        this.settlementTransactionLogRepository = settlementTransactionLogRepository;
    }

    @GetMapping("/settlement-transaction-logs")
    public Page<SettlementTransactionLog> getAllSettlementTransactionLogs(Pageable pageable) {
        return settlementTransactionLogRepository.findAll(pageable);
    }
}

