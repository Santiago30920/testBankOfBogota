package com.bancobogota.bankbogota;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "com.bancobogota.bankbogota")
@EnableMongoRepositories(basePackages = "com.bancobogota.bankbogota.repository")
public class BankBogotaApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankBogotaApplication.class, args);
    }
}
