package com.irembo.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.model.PaymentInvoiceItem;
import com.irembo.portal.repository.PaymentInvoiceItemRepository;

@RestController
@RequestMapping("/api")
public class PaymentInvoiceItemController {
    private final PaymentInvoiceItemRepository paymentInvoiceItemRepository;

    @Autowired
    public PaymentInvoiceItemController(PaymentInvoiceItemRepository paymentInvoiceItemRepository) {
        this.paymentInvoiceItemRepository = paymentInvoiceItemRepository;
    }

    @GetMapping("/payment-invoice-items")
    public Page<PaymentInvoiceItem> getAllPaymentInvoiceItems(Pageable pageable) {
        return paymentInvoiceItemRepository.findAll(pageable);
    }
}
