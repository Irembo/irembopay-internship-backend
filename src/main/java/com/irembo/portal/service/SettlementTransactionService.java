package com.irembo.portal.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.irembo.portal.dto.SettlementTransactionProjection;
import com.irembo.portal.exception.ApiException;
import com.irembo.portal.model.SettlementTransaction;
import com.irembo.portal.repository.SettlementTransactionRepository;

@Service
public class SettlementTransactionService {

    @Autowired
    private SettlementTransactionRepository settlementTransactionRepository;

    // get all payment invoices
    public Page<SettlementTransactionProjection> getAllSettlementTrasncation(UUID accountNumber, Pageable pageable) {
        try {
            if (accountNumber == null) {
                throw new IllegalArgumentException("'accountNumber' is required");
            }
            return settlementTransactionRepository.findByAppAccountId(accountNumber, pageable);
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }

    }

    // get one payment invoice by id
    public Optional<SettlementTransaction> getPaymentInvoiceById(UUID id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("'id' is required");
            }
            return settlementTransactionRepository.findById(id);
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }

    }

    public Page<SettlementTransactionProjection> getAllSettlementTransactionsForDestinationAccountId(
            UUID destinationAccountId, Pageable pageable) {
        try {
            if (destinationAccountId == null) {
                throw new IllegalArgumentException("'destinationAccountId' is required");
            }
            return settlementTransactionRepository.findAllProjectedByDestinationAccountId(destinationAccountId,
                    pageable);
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }

    }

}
