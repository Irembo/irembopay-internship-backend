package com.irembo.portal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.irembo.portal.dto.SettlementTransactionProjection;
import com.irembo.portal.model.SettlementTransaction;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface SettlementTransactionRepository extends JpaRepository<SettlementTransaction, UUID> {
    Page<SettlementTransactionProjection> findAllProjectedBy(Pageable pageable);
}
