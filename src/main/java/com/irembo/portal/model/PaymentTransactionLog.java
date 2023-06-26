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
@Table(name = "payment_transaction_log")
public class PaymentTransactionLog {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "raw_dump", length = 255)
    private String rawDump;

    @ManyToOne
    @JoinColumn(name = "payment_transaction_id", referencedColumnName = "id")
    private PaymentTransaction paymentTransaction;

    public PaymentTransactionLog() {
        // Default constructor
    }

    public PaymentTransactionLog(UUID id, LocalDateTime createdAt, String rawDump,
                                 PaymentTransaction paymentTransaction) {
        this.id = id;
        this.createdAt = createdAt;
        this.rawDump = rawDump;
        this.paymentTransaction = paymentTransaction;
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

    public String getRawDump() {
        return rawDump;
    }

    public void setRawDump(String rawDump) {
        this.rawDump = rawDump;
    }

    public PaymentTransaction getPaymentTransaction() {
        return paymentTransaction;
    }

    public void setPaymentTransaction(PaymentTransaction paymentTransaction) {
        this.paymentTransaction = paymentTransaction;
    }
}
