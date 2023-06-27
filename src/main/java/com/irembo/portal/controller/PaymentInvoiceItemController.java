package com.irembo.portal.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.model.PaymentInvoiceItem;
import com.irembo.portal.repository.PaymentInvoiceItemRepository;

@RestController
@RequestMapping("/api/payment-invoice-item")
public class PaymentInvoiceItemController {
    private final PaymentInvoiceItemRepository paymentInvoiceItemRepository;

    public PaymentInvoiceItemController(PaymentInvoiceItemRepository paymentInvoiceItemRepository) {
        this.paymentInvoiceItemRepository = paymentInvoiceItemRepository;
    }

    @GetMapping
    public Page<PaymentInvoiceItem> getAllPaymentInvoiceItems(Pageable pageable) {
        return paymentInvoiceItemRepository.findAll(pageable);
    }
}
