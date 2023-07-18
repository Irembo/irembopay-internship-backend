package com.irembo.portal.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.dto.PaymentAccountProjection;

import com.irembo.portal.service.PaymentAccountService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/payment-account")
public class PaymentAccountController {

    @Autowired
    PaymentAccountService paymentAccountService;

    Logger logger = LoggerFactory.getLogger(PaymentAccountController.class);

    @GetMapping
    public Page<PaymentAccountProjection> getAllPaymentAccountsByMerchantId(
            @RequestParam UUID accountId,
            Pageable pageable) {
        return paymentAccountService.getPaymentAccountsByAccountId(accountId, pageable);
    }
}
