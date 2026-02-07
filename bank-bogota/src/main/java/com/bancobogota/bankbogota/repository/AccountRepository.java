package com.bancobogota.bankbogota.repository;

import com.bancobogota.bankbogota.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    List<Account> findByCustomerId(String customerId);
    long countByCustomerId(String customerId);
}
