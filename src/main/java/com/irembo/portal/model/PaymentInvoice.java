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
@Table(name = "payment_invoice", indexes = {
        @Index(name = "idx_payment_invoice", columnList = "invoice_number"),
        @Index(name = "payment_invoice_status_index", columnList = "status"),
        @Index(name = "transaction_id_index", columnList = "transaction_id"), })
public class PaymentInvoice {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "amount", precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "app_account_id")
    private UUID appAccountId;

    @Column(name = "batch_number", unique = true)
    private String batchNumber;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "description")
    private String description;

    @Column(name = "expiry_at")
    private LocalDateTime expiryAt;

    @Column(name = "invoice_number", nullable = false, unique = true)
    private String invoiceNumber;

    @Column(name = "language")
    private String language;

    @Column(name = "payment_link_url")
    private String paymentLinkUrl;

    @Column(name = "payment_made_at")
    private LocalDateTime paymentMadeAt;

    @Column(name = "payment_recorded_at")
    private LocalDateTime paymentRecordedAt;

    @Column(name = "payment_status", nullable = false)
    private String paymentStatus;

    @Column(name = "payout_amount", precision = 19, scale = 2)
    private BigDecimal payoutAmount;

    @Column(name = "published")
    private boolean published;

    @Column(name = "settled_at")
    private LocalDateTime settledAt;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "transaction_id", nullable = false)
    private String transactionId;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "batch_invoice_id", referencedColumnName = "id")
    private PaymentInvoice batchInvoiceId;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customerId;

    @ManyToOne
    @JoinColumn(name = "merchant_account_id", referencedColumnName = "id")
    private PaymentAccount merchantAccountId;

    @ManyToOne
    @JoinColumn(name = "revenue_account_id", referencedColumnName = "id")
    private PaymentAccount revenueAccountId;

    @ManyToOne
    @JoinColumn(name = "rra_settlement_transaction_id", referencedColumnName = "id")
    private SettlementTransaction rraSettlementTransactionId;

    @ManyToOne
    @JoinColumn(name = "settlement_account_id", referencedColumnName = "id")
    private PaymentAccount settlementAccountId;

    @ManyToOne
    @JoinColumn(name = "settlement_transaction_id", referencedColumnName = "id")
    private SettlementTransaction settlementTransactionId;

    @ManyToOne
    @JoinColumn(name = "split_rule_id", referencedColumnName = "id")
    private SplitRule splitRuleId;

    public PaymentInvoice() {
        // Default constructor is needed for JPA
    }

    public PaymentInvoice(UUID id, BigDecimal amount, UUID appAccountId, String batchNumber, LocalDateTime createdAt,
            String currency, String description, LocalDateTime expiryAt, String invoiceNumber,
            String language, Integer merchantNotificationCount, boolean merchantNotified,
            String paymentLinkUrl, LocalDateTime paymentMadeAt, LocalDateTime paymentRecordedAt,
            String paymentStatus, BigDecimal payoutAmount, boolean published, boolean rraNotified,
            LocalDateTime settledAt, String status, String transactionId, String type,
            LocalDateTime updatedAt, PaymentInvoice batchInvoiceId, Customer customerId, PaymentAccount merchantAccountId,
            PaymentAccount revenueAccountId, SettlementTransaction rraSettlementTransactionId, PaymentAccount settlementAccountId,
            SettlementTransaction settlementTransactionId, SplitRule splitRuleId) {
        this.id = id;
        this.amount = amount;
        this.appAccountId = appAccountId;
        this.batchNumber = batchNumber;
        this.createdAt = createdAt;
        this.currency = currency;
        this.description = description;
        this.expiryAt = expiryAt;
        this.invoiceNumber = invoiceNumber;
        this.language = language;
        this.paymentLinkUrl = paymentLinkUrl;
        this.paymentMadeAt = paymentMadeAt;
        this.paymentRecordedAt = paymentRecordedAt;
        this.paymentStatus = paymentStatus;
        this.payoutAmount = payoutAmount;
        this.published = published;
        this.settledAt = settledAt;
        this.status = status;
        this.transactionId = transactionId;
        this.type = type;
        this.updatedAt = updatedAt;
        this.batchInvoiceId = batchInvoiceId;
        this.customerId = customerId;
        this.merchantAccountId = merchantAccountId;
        this.revenueAccountId = revenueAccountId;
        this.rraSettlementTransactionId = rraSettlementTransactionId;
        this.settlementAccountId = settlementAccountId;
        this.settlementTransactionId = settlementTransactionId;
        this.splitRuleId = splitRuleId;
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

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getExpiryAt() {
        return expiryAt;
    }

    public void setExpiryAt(LocalDateTime expiryAt) {
        this.expiryAt = expiryAt;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPaymentLinkUrl() {
        return paymentLinkUrl;
    }

    public void setPaymentLinkUrl(String paymentLinkUrl) {
        this.paymentLinkUrl = paymentLinkUrl;
    }

    public LocalDateTime getPaymentMadeAt() {
        return paymentMadeAt;
    }

    public void setPaymentMadeAt(LocalDateTime paymentMadeAt) {
        this.paymentMadeAt = paymentMadeAt;
    }

    public LocalDateTime getPaymentRecordedAt() {
        return paymentRecordedAt;
    }

    public void setPaymentRecordedAt(LocalDateTime paymentRecordedAt) {
        this.paymentRecordedAt = paymentRecordedAt;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public BigDecimal getPayoutAmount() {
        return payoutAmount;
    }

    public void setPayoutAmount(BigDecimal payoutAmount) {
        this.payoutAmount = payoutAmount;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public LocalDateTime getSettledAt() {
        return settledAt;
    }

    public void setSettledAt(LocalDateTime settledAt) {
        this.settledAt = settledAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

    public PaymentInvoice getBatchInvoiceId() {
        return batchInvoiceId;
    }

    public void setBatchInvoiceId(PaymentInvoice batchInvoiceId) {
        this.batchInvoiceId = batchInvoiceId;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public PaymentAccount getMerchantAccountId() {
        return merchantAccountId;
    }

    public void setMerchantAccountId(PaymentAccount merchantAccountId) {
        this.merchantAccountId = merchantAccountId;
    }

    public PaymentAccount getRevenueAccountId() {
        return revenueAccountId;
    }

    public void setRevenueAccountId(PaymentAccount revenueAccountId) {
        this.revenueAccountId = revenueAccountId;
    }

    public SettlementTransaction getRraSettlementTransactionId() {
        return rraSettlementTransactionId;
    }

    public void setRraSettlementTransactionId(SettlementTransaction rraSettlementTransactionId) {
        this.rraSettlementTransactionId = rraSettlementTransactionId;
    }

    public PaymentAccount getSettlementAccountId() {
        return settlementAccountId;
    }

    public void setSettlementAccountId(PaymentAccount settlementAccountId) {
        this.settlementAccountId = settlementAccountId;
    }

    public SettlementTransaction getSettlementTransactionId() {
        return settlementTransactionId;
    }

    public void setSettlementTransactionId(SettlementTransaction settlementTransactionId) {
        this.settlementTransactionId = settlementTransactionId;
    }

    public SplitRule getSplitRuleId() {
        return splitRuleId;
    }

    public void setSplitRuleId(SplitRule splitRuleId) {
        this.splitRuleId = splitRuleId;
    }
}
