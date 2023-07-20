package com.irembo.portal.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public interface PaymentInvoiceStatusProjection {
    UUID getId();

    BigDecimal getAmount();

    String getCurrency();

    LocalDateTime getCreatedAt();

    LocalDateTime getExpiryAt();

    String getPaymentStatus();

    String getStatus();

    String getInvoiceNumber();

    BigDecimal getInvoicePayout();
}
