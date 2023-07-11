package com.irembo.portal.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public interface SettlementTransactionProjection {
    UUID getId();
    BigDecimal getAmount();
    LocalDateTime getCreatedAt();
    String getCurrency();
    LocalDateTime getSettlementDate();
    String getSettlementStatus();
    String getStatus();
}
