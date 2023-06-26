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

import jakarta.persistence.Index;

@Entity
@Table(name = "payment_transaction", indexes = {
        @Index(name = "idx_payment_transaction_status_pinvoice_id_tstatus", columnList = "status,payment_invoice_id,transaction_status"),
        @Index(name = "payment_transaction_status_index", columnList = "status"),
        @Index(name = "payment_transaction_transaction_status_index", columnList = "transaction_status"),
        @Index(name = "payment_transaction_channel_index", columnList = "payment_channel"),
        @Index(name = "payment_transaction_provider_index", columnList = "payment_provider"), })
public class PaymentTransaction {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "app_account_id")
    private UUID appAccountId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "narration", length = 255)
    private String narration;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    @Column(name = "payment_channel", nullable = false)
    private String paymentChannel;

    @Column(name = "payment_provider", nullable = false)
    private String paymentProvider;

    @Column(name = "payment_reference", nullable = false, unique = true)
    private String paymentReference;

    @Column(name = "provider_reference", length = 255)
    private String providerReference;

    @Column(name = "published")
    private boolean published;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "transaction_status", nullable = false)
    private String transactionStatus;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "payment_invoice_id", referencedColumnName = "id")
    private PaymentInvoice paymentInvoice;

    public PaymentTransaction() {
        // Default constructor
    }

    public PaymentTransaction(UUID id, BigDecimal amount, UUID appAccountId, LocalDateTime createdAt,
            String narration, LocalDateTime paidAt, String paymentChannel,
            String paymentProvider, String paymentReference, String providerReference,
            boolean published, String status, String transactionStatus,
            LocalDateTime updatedAt, Customer customer, PaymentInvoice paymentInvoice) {
        this.id = id;
        this.amount = amount;
        this.appAccountId = appAccountId;
        this.createdAt = createdAt;
        this.narration = narration;
        this.paidAt = paidAt;
        this.paymentChannel = paymentChannel;
        this.paymentProvider = paymentProvider;
        this.paymentReference = paymentReference;
        this.providerReference = providerReference;
        this.published = published;
        this.status = status;
        this.transactionStatus = transactionStatus;
        this.updatedAt = updatedAt;
        this.customer = customer;
        this.paymentInvoice = paymentInvoice;
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

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }

    public String getPaymentChannel() {
        return paymentChannel;
    }

    public void setPaymentChannel(String paymentChannel) {
        this.paymentChannel = paymentChannel;
    }

    public String getPaymentProvider() {
        return paymentProvider;
    }

    public void setPaymentProvider(String paymentProvider) {
        this.paymentProvider = paymentProvider;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public String getProviderReference() {
        return providerReference;
    }

    public void setProviderReference(String providerReference) {
        this.providerReference = providerReference;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public PaymentInvoice getPaymentInvoice() {
        return paymentInvoice;
    }

    public void setPaymentInvoice(PaymentInvoice paymentInvoice) {
        this.paymentInvoice = paymentInvoice;
    }
}
