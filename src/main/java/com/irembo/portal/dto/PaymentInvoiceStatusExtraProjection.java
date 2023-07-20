package com.irembo.portal.dto;

import java.time.LocalDateTime;

public interface PaymentInvoiceStatusExtraProjection extends PaymentInvoiceStatusProjection {
    LocalDateTime getSettledAt();

    LocalDateTime getPaymentMadeAt();

    String getAccountType();

}
