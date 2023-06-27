package com.irembo.portal.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.model.PaymentChargeItem;
import com.irembo.portal.repository.PaymentChargeItemRepository;

@RestController
@RequestMapping("/api/payment-charge-item")
public class PaymentChargeItemController {
    private final PaymentChargeItemRepository paymentChargeItemRepository;

    public PaymentChargeItemController(PaymentChargeItemRepository paymentChargeItemRepository) {
        this.paymentChargeItemRepository = paymentChargeItemRepository;
    }

    @GetMapping
    public Page<PaymentChargeItem> getAllPaymentChargeItems(Pageable pageable) {
        return paymentChargeItemRepository.findAll(pageable);
    }
}

