package com.irembo.portal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "payment_account", indexes = {
        @Index(name = "payment_account_status_index", columnList = "status"), })
public class PaymentAccount {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "app_account_id")
    private UUID appAccountId;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "identifier", nullable = false)
    private String identifier;

    @Column(name = "is_default", columnDefinition = "boolean default false")
    private boolean defaultAccount;

    @Column(name = "last_settlement_date")
    private LocalDateTime lastSettlementDate;

    @Column(name = "merchant_id")
    private UUID merchantId;

    @Column(name = "payout_enabled")
    private boolean payoutEnabled;

    @Column(name = "published")
    private boolean published;

    @Column(name = "status")
    private String status;

    @Column(name = "type")
    private String type;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(name = "wallet_balance", precision = 19, scale = 2)
    private BigDecimal walletBalance;

    @ManyToOne
    @JoinColumn(name = "bank_id", referencedColumnName = "id")
    private Bank bankId;

    @Column(name = "rra_district_code")
    private String rraDistrictCode;

    @ManyToOne
    @JoinColumn(name = "revenue_account_id", referencedColumnName = "id")
    private PaymentAccount revenueAccountId;

    public PaymentAccount() {
        // Default constructor is needed for JPA
    }

    public PaymentAccount(UUID id, String accountName, String accountNumber, UUID appAccountId,
            LocalDateTime createdAt, String currency, String identifier, boolean defaultAccount,
            LocalDateTime lastSettlementDate, UUID merchantId, boolean payoutEnabled, boolean published,
            String status, String type, LocalDateTime updatedAt, BigDecimal walletBalance,
            Bank bankId, String rraDistrictCode, PaymentAccount revenueAccountId) {
        this.id = id;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.appAccountId = appAccountId;
        this.createdAt = createdAt;
        this.currency = currency;
        this.identifier = identifier;
        this.defaultAccount = defaultAccount;
        this.lastSettlementDate = lastSettlementDate;
        this.merchantId = merchantId;
        this.payoutEnabled = payoutEnabled;
        this.published = published;
        this.status = status;
        this.type = type;
        this.updatedAt = updatedAt;
        this.walletBalance = walletBalance;
        this.bankId = bankId;
        this.rraDistrictCode = rraDistrictCode;
        this.revenueAccountId = revenueAccountId;
    }

    public UUID getId() {
        return id;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public UUID getAppAccountId() {
        return appAccountId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getCurrency() {
        return currency;
    }

    public String getIdentifier() {
        return identifier;
    }

    public boolean isDefaultAccount() {
        return defaultAccount;
    }

    public LocalDateTime getLastSettlementDate() {
        return lastSettlementDate;
    }

    public UUID getMerchantId() {
        return merchantId;
    }

    public boolean isPayoutEnabled() {
        return payoutEnabled;
    }

    public boolean isPublished() {
        return published;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public BigDecimal getWalletBalance() {
        return walletBalance;
    }

    public Bank getBankId() {
        return bankId;
    }

    public String getRraDistrictCode() {
        return rraDistrictCode;
    }

    public PaymentAccount getRevenueAccountId() {
        return revenueAccountId;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAppAccountId(UUID appAccountId) {
        this.appAccountId = appAccountId;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setDefaultAccount(boolean defaultAccount) {
        this.defaultAccount = defaultAccount;
    }

    public void setLastSettlementDate(LocalDateTime lastSettlementDate) {
        this.lastSettlementDate = lastSettlementDate;
    }

    public void setMerchantId(UUID merchantId) {
        this.merchantId = merchantId;
    }

    public void setPayoutEnabled(boolean payoutEnabled) {
        this.payoutEnabled = payoutEnabled;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setWalletBalance(BigDecimal walletBalance) {
        this.walletBalance = walletBalance;
    }

    public void setBankId(Bank bankId) {
        this.bankId = bankId;
    }

    public void setRraDistrictCode(String rraDistrictCode) {
        this.rraDistrictCode = rraDistrictCode;
    }

    public void setRevenueAccountId(PaymentAccount revenueAccountId) {
        this.revenueAccountId = revenueAccountId;
    }
}
