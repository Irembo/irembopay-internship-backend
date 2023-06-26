package com.irembo.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irembo.portal.model.PaymentInvoiceItem;

import java.util.UUID;

@Repository
public interface PaymentInvoiceItemRepository extends JpaRepository<PaymentInvoiceItem, UUID> {
    // Custom query methods can be defined here if needed
}
