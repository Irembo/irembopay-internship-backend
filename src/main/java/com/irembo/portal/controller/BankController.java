package com.irembo.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.model.Bank;
import com.irembo.portal.repository.BankRepository;

@RestController
@RequestMapping("/api")
public class BankController {
    private final BankRepository bankRepository;

    @Autowired
    public BankController(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @GetMapping("/banks")
    public Page<Bank> getAllBanks(Pageable pageable) {
        return bankRepository.findAll(pageable);
    }
}
