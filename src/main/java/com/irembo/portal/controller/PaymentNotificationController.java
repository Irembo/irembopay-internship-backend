package com.irembo.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.model.PaymentNotification;
import com.irembo.portal.repository.PaymentNotificationRepository;

@RestController
@RequestMapping("/api")
public class PaymentNotificationController {
    private final PaymentNotificationRepository paymentNotificationRepository;

    @Autowired
    public PaymentNotificationController(PaymentNotificationRepository paymentNotificationRepository) {
        this.paymentNotificationRepository = paymentNotificationRepository;
    }

    @GetMapping("/payment-notifications")
    public Page<PaymentNotification> getAllPaymentNotifications(Pageable pageable) {
        return paymentNotificationRepository.findAll(pageable);
    }
}
