package com.irembo.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.dto.PaymentInvoiceProjection;
import com.irembo.portal.service.PaymentInvoiceService;

@RestController
@RequestMapping("/api/payment-invoice")
public class PaymentInvoiceController {

    @Autowired
    PaymentInvoiceService paymentInvoiceService;

    @GetMapping
    public Page<PaymentInvoiceProjection> getAllPaymentInvoices(Pageable pageable) {
        return paymentInvoiceService.getAllPaymentInvoices(pageable);
    }
}
