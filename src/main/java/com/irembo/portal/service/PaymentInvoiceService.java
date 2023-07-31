package com.irembo.portal.service;

import org.springframework.stereotype.Service;

import com.irembo.portal.dto.PaymentInvoiceStatusExtraProjection;
import com.irembo.portal.dto.PaymentInvoiceStatusProjection;
import com.irembo.portal.model.PaymentInvoice;
import com.irembo.portal.repository.PaymentInvoiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentInvoiceService {

    @Autowired
    private PaymentInvoiceRepository paymentInvoiceRepository;

    // get all payment invoices
    public Page<PaymentInvoiceStatusProjection> getAllPaymentInvoices(UUID accountNumber, Pageable pageable) {
        return paymentInvoiceRepository.findByAppAccountId(accountNumber, pageable);
    }

    // get all payment invoices
    public List<PaymentInvoiceStatusProjection> getAllPaymentInvoicesAll(UUID accountNumber) {
        return paymentInvoiceRepository.findByAppAccountIdAll(accountNumber);
    }

    // get one payment invoice by id
    public PaymentInvoiceStatusExtraProjection getPaymentInvoiceById(UUID id) {
        return paymentInvoiceRepository.getPaymentInvoiceDetailsWithStatus(id);
    }

    public Page<PaymentInvoiceStatusProjection> searchPaymentInvoice(UUID accountNumber,
            String invoiceNumber,
            String status,
            Pageable pageable) {

        // Perform the search based on invoice number and status
        return paymentInvoiceRepository
                .searchForInvoice(
                        accountNumber, invoiceNumber, status, pageable);
    }
}
