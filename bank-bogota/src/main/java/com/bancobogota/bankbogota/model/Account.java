package com.bancobogota.bankbogota.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entidad que representa una cuenta bancaria.
 * Cada cuenta está asociada a un cliente y tiene un número único generado automáticamente.
 */
@Document(collection = "accounts")
public class Account {
    @Id
    private String id;
    private String customerId;
    private String accountNumber;
    private AccountStatus status;

    public Account() {
    }

    public Account(String id, String customerId, String accountNumber, AccountStatus status) {
        this.id = id;
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }
}
