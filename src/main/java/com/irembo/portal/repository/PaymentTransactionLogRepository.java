package com.irembo.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.irembo.portal.model.PaymentTransactionLog;
import java.util.UUID;import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTransactionLogRepository extends JpaRepository<PaymentTransactionLog, UUID> {
    // Custom query methods can be defined here if needed
}

