package com.bancobogota.bankbogota.repository;

import com.bancobogota.bankbogota.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    Optional<Customer> findByDocumentNumber(String documentNumber);
    Optional<Customer> findByEmail(String email);
}
