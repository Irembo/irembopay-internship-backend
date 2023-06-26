package com.irembo.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.model.PaymentLedger;
import com.irembo.portal.repository.PaymentLedgerRepository;

@RestController
@RequestMapping("/api")
public class PaymentLedgerController {
    private final PaymentLedgerRepository paymentLedgerRepository;

    @Autowired
    public PaymentLedgerController(PaymentLedgerRepository paymentLedgerRepository) {
        this.paymentLedgerRepository = paymentLedgerRepository;
    }

    @GetMapping("/payment-ledgers")
    public Page<PaymentLedger> getAllPaymentLedgers(Pageable pageable) {
        return paymentLedgerRepository.findAll(pageable);
    }
}

