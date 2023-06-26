package com.irembo.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.model.PaymentTransactionLog;
import com.irembo.portal.repository.PaymentTransactionLogRepository;

@RestController
@RequestMapping("/api")
public class PaymentTransactionLogController {
    private final PaymentTransactionLogRepository paymentTransactionLogRepository;

    @Autowired
    public PaymentTransactionLogController(PaymentTransactionLogRepository paymentTransactionLogRepository) {
        this.paymentTransactionLogRepository = paymentTransactionLogRepository;
    }

    @GetMapping("/payment-transaction-logs")
    public Page<PaymentTransactionLog> getAllPaymentTransactionLogs(Pageable pageable) {
        return paymentTransactionLogRepository.findAll(pageable);
    }
}
