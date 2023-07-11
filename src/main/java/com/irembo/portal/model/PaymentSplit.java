package com.irembo.portal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payment_split")
public class PaymentSplit {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "payment_invoice_id", referencedColumnName = "id")
    private PaymentInvoice paymentInvoice;

    @ManyToOne
    @JoinColumn(name = "payment_transaction_id", referencedColumnName = "id")
    private PaymentTransaction paymentTransaction;

    @ManyToOne
    @JoinColumn(name = "split_participant_id", referencedColumnName = "id")
    private SplitParticipant splitParticipant;

    public PaymentSplit() {
        // Default constructor
    }

    public PaymentSplit(UUID id, BigDecimal amount, LocalDateTime createdAt, String status, LocalDateTime updatedAt,
            PaymentInvoice paymentInvoice, PaymentTransaction paymentTransaction,
            SplitParticipant splitParticipant) {
        this.id = id;
        this.amount = amount;
        this.createdAt = createdAt;
        this.status = status;
        this.updatedAt = updatedAt;
        this.paymentInvoice = paymentInvoice;
        this.paymentTransaction = paymentTransaction;
        this.splitParticipant = splitParticipant;
    }

    // Getters and setters for each field

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

    public PaymentInvoice getPaymentInvoice() {
        return paymentInvoice;
    }

    public void setPaymentInvoice(PaymentInvoice paymentInvoice) {
        this.paymentInvoice = paymentInvoice;
    }

    public PaymentTransaction getPaymentTransaction() {
        return paymentTransaction;
    }

    public void setPaymentTransaction(PaymentTransaction paymentTransaction) {
        this.paymentTransaction = paymentTransaction;
    }

    public SplitParticipant getSplitParticipant() {
        return splitParticipant;
    }

    public void setSplitParticipant(SplitParticipant splitParticipant) {
        this.splitParticipant = splitParticipant;
    }
}
