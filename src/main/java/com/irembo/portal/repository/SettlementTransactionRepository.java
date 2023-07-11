package com.irembo.portal.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.irembo.portal.dto.BalanceProjection;
import com.irembo.portal.dto.SettlementTransactionProjection;
import com.irembo.portal.model.SettlementTransaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface SettlementTransactionRepository extends JpaRepository<SettlementTransaction, UUID> {
    Page<SettlementTransactionProjection> findAllProjectedBy(UUID appAccountId, Pageable pageable);

    // create sumTransactionAmountByAccountIdAndSettlementDateAfter query method
    @Query("SELECT SUM(st.amount) FROM SettlementTransaction st WHERE st.appAccountId = ?1 AND st.settlementDate > ?2")
    BigDecimal sumTransactionAmountByAccountIdAndSettlementDateAfter(UUID accountId, LocalDateTime sevenDaysAgo);

    @Query(value = "SELECT currency, SUM(amount) AS totalAmount\n" + //
            "FROM settlement_transaction st\n" + //
            "WHERE st.app_account_id = ?1\n" + //
            "  AND st.settlement_status = 'DONE'\n" + //
            "GROUP BY currency;",nativeQuery = true)
    List<BalanceProjection> sumTransactionAmountByAccountIdAndDestinationAccountId(UUID accountId);

}
