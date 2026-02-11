package com.bancobogota.bankbogota.service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.bancobogota.bankbogota.dto.AccountRequest;
import com.bancobogota.bankbogota.exception.BusinessException;
import com.bancobogota.bankbogota.exception.CustomerNotFoundException;
import com.bancobogota.bankbogota.model.Account;
import com.bancobogota.bankbogota.model.AccountStatus;
import com.bancobogota.bankbogota.repository.AccountRepository;
import com.bancobogota.bankbogota.repository.CustomerRepository;

/**
 * Servicio para la gestión de cuentas bancarias.
 * Contiene la lógica de negocio para crear y consultar cuentas.
 */
@Service
public class AccountService {
    
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    /**
     * Constructor del servicio.
     * @param accountRepository Repositorio de cuentas
     * @param customerRepository Repositorio de clientes
     */
    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    /**
     * Crea una nueva cuenta bancaria para un cliente.
     * Valida que el cliente exista y que no tenga ya una cuenta.
     * Genera automáticamente el número de cuenta y establece estado ACTIVE.
     * @param request Datos de la cuenta (customerId)
     * @return Cuenta creada
     * @throws CustomerNotFoundException si el cliente no existe
     * @throws BusinessException si el cliente ya tiene una cuenta
     */
    public Account createAccount(AccountRequest request) {
        String customerId = request.getCustomerId();
        
        if (customerId == null || customerId.isEmpty()) {
            throw new BusinessException("El customerId no puede ser nulo o vacío");
        }
        
        // Validar que el cliente existe
        if (!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundException("El cliente con id " + customerId + " no existe");
        }

        // Validar que el cliente nuevo solo pueda tener una cuenta
        long accountCount = accountRepository.countByCustomerId(customerId);
        if (accountCount > 0) {
            throw new BusinessException("El cliente ya tiene una cuenta registrada");
        }

        Account account = new Account();
        account.setCustomerId(customerId);
        account.setAccountNumber(generateAccountNumber());
        
        // Usar el status del request, o ACTIVE por defecto si no se especifica
        AccountStatus status = request.getStatus() != null ? request.getStatus() : AccountStatus.ACTIVE;
        account.setStatus(status);

        return accountRepository.save(account);
    }

    /**
     * Obtiene cuentas filtradas por cliente o todas si no se especifica.
     * @param customerId ID del cliente (opcional)
     * @return Lista de cuentas
     * @throws CustomerNotFoundException si se especifica un customerId que no existe
     */
    public List<Account> getAccountsByCustomerId(String customerId) {
        if (customerId != null && !customerId.isEmpty()) {
            // Validar que el cliente existe
            if (!customerRepository.existsById(customerId)) {
                throw new CustomerNotFoundException("El cliente con id " + customerId + " no existe");
            }
            return accountRepository.findByCustomerId(customerId);
        }
        return accountRepository.findAll();
    }

    /**
     * Genera un número de cuenta aleatorio de 10 dígitos.
     * @return Número de cuenta generado
     */
    private String generateAccountNumber() {
        Random random = new Random();
        long number = 1000000000L + (long)(random.nextDouble() * 9000000000L);
        return String.valueOf(number);
    }

    /**
     * Elimina todas las cuentas asociadas a un cliente.
     * @param customerId ID del cliente
     */
    public void deleteAccountsByCustomerId(String customerId) {
        List<Account> accounts = accountRepository.findByCustomerId(customerId);
        if (!accounts.isEmpty()) {
            accountRepository.deleteAll(accounts);
        }
    }

    /**
     * Elimina una cuenta por su ID.
     * @param accountId ID de la cuenta a eliminar
     */
    public void deleteAccount(String accountId) {
        if (accountId == null || accountId.isEmpty()) {
            throw new BusinessException("El id de la cuenta no puede ser nulo o vacío");
        }
        
        if (!accountRepository.existsById(accountId)) {
            throw new BusinessException("Cuenta no encontrada con id: " + accountId);
        }
        accountRepository.deleteById(accountId);
    }
}
