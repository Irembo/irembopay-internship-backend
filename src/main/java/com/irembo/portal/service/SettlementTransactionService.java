package com.irembo.portal.service;

import org.springframework.stereotype.Service;

import com.irembo.portal.dto.SettlementTransactionProjection;
import com.irembo.portal.model.SettlementTransaction;
import com.irembo.portal.repository.SettlementTransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

@Service
public class SettlementTransactionService {

    @Autowired
    private SettlementTransactionRepository settlementTransactionRepository;

    // get all payment invoices
    public Page<SettlementTransactionProjection> getAllSettlementTrasncation(UUID accountNumber, Pageable pageable) {
        return settlementTransactionRepository.findByAppAccountId(accountNumber, pageable);
    }

    // get one payment invoice by id
    public Optional<SettlementTransaction> getPaymentInvoiceById(UUID id) {
        return settlementTransactionRepository.findById(id);
    }

    public Page<SettlementTransactionProjection> getAllSettlementTransactionsForDestinationAccountId(
            UUID destinationAccountId, Pageable pageable) {
        return settlementTransactionRepository.findAllProjectedByDestinationAccountId(destinationAccountId, pageable);
    }

}
