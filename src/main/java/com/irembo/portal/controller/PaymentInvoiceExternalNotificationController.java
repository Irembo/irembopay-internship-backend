package com.irembo.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.model.PaymentInvoiceExternalNotification;
import com.irembo.portal.repository.PaymentInvoiceExternalNotificationRepository;

@RestController
@RequestMapping("/api")
public class PaymentInvoiceExternalNotificationController {
    private final PaymentInvoiceExternalNotificationRepository paymentInvoiceExternalNotificationRepository;

    @Autowired
    public PaymentInvoiceExternalNotificationController(PaymentInvoiceExternalNotificationRepository paymentInvoiceExternalNotificationRepository) {
        this.paymentInvoiceExternalNotificationRepository = paymentInvoiceExternalNotificationRepository;
    }

    @GetMapping("/payment-invoice-external-notifications")
    public Page<PaymentInvoiceExternalNotification> getAllPaymentInvoiceExternalNotifications(Pageable pageable) {
        return paymentInvoiceExternalNotificationRepository.findAll(pageable);
    }
}

