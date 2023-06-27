package com.irembo.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.model.PaymentAccount;
import com.irembo.portal.repository.PaymentAccountRepository;
import com.irembo.portal.service.PaymentAccountService;

@RestController
@RequestMapping("/api/payment-account")
public class PaymentAccountController {
    private final PaymentAccountRepository paymentAccountRepository;

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
    public PaymentAccount createPaymentAccount(PaymentAccount paymentAccount) {
        return paymentAccountService.createPaymentAccount(paymentAccount);
    }
}
