package com.bancobogota.bankbogota.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entidad que representa un cliente del banco.
 * Contiene información personal y de identificación del cliente.
 */
@Document(collection = "customers")
public class Customer {
    @Id
    private String id;
    private DocumentType documentType;
    private String documentNumber;
    private String fullName;
    private String email;

    public Customer() {
    }

    public Customer(String id, DocumentType documentType, String documentNumber, String fullName, String email) {
        this.id = id;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.fullName = fullName;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
