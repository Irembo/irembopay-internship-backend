package com.irembo.portal.repository;

import org.hibernate.mapping.List;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.irembo.portal.dto.SettlementTransactionProjection;
import com.irembo.portal.model.SettlementTransaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface SettlementTransactionRepository extends JpaRepository<SettlementTransaction, UUID> {
    Page<SettlementTransactionProjection> findAllProjectedBy(Pageable pageable);

    // create sumTransactionAmountByAccountIdAndSettlementDateAfter query method
    @Query("SELECT SUM(st.amount) FROM SettlementTransaction st WHERE st.appAccountId = ?1 AND st.settlementDate > ?2")
    BigDecimal sumTransactionAmountByAccountIdAndSettlementDateAfter(UUID accountId, LocalDateTime sevenDaysAgo);

    @Query("SELECT SUM(st.amount) FROM SettlementTransaction st WHERE st.appAccountId = ?1 AND st.destinationAccountId = ?2")
    BigDecimal sumTransactionAmountByAccountIdAndDestinationAccountId(UUID accountId, String accountNumber);

}
