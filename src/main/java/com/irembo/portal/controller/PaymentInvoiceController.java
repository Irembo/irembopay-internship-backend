package com.irembo.portal.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.model.PaymentInvoice;
import com.irembo.portal.repository.PaymentInvoiceRepository;

@RestController
@RequestMapping("/api/payment-invoice")
public class PaymentInvoiceController {
    private final PaymentInvoiceRepository paymentInvoiceRepository;

    public PaymentInvoiceController(PaymentInvoiceRepository paymentInvoiceRepository) {
        this.paymentInvoiceRepository = paymentInvoiceRepository;
    }

    @GetMapping
    public Page<PaymentInvoice> getAllPaymentInvoices(Pageable pageable) {
        return paymentInvoiceRepository.findAll(pageable);
    }
}
