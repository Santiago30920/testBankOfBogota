package com.bancobogota.bankbogota.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AccountRequest {
    @NotNull(message = "El customerId no puede ser nulo")
    @NotBlank(message = "El customerId es obligatorio")
    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
