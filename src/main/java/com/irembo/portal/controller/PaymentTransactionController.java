package com.irembo.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.model.PaymentTransaction;
import com.irembo.portal.repository.PaymentTransactionRepository;

@RestController
@RequestMapping("/api")
public class PaymentTransactionController {
    private final PaymentTransactionRepository paymentTransactionRepository;

    @Autowired
    public PaymentTransactionController(PaymentTransactionRepository paymentTransactionRepository) {
        this.paymentTransactionRepository = paymentTransactionRepository;
    }

    @GetMapping("/payment-transactions")
    public Page<PaymentTransaction> getAllPaymentTransactions(Pageable pageable) {
        return paymentTransactionRepository.findAll(pageable);
    }
}
