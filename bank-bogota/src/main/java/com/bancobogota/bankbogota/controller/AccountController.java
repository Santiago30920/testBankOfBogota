package com.bancobogota.bankbogota.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bancobogota.bankbogota.dto.AccountRequest;
import com.bancobogota.bankbogota.model.Account;
import com.bancobogota.bankbogota.service.AccountService;

import jakarta.validation.Valid;

/**
 * Controlador REST para la gestión de cuentas bancarias.
 * Proporciona endpoints para crear y consultar cuentas asociadas a clientes.
 */
@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "*")
public class AccountController {
    
    private final AccountService accountService;

    /**
     * Constructor del controlador.
     * @param accountService Servicio de gestión de cuentas
     */
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Crea una nueva cuenta bancaria para un cliente.
     * @param request Datos de la cuenta a crear (requiere customerId)
     * @return ResponseEntity con la cuenta creada y mensaje de éxito
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createAccount(@Valid @RequestBody AccountRequest request) {
        Account account = accountService.createAccount(request);
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.CREATED.value());
        response.put("message", "Cuenta creada exitosamente");
        response.put("data", account);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Obtiene las cuentas filtradas opcionalmente por cliente.
     * @param customerId ID del cliente (opcional). Si no se especifica, retorna todas las cuentas
     * @return ResponseEntity con la lista de cuentas
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAccounts(@RequestParam(required = false) String customerId) {
        List<Account> accounts = accountService.getAccountsByCustomerId(customerId);
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Cuentas obtenidas exitosamente");
        response.put("data", accounts);
        response.put("total", accounts.size());
        
        return ResponseEntity.ok(response);
    }

    /**
     * Elimina una cuenta.
     * @param id ID de la cuenta a eliminar
     * @return ResponseEntity con mensaje de confirmación
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteAccount(@PathVariable String id) {
        accountService.deleteAccount(id);
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Cuenta eliminada exitosamente");
        
        return ResponseEntity.ok(response);
    }
}
