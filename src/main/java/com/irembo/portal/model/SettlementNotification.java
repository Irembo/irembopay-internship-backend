package com.irembo.portal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "settlement_notification")
public class SettlementNotification {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "emails", nullable = false, columnDefinition = "text")
    private String emails;

    @Column(name = "settlement_status", nullable = false)
    private String settlementStatus;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "payment_account_id", referencedColumnName = "id")
    private PaymentAccount paymentAccountId;

    public SettlementNotification() {
        // Default constructor
    }

    public SettlementNotification(UUID id, LocalDateTime createdAt, String emails, String settlementStatus,
                                 String status, LocalDateTime updatedAt, PaymentAccount paymentAccountId) {
        this.id = id;
        this.createdAt = createdAt;
        this.emails = emails;
        this.settlementStatus = settlementStatus;
        this.status = status;
        this.updatedAt = updatedAt;
        this.paymentAccountId = paymentAccountId;
    }

    // Getters and setters for each field

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getSettlementStatus() {
        return settlementStatus;
    }

    public void setSettlementStatus(String settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public PaymentAccount getPaymentAccountId() {
        return paymentAccountId;
    }

    public void setPaymentAccountId(PaymentAccount paymentAccountId) {
        this.paymentAccountId = paymentAccountId;
    }
}

