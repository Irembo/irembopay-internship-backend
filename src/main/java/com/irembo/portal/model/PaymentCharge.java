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
@Table(name = "payment_charge")
public class PaymentCharge {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "app_account_id")
    private UUID appAccountId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "is_default", columnDefinition = "boolean default false")
    private boolean defaultCharge;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "share_type", nullable = false)
    private String shareType;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product productId;

    public PaymentCharge() {
        // Default constructor is needed for JPA
    }

    public PaymentCharge(UUID id, UUID appAccountId, LocalDateTime createdAt, boolean defaultCharge,
                         String name, String shareType, String status, LocalDateTime updatedAt, Product productId) {
        this.id = id;
        this.appAccountId = appAccountId;
        this.createdAt = createdAt;
        this.defaultCharge = defaultCharge;
        this.name = name;
        this.shareType = shareType;
        this.status = status;
        this.updatedAt = updatedAt;
        this.productId = productId;
    }

    // Getters and setters for each field

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public boolean isDefaultCharge() {
        return defaultCharge;
    }

    public void setDefaultCharge(boolean defaultCharge) {
        this.defaultCharge = defaultCharge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
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

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }
}
