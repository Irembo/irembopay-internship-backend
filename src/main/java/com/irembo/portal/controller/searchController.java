package com.irembo.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.service.PaymentInvoiceService;
import com.irembo.portal.service.SettlementTransactionService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/payment-invoice")
public class searchController {

    @Autowired
    PaymentInvoiceService paymentInvoiceService;

     @Autowired
    SettlementTransactionService settlementTransactionService;

}
