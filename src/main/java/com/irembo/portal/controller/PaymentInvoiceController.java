package com.irembo.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.model.PaymentInvoice;
import com.irembo.portal.repository.PaymentInvoiceRepository;

@RestController
@RequestMapping("/api")
public class PaymentInvoiceController {
    private final PaymentInvoiceRepository paymentInvoiceRepository;

    @Autowired
    public PaymentInvoiceController(PaymentInvoiceRepository paymentInvoiceRepository) {
        this.paymentInvoiceRepository = paymentInvoiceRepository;
    }

    @GetMapping("/payment-invoices")
    public Page<PaymentInvoice> getAllPaymentInvoices(Pageable pageable) {
        return paymentInvoiceRepository.findAll(pageable);
    }
}
