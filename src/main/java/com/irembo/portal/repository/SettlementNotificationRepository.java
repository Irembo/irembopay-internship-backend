package com.irembo.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.irembo.portal.model.SettlementNotification;
import java.util.UUID;import org.springframework.stereotype.Repository;

@Repository
public interface SettlementNotificationRepository extends JpaRepository<SettlementNotification, UUID> {
    // Custom query methods can be defined here if needed
}

