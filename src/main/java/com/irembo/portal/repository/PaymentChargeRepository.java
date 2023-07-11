package com.irembo.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.irembo.portal.model.PaymentCharge;
import java.util.UUID;import org.springframework.stereotype.Repository;


public interface PaymentChargeRepository extends JpaRepository<PaymentCharge, UUID> {
    // Custom query methods can be defined here if needed
}

