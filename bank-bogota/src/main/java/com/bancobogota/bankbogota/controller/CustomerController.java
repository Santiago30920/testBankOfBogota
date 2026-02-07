package com.bancobogota.bankbogota.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancobogota.bankbogota.dto.CustomerRequest;
import com.bancobogota.bankbogota.model.Customer;
import com.bancobogota.bankbogota.service.CustomerService;

import jakarta.validation.Valid;

/**
 * Controlador REST para la gestión de clientes.
 * Proporciona endpoints para crear y consultar clientes.
 */
@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {
    
    private final CustomerService customerService;

    /**
     * Constructor del controlador.
     * @param customerService Servicio de gestión de clientes
     */
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Crea un nuevo cliente en el sistema.
     * @param request Datos del cliente a crear
     * @return ResponseEntity con el cliente creado y mensaje de éxito
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createCustomer(@Valid @RequestBody CustomerRequest request) {
        Customer customer = customerService.createCustomer(request);
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.CREATED.value());
        response.put("message", "Cliente creado exitosamente");
        response.put("data", customer);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Obtiene la lista de todos los clientes registrados.
     * @return ResponseEntity con la lista de clientes
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Clientes obtenidos exitosamente");
        response.put("data", customers);
        response.put("total", customers.size());
        
        return ResponseEntity.ok(response);
    }
}
