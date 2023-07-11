package com.irembo.portal.dto;

import java.util.UUID;

public interface PaymentAccountProjection {
    UUID getId();

    String getAccountName();

    String getCurrency();

    boolean isPublished();

    String getStatus();

    String getType();
}