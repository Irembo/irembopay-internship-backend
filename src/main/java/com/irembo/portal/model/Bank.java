package com.irembo.portal.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bank")
public class Bank {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @Column(name = "bank_code")
    private String bankCode;

    @Column(name = "name")
    private String name;

    @Column(name = "published")
    private boolean published;

    @Column(name = "status")
    private String status;

    public Bank() {
        // Default constructor is needed for JPA
    }

    public Bank(UUID id, String bankCode, String name, boolean published, String status) {
        this.id = id;
        this.bankCode = bankCode;
        this.name = name;
        this.published = published;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
