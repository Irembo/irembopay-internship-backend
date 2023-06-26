package com.irembo.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.irembo.portal.model.PaymentInvoice;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface PaymentInvoiceRepository extends JpaRepository<PaymentInvoice, UUID> {
    // Custom query methods can be defined here if needed
}
