package com.irembo.portal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Index;

@Entity
@Table(name = "customer", indexes = {
        @Index(name = "customer_email_index", columnList = "email"),
        @Index(name = "customer_phone_number_index", columnList = "phone_number"),
})
public class Customer {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "app_account_id")
    private UUID appAccountId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "email")
    private String email;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "identifier")
    private String identifier;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "published")
    private boolean published;

    @Column(name = "status")
    private String status;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Customer() {
        // Default constructor is needed for JPA
    }

    public Customer(UUID id, UUID appAccountId, LocalDateTime createdAt, String email, String fullName,
            String identifier, String phoneNumber, boolean published, String status, LocalDateTime updatedAt) {
        this.id = id;
        this.appAccountId = appAccountId;
        this.createdAt = createdAt;
        this.email = email;
        this.fullName = fullName;
        this.identifier = identifier;
        this.phoneNumber = phoneNumber;
        this.published = published;
        this.status = status;
        this.updatedAt = updatedAt;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
