package com.irembo.portal.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.irembo.portal.dto.BalanceProjection;
import com.irembo.portal.dto.CountProjection;
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
        @Query(value = "SELECT SUM(st.amount) FROM settlement_transaction st WHERE st.settlement_date > ?1 AND st.destination_account_id != ?2", nativeQuery = true)
        List<CountProjection> sumTransactionAmountByAccountIdAndSettlementDateAfter(LocalDateTime sevenDaysAgo,
                        UUID accountNumber);

        @Query(value = "SELECT SUM(st.amount) FROM settlement_transaction st WHERE st.app_account_id = ?1 AND st.settlement_date > ?2", nativeQuery = true)
        BigDecimal sumTransactionAmountByAccountIdAndSettlementDateAfterCycle(UUID accountId,
                        LocalDateTime sevenDaysAgo);

        @Query(value = "SELECT currency, SUM(amount) AS totalAmount\n" + //
                        "FROM settlement_transaction st\n" + //
                        "WHERE st.app_account_id = ?1\n" + //
                        "  AND st.settlement_status = 'DONE'\n" + //
                        "GROUP BY currency;", nativeQuery = true)
        List<BalanceProjection> sumTransactionAmountByAccountIdAndDestinationAccountId(UUID accountId);

        Page<SettlementTransactionProjection> findByAppAccountId(UUID accountNumber, Pageable pageable);

        @Query(value = "SELECT id, currency, status, amount, app_account_id as appAccountId, created_at as createdAt, settlement_date as settlementDate, settlement_status as settlementStatus FROM settlement_transaction st WHERE st.destination_account_id = ?1 AND settlement_status = 'DONE'", nativeQuery = true)
        Page<SettlementTransactionProjection> findAllProjectedByDestinationAccountId(UUID destinationAccountId,
                        Pageable pageable);

}
