package com.irembo.portal.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.irembo.portal.dto.PaymentAccountProjection;
import com.irembo.portal.repository.PaymentAccountRepository;

import org.springframework.data.domain.Pageable;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PaymentAccountService {

    @Autowired
    private PaymentAccountRepository paymentAccountRepository;

    public Page<PaymentAccountProjection> getPaymentAccountsByAccountId(UUID accountNumber, Pageable pageable) {
        return paymentAccountRepository.findByAppAccountIdAndPublishedIsTrue(accountNumber, pageable);
    }
}
