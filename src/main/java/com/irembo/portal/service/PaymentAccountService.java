package com.irembo.portal.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.irembo.portal.dto.PaymentAccountProjection;
import com.irembo.portal.exception.ApiException;
import com.irembo.portal.repository.PaymentAccountRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PaymentAccountService {

    @Autowired
    private PaymentAccountRepository paymentAccountRepository;

    public Page<PaymentAccountProjection> getPaymentAccountsByAccountId(UUID accountNumber, Pageable pageable) {
        try {
            if (accountNumber == null) {
                throw new IllegalArgumentException("Account number is required");
            }
            return paymentAccountRepository.findByAppAccountIdAndPublishedIsTrue(accountNumber, pageable);
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }

    }
}
