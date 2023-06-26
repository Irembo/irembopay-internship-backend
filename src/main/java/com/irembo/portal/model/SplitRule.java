package com.irembo.portal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "split_rule")
public class SplitRule {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "app_account_id")
    private UUID appAccountId;

    @Column(name = "application_mode", nullable = false)
    private String applicationMode;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "is_default", columnDefinition = "boolean default false")
    private boolean isDefault;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "published")
    private boolean published;

    @Column(name = "split_type", nullable = false)
    private String splitType;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public SplitRule() {
        // Default constructor
    }

    public SplitRule(UUID id, UUID appAccountId, String applicationMode, LocalDateTime createdAt,
            boolean isDefault, String name, boolean published, String splitType, String status,
            LocalDateTime updatedAt) {
        this.id = id;
        this.appAccountId = appAccountId;
        this.applicationMode = applicationMode;
        this.createdAt = createdAt;
        this.isDefault = isDefault;
        this.name = name;
        this.published = published;
        this.splitType = splitType;
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

    public UUID getAppAccountId() {
        return appAccountId;
    }

    public void setAppAccountId(UUID appAccountId) {
        this.appAccountId = appAccountId;
    }

    public String getApplicationMode() {
        return applicationMode;
    }

    public void setApplicationMode(String applicationMode) {
        this.applicationMode = applicationMode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public String getSplitType() {
        return splitType;
    }

    public void setSplitType(String splitType) {
        this.splitType = splitType;
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
