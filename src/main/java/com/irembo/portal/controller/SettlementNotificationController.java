package com.irembo.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.model.SettlementNotification;
import com.irembo.portal.repository.SettlementNotificationRepository;

@RestController
@RequestMapping("/api")
public class SettlementNotificationController {
    private final SettlementNotificationRepository settlementNotificationRepository;

    @Autowired
    public SettlementNotificationController(SettlementNotificationRepository settlementNotificationRepository) {
        this.settlementNotificationRepository = settlementNotificationRepository;
    }

    @GetMapping("/settlement-notifications")
    public Page<SettlementNotification> getAllSettlementNotifications(Pageable pageable) {
        return settlementNotificationRepository.findAll(pageable);
    }
}

