package com.irembo.portal.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public interface PaymentInvoiceProjection {
    BigDecimal getAmount();
    LocalDateTime getCreatedAt();
    String getCurrency();
    LocalDateTime getExpiryAt();
    String getInvoiceNumber();
    UUID getId();
    String getPaymentStatus();

}
