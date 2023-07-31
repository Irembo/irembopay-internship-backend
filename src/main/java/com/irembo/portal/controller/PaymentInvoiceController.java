package com.irembo.portal.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.dto.PaymentInvoiceStatusExtraProjection;
import com.irembo.portal.dto.PaymentInvoiceStatusProjection;
import com.irembo.portal.service.PaymentInvoiceService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/payment-invoice")
public class PaymentInvoiceController {

    @Autowired
    PaymentInvoiceService paymentInvoiceService;

    @GetMapping
    public Page<PaymentInvoiceStatusProjection> getAllPaymentInvoices(@RequestParam UUID accountId,
            Pageable pageable) {
        return paymentInvoiceService.getAllPaymentInvoices(accountId, pageable);
    }

    @GetMapping("/all")
    public List<PaymentInvoiceStatusProjection> getAllPaymentInvoicesAll(@RequestParam UUID accountId) {
        return paymentInvoiceService.getAllPaymentInvoicesAll(accountId);
    }

    @GetMapping("/invoice-number")
    public PaymentInvoiceStatusExtraProjection getPaymentInvoiceByInvoiceNumber(
            @RequestParam UUID invoiceId) {
        return paymentInvoiceService.getPaymentInvoiceById(invoiceId);
    }

    @GetMapping("/search/{accountId}")
    public Page<PaymentInvoiceStatusProjection> searchPaymentInvoice(@PathVariable UUID accountId,
            @RequestParam String search, Pageable pageable) {
        return paymentInvoiceService.searchPaymentInvoice(accountId, search, pageable);
    }

}
