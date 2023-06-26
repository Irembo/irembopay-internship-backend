package com.irembo.portal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "app_account_id")
    private UUID appAccountId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "expiration")
    private Integer expiration;

    @Column(name = "notification_url", length = 255)
    private String notificationUrl;

    @Column(name = "product_code", nullable = false, length = 45)
    private String productCode;

    @Column(name = "product_type", nullable = false)
    private String productType;

    @Column(name = "published")
    private boolean published;

    @Column(name = "rra_notification")
    private boolean rraNotification;

    @Column(name = "rra_reference_id", length = 255)
    private String rraReferenceId;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Product() {
        // Default constructor
    }

    public Product(UUID id, BigDecimal amount, UUID appAccountId, LocalDateTime createdAt,
                   String currency, String description, Integer expiration, String notificationUrl,
                   String productCode, String productType, boolean published, boolean rraNotification,
                   String rraReferenceId, String status, LocalDateTime updatedAt) {
        this.id = id;
        this.amount = amount;
        this.appAccountId = appAccountId;
        this.createdAt = createdAt;
        this.currency = currency;
        this.description = description;
        this.expiration = expiration;
        this.notificationUrl = notificationUrl;
        this.productCode = productCode;
        this.productType = productType;
        this.published = published;
        this.rraNotification = rraNotification;
        this.rraReferenceId = rraReferenceId;
        this.status = status;
        this.updatedAt = updatedAt;
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

    public Integer getExpiration() {
        return expiration;
    }

    public void setExpiration(Integer expiration) {
        this.expiration = expiration;
    }

    public String getNotificationUrl() {
        return notificationUrl;
    }

    public void setNotificationUrl(String notificationUrl) {
        this.notificationUrl = notificationUrl;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRraNotification() {
        return rraNotification;
    }

    public void setRraNotification(boolean rraNotification) {
        this.rraNotification = rraNotification;
    }

    public String getRraReferenceId() {
        return rraReferenceId;
    }

    public void setRraReferenceId(String rraReferenceId) {
        this.rraReferenceId = rraReferenceId;
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
}

