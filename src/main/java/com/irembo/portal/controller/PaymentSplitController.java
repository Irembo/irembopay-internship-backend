package com.irembo.portal.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.model.PaymentSplit;
import com.irembo.portal.repository.PaymentSplitRepository;

@RestController
@RequestMapping("/api/payment-split")
public class PaymentSplitController {
    private final PaymentSplitRepository paymentSplitRepository;

    public PaymentSplitController(PaymentSplitRepository paymentSplitRepository) {
        this.paymentSplitRepository = paymentSplitRepository;
    }

    @GetMapping
    public Page<PaymentSplit> getAllPaymentSplits(Pageable pageable) {
        return paymentSplitRepository.findAll(pageable);
    }
}
