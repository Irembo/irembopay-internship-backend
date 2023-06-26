package com.irembo.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.irembo.portal.model.SettlementTransactionLog;
import java.util.UUID;import org.springframework.stereotype.Repository;

@Repository
public interface SettlementTransactionLogRepository extends JpaRepository<SettlementTransactionLog, UUID> {
    // Custom query methods can be defined here if needed
}

