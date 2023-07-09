package com.irembo.portal.dto;

import java.math.BigDecimal;

public interface BalanceProjection {
    String getCurrency();
    BigDecimal getTotalAmount();
    void setTotalAmount(BigDecimal add);
}