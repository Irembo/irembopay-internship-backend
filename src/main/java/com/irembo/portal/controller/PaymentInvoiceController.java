package com.irembo.portal.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.dto.PaymentInvoiceStatusExtraProjection;
import com.irembo.portal.dto.PaymentInvoiceStatusProjection;
import com.irembo.portal.exception.ApiException;
import com.irembo.portal.service.PaymentInvoiceService;

@CrossOrigin(origins = { "http://localhost:3000", "https://irembo-customer-portal.vercel.app" })
@RestController
@RequestMapping("/api/payment-invoice")
public class PaymentInvoiceController {

    @Autowired
    PaymentInvoiceService paymentInvoiceService;

    @GetMapping
    public Page<PaymentInvoiceStatusProjection> getAllPaymentInvoices(@RequestParam(required = false) UUID accountId,
            Pageable pageable) {
        try {
            if (accountId == null) {
                throw new IllegalArgumentException("The request parameter 'accountId' is required.");
            }
            return paymentInvoiceService.getAllPaymentInvoices(accountId, pageable);
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }

    }

    public List<PaymentInvoiceStatusProjection> getAllPaymentInvoicesAll(@RequestParam(required = false) UUID accountId) {
        try {
            if (accountId == null) {
                throw new IllegalArgumentException("The request parameter 'accountId' is required.");
            }
            return paymentInvoiceService.getAllPaymentInvoicesAll(accountId);
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }

    }

    @GetMapping("/invoice-number")
    public PaymentInvoiceStatusExtraProjection getPaymentInvoiceByInvoiceNumber(
            @RequestParam(required = false) UUID invoiceId) {
        try {
            if (invoiceId == null) {
                throw new IllegalArgumentException("The request parameter 'invoiceId' is required.");
            }
            return paymentInvoiceService.getPaymentInvoiceById(invoiceId);
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }

    }

    @GetMapping("/search")
    public Page<PaymentInvoiceStatusProjection> searchPaymentInvoice(@RequestParam(required = false) UUID accountId,
            @RequestParam(required = false) String invoiceNumber, @RequestParam(required = false) String status,
            Pageable pageable) {
        try {
            if (accountId == null) {
                throw new IllegalArgumentException("The request parameter 'accountId' is required.");
            }
            if (invoiceNumber == null && status == null) {
                throw new IllegalArgumentException(
                        "At least one of the request parameters 'invoiceNumber' or 'status' is required.");
            }
            return paymentInvoiceService.searchPaymentInvoice(accountId, invoiceNumber, status, pageable);
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred - ");
        }

    }

}
