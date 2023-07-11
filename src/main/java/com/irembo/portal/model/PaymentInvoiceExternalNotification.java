package com.irembo.portal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Index;

@Entity
@Table(name = "payment_invoice_external_notification", indexes = {
        @Index(name = "external_invoice_notification_status_index", columnList = "notification_status"),
        @Index(name = "external_invoice_recipient_index", columnList = "recipient") })
public class PaymentInvoiceExternalNotification {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "notification_count", nullable = false)
    private int notificationCount;

    @Column(name = "notification_date")
    private LocalDateTime notificationDate;

    @Column(name = "notification_status", nullable = false)
    private String notificationStatus;

    @Column(name = "recipient", nullable = false)
    private String recipient;

    @ManyToOne
    @JoinColumn(name = "payment_invoice_id", referencedColumnName = "id")
    private PaymentInvoice paymentInvoice;

    @ManyToOne
    @JoinColumn(name = "settlement_transaction_id", referencedColumnName = "id")
    private SettlementTransaction settlementTransaction;

    public PaymentInvoiceExternalNotification() {
        // Default constructor
    }

    public PaymentInvoiceExternalNotification(UUID id, int notificationCount, LocalDateTime notificationDate,
            String notificationStatus, String recipient, PaymentInvoice paymentInvoice,
            SettlementTransaction settlementTransaction) {
        this.id = id;
        this.notificationCount = notificationCount;
        this.notificationDate = notificationDate;
        this.notificationStatus = notificationStatus;
        this.recipient = recipient;
        this.paymentInvoice = paymentInvoice;
        this.settlementTransaction = settlementTransaction;
    }

    // Getters and setters for each field

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getNotificationCount() {
        return notificationCount;
    }

    public void setNotificationCount(int notificationCount) {
        this.notificationCount = notificationCount;
    }

    public LocalDateTime getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(LocalDateTime notificationDate) {
        this.notificationDate = notificationDate;
    }

    public String getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(String notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public PaymentInvoice getPaymentInvoice() {
        return paymentInvoice;
    }

    public void setPaymentInvoice(PaymentInvoice paymentInvoice) {
        this.paymentInvoice = paymentInvoice;
    }

    public SettlementTransaction getSettlementTransaction() {
        return settlementTransaction;
    }

    public void setSettlementTransaction(SettlementTransaction settlementTransaction) {
        this.settlementTransaction = settlementTransaction;
    }
}
