package com.irembo.portal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.irembo.portal.dto.PaymentAccountProjection;
import com.irembo.portal.model.PaymentAccount;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface PaymentAccountRepository extends JpaRepository<PaymentAccount, UUID> {
    Page<PaymentAccountProjection> findByAppAccountId(UUID appAccountId, Pageable pageable);
}
