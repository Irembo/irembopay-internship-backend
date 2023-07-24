package com.irembo.portal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.irembo.portal.dto.PaymentAccountBalance;
import com.irembo.portal.dto.PaymentAccountProjection;
import com.irembo.portal.model.PaymentAccount;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface PaymentAccountRepository extends JpaRepository<PaymentAccount, UUID> {
    Page<PaymentAccountProjection> findByAppAccountId(UUID appAccountId, Pageable pageable);

    @Query(value = "SELECT * FROM payment_account WHERE app_account_id = ?1", nativeQuery = true)
    List<PaymentAccount> findByAppIdCustomQuery(UUID appAccountId);

    @Query("SELECT pa FROM PaymentAccount pa WHERE pa.appAccountId = :accountNumber AND pa.published = true AND status = 'ACTIVE'")
    Page<PaymentAccountProjection> findByAppAccountIdAndPublishedIsTrue(UUID accountNumber, Pageable pageable);

    @Query("SELECT pa FROM PaymentAccount pa WHERE pa.appAccountId = :accountId AND pa.id = :accountNumber")
    Page<PaymentAccountBalance> getBalanceForPaymentAccount(UUID accountId, UUID accountNumber, Pageable pageable);
}
