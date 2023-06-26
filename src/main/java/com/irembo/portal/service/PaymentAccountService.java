package com.irembo.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irembo.portal.model.PaymentAccount;
import com.irembo.portal.repository.PaymentAccountRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentAccountService {
    private final PaymentAccountRepository paymentAccountRepository;

    public PaymentAccountService(PaymentAccountRepository paymentAccountRepository) {
        this.paymentAccountRepository = paymentAccountRepository;
    }

    public PaymentAccount createPaymentAccount(PaymentAccount paymentAccount) {
        return paymentAccountRepository.save(paymentAccount);
    }

    public PaymentAccount updatePaymentAccount(PaymentAccount paymentAccount) {
        return paymentAccountRepository.save(paymentAccount);
    }

    public List<PaymentAccount> getAllPaymentAccounts() {
        return paymentAccountRepository.findAll();
    }

    public Optional<PaymentAccount> getPaymentAccountById(UUID id) {
        return paymentAccountRepository.findById(id);
    }

    public void deletePaymentAccount(UUID id) {
        paymentAccountRepository.deleteById(id);
    }
}
