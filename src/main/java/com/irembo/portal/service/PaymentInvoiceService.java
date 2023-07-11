package com.irembo.portal.service;

import org.springframework.stereotype.Service;

import com.irembo.portal.dto.PaymentInvoiceProjection;
import com.irembo.portal.model.PaymentInvoice;
import com.irembo.portal.repository.PaymentInvoiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentInvoiceService {

    @Autowired
    private PaymentInvoiceRepository paymentInvoiceRepository;

    // get all payment invoices
    public Page<PaymentInvoiceProjection> getAllPaymentInvoices(Pageable pageable, UUID appAccountId) {
        return paymentInvoiceRepository.findAllProjectedBy(appAccountId, pageable);
    }

    // get one payment invoice by id
    public Optional<PaymentInvoice> getPaymentInvoiceById(UUID id) {
        return paymentInvoiceRepository.findById(id);
    }

}
