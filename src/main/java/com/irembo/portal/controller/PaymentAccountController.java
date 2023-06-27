package com.irembo.portal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.model.Bank;
import com.irembo.portal.model.PaymentAccount;
import com.irembo.portal.repository.PaymentAccountRepository;
import com.irembo.portal.service.PaymentAccountService;

@RestController
@RequestMapping("/api/payment-account")
public class PaymentAccountController {
    private final PaymentAccountRepository paymentAccountRepository;

    Logger logger = LoggerFactory.getLogger(PaymentAccountController.class);

    @Autowired
    PaymentAccountService paymentAccountService;

    public PaymentAccountController(PaymentAccountRepository paymentAccountRepository) {
        this.paymentAccountRepository = paymentAccountRepository;
    }

    @GetMapping
    public Page<PaymentAccount> getAllPaymentAccounts(Pageable pageable) {
        return paymentAccountRepository.findAll(pageable);
    }

    @PostMapping
    public ResponseEntity<PaymentAccount> createBank(@RequestBody PaymentAccount paymentAccount) {
        PaymentAccount createdPaymentAccount = paymentAccountService.createPaymentAccount(paymentAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPaymentAccount);
    }
}
