package com.irembo.portal.service;

import org.springframework.stereotype.Service;

import com.irembo.portal.repository.PaymentInvoiceRepository;
import com.irembo.portal.repository.SettlementTransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Service
public class searchService {

    @Autowired
    private PaymentInvoiceRepository paymentInvoiceRepository;

    @Autowired
    private SettlementTransactionRepository settlementTransactionRepository;

    // search for either payment invoice or settlement transaction. Use can search
    // by: invoiceNumber, status and search for settlement transcation by:
    // settlementStatus

    public String searchPaymentInvoice(UUID accountNumber, String search, Pageable pageable) {
        return "hello";
    }

}
