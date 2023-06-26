package com.irembo.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.irembo.portal.model.SettlementTransaction;
import java.util.UUID;import org.springframework.stereotype.Repository;

@Repository
public interface SettlementTransactionRepository extends JpaRepository<SettlementTransaction, UUID> {
    // Custom query methods can be defined here if needed
}

