package com.irembo.portal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payment_notification")
public class PaymentNotification {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "notification_url", length = 255)
    private String notificationUrl;

    @Column(name = "response_code", nullable = false)
    private String responseCode;

    @Column(name = "response_description", length = 255)
    private String responseDescription;

    @ManyToOne
    @JoinColumn(name = "payment_invoice_id", referencedColumnName = "id")
    private PaymentInvoice paymentInvoice;

    public PaymentNotification() {
        // Default constructor
    }

    public PaymentNotification(UUID id, LocalDateTime createdAt, String notificationUrl, String responseCode,
                               String responseDescription, PaymentInvoice paymentInvoice) {
        this.id = id;
        this.createdAt = createdAt;
        this.notificationUrl = notificationUrl;
        this.responseCode = responseCode;
        this.responseDescription = responseDescription;
        this.paymentInvoice = paymentInvoice;
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

    public String getNotificationUrl() {
        return notificationUrl;
    }

    public void setNotificationUrl(String notificationUrl) {
        this.notificationUrl = notificationUrl;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public PaymentInvoice getPaymentInvoice() {
        return paymentInvoice;
    }

    public void setPaymentInvoice(PaymentInvoice paymentInvoice) {
        this.paymentInvoice = paymentInvoice;
    }
}
