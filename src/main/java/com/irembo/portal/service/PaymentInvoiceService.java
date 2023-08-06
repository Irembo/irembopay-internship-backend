package com.irembo.portal.service;

import org.springframework.stereotype.Service;

import com.irembo.portal.dto.PaymentInvoiceStatusExtraProjection;
import com.irembo.portal.dto.PaymentInvoiceStatusProjection;
import com.irembo.portal.exception.ApiException;
import com.irembo.portal.model.PaymentInvoice;
import com.irembo.portal.repository.PaymentInvoiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentInvoiceService {

    @Autowired
    private PaymentInvoiceRepository paymentInvoiceRepository;

    // get all payment invoices
    public Page<PaymentInvoiceStatusProjection> getAllPaymentInvoices(UUID accountNumber, Pageable pageable) {
        try {
            if (accountNumber == null) {
                throw new IllegalArgumentException("Account number is required");
            }
            return paymentInvoiceRepository.findByAppAccountId(accountNumber, pageable);
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }

    }

    // get all payment invoices
    public List<PaymentInvoiceStatusProjection> getAllPaymentInvoicesAll(UUID accountNumber) {
        try {
            if (accountNumber == null) {
                throw new IllegalArgumentException("Account number is required");
            }
            return paymentInvoiceRepository.findByAppAccountIdAll(accountNumber);
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }

    }

    // get one payment invoice by id
    public PaymentInvoiceStatusExtraProjection getPaymentInvoiceById(UUID id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Account number is required");
            }
            return paymentInvoiceRepository.getPaymentInvoiceDetailsWithStatus(id);
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }

    }

    public Page<PaymentInvoiceStatusProjection> searchPaymentInvoice(UUID accountNumber,
            String invoiceNumber,
            String status,
            Pageable pageable) {

        try {
            if (accountNumber == null || (status == null && invoiceNumber == null)) {
                throw new IllegalArgumentException("Account number, invoice number and status are required");
            }
            // Perform the search based on invoice number and status
            return paymentInvoiceRepository
                    .searchForInvoice(
                            accountNumber, invoiceNumber, status, pageable);
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }

    }
}
