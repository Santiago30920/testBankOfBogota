package com.bancobogota.bankbogota.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "API Bank Bogota - Bienvenido");
        response.put("version", "1.0.0");
        response.put("endpoints", Map.of(
            "customers", Map.of(
                "POST", "/api/customers - Crear cliente",
                "GET", "/api/customers - Listar clientes"
            ),
            "accounts", Map.of(
                "POST", "/api/accounts - Crear cuenta",
                "GET", "/api/accounts?customerId=xxx - Listar cuentas"
            )
        ));
        return response;
    }
}
