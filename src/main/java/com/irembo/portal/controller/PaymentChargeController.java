package com.irembo.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.model.PaymentCharge;
import com.irembo.portal.repository.PaymentChargeRepository;

@RestController
@RequestMapping("/api")
public class PaymentChargeController {
    private final PaymentChargeRepository paymentChargeRepository;

    @Autowired
    public PaymentChargeController(PaymentChargeRepository paymentChargeRepository) {
        this.paymentChargeRepository = paymentChargeRepository;
    }

    @GetMapping("/payment-charges")
    public Page<PaymentCharge> getAllPaymentCharges(Pageable pageable) {
        return paymentChargeRepository.findAll(pageable);
    }
}
