package com.irembo.portal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.irembo.portal.dto.PaymentInvoiceProjection;
import com.irembo.portal.model.PaymentInvoice;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

@Repository
public interface PaymentInvoiceRepository extends JpaRepository<PaymentInvoice, UUID> {
    Page<PaymentInvoiceProjection> findAllProjectedBy(Pageable pageable);
}
