package com.irembo.portal.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.model.PaymentTransaction;
import com.irembo.portal.repository.PaymentTransactionRepository;

@RestController
@RequestMapping("/api/payment-transaction")
public class PaymentTransactionController {
    private final PaymentTransactionRepository paymentTransactionRepository;

    public PaymentTransactionController(PaymentTransactionRepository paymentTransactionRepository) {
        this.paymentTransactionRepository = paymentTransactionRepository;
    }

    @GetMapping
    public Page<PaymentTransaction> getAllPaymentTransactions(Pageable pageable) {
        return paymentTransactionRepository.findAll(pageable);
    }
}
