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
@Table(name = "payment_ledger")
public class PaymentLedger {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "narration", length = 1024)
    private String narration;

    @Column(name = "reference", nullable = false, length = 1024)
    private String reference;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "type", nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "payment_account_id", referencedColumnName = "id")
    private PaymentAccount paymentAccount;

    @ManyToOne
    @JoinColumn(name = "payment_transaction_id", referencedColumnName = "id")
    private PaymentTransaction paymentTransaction;

    @ManyToOne
    @JoinColumn(name = "settlement_transaction_id", referencedColumnName = "id")
    private SettlementTransaction settlementTransaction;

    public PaymentLedger() {
        // Default constructor
    }

    public PaymentLedger(UUID id, BigDecimal amount, LocalDateTime createdAt, String narration, String reference,
                         String status, String type, PaymentAccount paymentAccount,
                         PaymentTransaction paymentTransaction, SettlementTransaction settlementTransaction) {
        this.id = id;
        this.amount = amount;
        this.createdAt = createdAt;
        this.narration = narration;
        this.reference = reference;
        this.status = status;
        this.type = type;
        this.paymentAccount = paymentAccount;
        this.paymentTransaction = paymentTransaction;
        this.settlementTransaction = settlementTransaction;
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

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
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

    public PaymentAccount getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(PaymentAccount paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    public PaymentTransaction getPaymentTransaction() {
        return paymentTransaction;
    }

    public void setPaymentTransaction(PaymentTransaction paymentTransaction) {
        this.paymentTransaction = paymentTransaction;
    }

    public SettlementTransaction getSettlementTransaction() {
        return settlementTransaction;
    }

    public void setSettlementTransaction(SettlementTransaction settlementTransaction) {
        this.settlementTransaction = settlementTransaction;
    }
}

