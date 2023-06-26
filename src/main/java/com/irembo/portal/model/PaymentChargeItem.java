package com.irembo.portal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "payment_charge_item")
public class PaymentChargeItem {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "payment_channel", nullable = false)
    private String paymentChannel;

    @Column(name = "payment_provider", nullable = false)
    private String paymentProvider;

    @Column(name = "share", precision = 19, scale = 2, nullable = false)
    private BigDecimal share;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "payment_charge_id", referencedColumnName = "id")
    private PaymentCharge paymentChargeId;

    public PaymentChargeItem() {
        // Default constructor is needed for JPA
    }

    public PaymentChargeItem(UUID id, LocalDateTime createdAt, String paymentChannel, String paymentProvider,
                             BigDecimal share, String status, LocalDateTime updatedAt, PaymentCharge paymentChargeId) {
        this.id = id;
        this.createdAt = createdAt;
        this.paymentChannel = paymentChannel;
        this.paymentProvider = paymentProvider;
        this.share = share;
        this.status = status;
        this.updatedAt = updatedAt;
        this.paymentChargeId = paymentChargeId;
    }

    // Getters and setters for each field

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

    public BigDecimal getShare() {
        return share;
    }

    public void setShare(BigDecimal share) {
        this.share = share;
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

    public PaymentCharge getPaymentChargeId() {
        return paymentChargeId;
    }

    public void setPaymentChargeId(PaymentCharge paymentChargeId) {
        this.paymentChargeId = paymentChargeId;
    }
}
