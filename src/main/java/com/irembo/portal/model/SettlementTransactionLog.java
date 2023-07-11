package com.irembo.portal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "settlement_transaction_log")
public class SettlementTransactionLog {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "note", length = 255)
    private String note;

    @Column(name = "settlement_status", nullable = false)
    private String settlementStatus;

    @Column(name = "transaction_reference", nullable = false,unique = true)
    private String transactionReference;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "settlement_transaction_id", referencedColumnName = "id")
    private SettlementTransaction settlementTransactionId;

    public SettlementTransactionLog() {
        // Default constructor
    }

    public SettlementTransactionLog(UUID id, LocalDateTime createdAt, String note, String settlementStatus,
                                   String transactionReference, LocalDateTime updatedAt, SettlementTransaction settlementTransactionId) {
        this.id = id;
        this.createdAt = createdAt;
        this.note = note;
        this.settlementStatus = settlementStatus;
        this.transactionReference = transactionReference;
        this.updatedAt = updatedAt;
        this.settlementTransactionId = settlementTransactionId;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSettlementStatus() {
        return settlementStatus;
    }

    public void setSettlementStatus(String settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public SettlementTransaction getSettlementTransactionId() {
        return settlementTransactionId;
    }

    public void setSettlementTransactionId(SettlementTransaction settlementTransactionId) {
        this.settlementTransactionId = settlementTransactionId;
    }
}

