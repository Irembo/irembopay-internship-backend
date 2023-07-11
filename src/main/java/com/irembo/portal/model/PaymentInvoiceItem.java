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
@Table(name = "payment_invoice_item", indexes = {
        @Index(name = "idx_payment_invoice_item_status_payment_invoice", columnList = "payment_invoice_id"),
        @Index(name = "payment_invoice_item_status_index", columnList = "status") })
public class PaymentInvoiceItem {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "published")
    private boolean published;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "payment_invoice_id", referencedColumnName = "id")
    private PaymentInvoice paymentInvoice;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public PaymentInvoiceItem() {
        // Default constructor
    }

    public PaymentInvoiceItem(UUID id, BigDecimal amount, String description, boolean published, int quantity,
            String status, PaymentInvoice paymentInvoice, Product product,
            LocalDateTime createdAt) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.published = published;
        this.quantity = quantity;
        this.status = status;
        this.paymentInvoice = paymentInvoice;
        this.product = product;
        this.createdAt = createdAt;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PaymentInvoice getPaymentInvoice() {
        return paymentInvoice;
    }

    public void setPaymentInvoice(PaymentInvoice paymentInvoice) {
        this.paymentInvoice = paymentInvoice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
