package com.irembo.portal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "settlement_transaction", indexes = {
        @Index(name = "transaction_reference_index", columnList = "transaction_reference"),
})
public class SettlementTransaction {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "app_account_id")
    private UUID appAccountId;

    @Column(name = "approved_by_id")
    private UUID approvedById;

    @Column(name = "bank_transaction_reference")
    private String bankTransactionReference;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "narration")
    private String narration;

    @Column(name = "published")
    private boolean published;

    @Column(name = "settlement_date")
    private LocalDateTime settlementDate;

    @Column(name = "settlement_status", nullable = false)
    private String settlementStatus;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "transaction_reference", nullable = false, unique = true)
    private String transactionReference;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "destination_account_id", referencedColumnName = "id")
    private PaymentAccount destinationAccountId;

    @ManyToOne
    @JoinColumn(name = "related_settlement_id", referencedColumnName = "id")
    private SettlementTransaction relatedSettlementId;

    @ManyToOne
    @JoinColumn(name = "rra_settlement_id", referencedColumnName = "id")
    private SettlementTransaction rraSettlementId;

    @ManyToOne
    @JoinColumn(name = "source_account_id", referencedColumnName = "id")
    private PaymentAccount sourceAccountId;

    public SettlementTransaction() {
        // Default constructor
    }

    public SettlementTransaction(UUID id, BigDecimal amount, UUID appAccountId, UUID approvedById,
            String bankTransactionReference, LocalDateTime createdAt, String currency,
            String narration, boolean published, LocalDateTime settlementDate,
            String settlementStatus, String status, String transactionReference,
            LocalDateTime updatedAt, PaymentAccount destinationAccountId, SettlementTransaction relatedSettlementId,
            SettlementTransaction rraSettlementId, PaymentAccount sourceAccountId) {
        this.id = id;
        this.amount = amount;
        this.appAccountId = appAccountId;
        this.approvedById = approvedById;
        this.bankTransactionReference = bankTransactionReference;
        this.createdAt = createdAt;
        this.currency = currency;
        this.narration = narration;
        this.published = published;
        this.settlementDate = settlementDate;
        this.settlementStatus = settlementStatus;
        this.status = status;
        this.transactionReference = transactionReference;
        this.updatedAt = updatedAt;
        this.destinationAccountId = destinationAccountId;
        this.relatedSettlementId = relatedSettlementId;
        this.rraSettlementId = rraSettlementId;
        this.sourceAccountId = sourceAccountId;
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

    public UUID getAppAccountId() {
        return appAccountId;
    }

    public void setAppAccountId(UUID appAccountId) {
        this.appAccountId = appAccountId;
    }

    public UUID getApprovedById() {
        return approvedById;
    }

    public void setApprovedById(UUID approvedById) {
        this.approvedById = approvedById;
    }

    public String getBankTransactionReference() {
        return bankTransactionReference;
    }

    public void setBankTransactionReference(String bankTransactionReference) {
        this.bankTransactionReference = bankTransactionReference;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public LocalDateTime getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(LocalDateTime settlementDate) {
        this.settlementDate = settlementDate;
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

    public PaymentAccount getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(PaymentAccount destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }

    public SettlementTransaction getRelatedSettlementId() {
        return relatedSettlementId;
    }

    public void setRelatedSettlementId(SettlementTransaction relatedSettlementId) {
        this.relatedSettlementId = relatedSettlementId;
    }

    public SettlementTransaction getRraSettlementId() {
        return rraSettlementId;
    }

    public void setRraSettlementId(SettlementTransaction rraSettlementId) {
        this.rraSettlementId = rraSettlementId;
    }

    public PaymentAccount getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(PaymentAccount sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }
}
