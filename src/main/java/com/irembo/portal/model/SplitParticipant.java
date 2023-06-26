package com.irembo.portal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "split_participant")
public class SplitParticipant {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "share", nullable = false)
    private BigDecimal share;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "payment_account_id", referencedColumnName = "id")
    private PaymentAccount paymentAccountId;

    @ManyToOne
    @JoinColumn(name = "split_rule_id", referencedColumnName = "id")
    private SplitRule splitRuleId;

    public SplitParticipant() {
        // Default constructor
    }

    public SplitParticipant(UUID id, LocalDateTime createdAt, BigDecimal share, String status, String type,
                            LocalDateTime updatedAt, PaymentAccount paymentAccountId, SplitRule splitRuleId) {
        this.id = id;
        this.createdAt = createdAt;
        this.share = share;
        this.status = status;
        this.type = type;
        this.updatedAt = updatedAt;
        this.paymentAccountId = paymentAccountId;
        this.splitRuleId = splitRuleId;
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

    public BigDecimal getShare() {
        return share;
    }

    public void setShare(BigDecimal share) {
        this.share = share;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public SplitRule getSplitRuleId() {
        return splitRuleId;
    }

    public void setSplitRuleId(SplitRule splitRuleId) {
        this.splitRuleId = splitRuleId;
    }
}

