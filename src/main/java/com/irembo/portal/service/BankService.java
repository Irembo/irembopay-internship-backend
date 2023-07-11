package com.irembo.portal.service;

import org.springframework.stereotype.Service;

import com.irembo.portal.model.Bank;
import com.irembo.portal.repository.BankRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BankService {
    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public Bank createBank(Bank bank) {
        return bankRepository.save(bank);
    }

    public Bank updateBank(Bank bank) {
        return bankRepository.save(bank);
    }

    public Optional<Bank> getBankById(UUID bankId) {
        return bankRepository.findById(bankId);
    }

    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }
}
