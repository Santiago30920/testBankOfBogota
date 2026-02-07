package com.bancobogota.bankbogota.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bancobogota.bankbogota.dto.CustomerRequest;
import com.bancobogota.bankbogota.exception.BusinessException;
import com.bancobogota.bankbogota.model.Customer;
import com.bancobogota.bankbogota.repository.CustomerRepository;

/**
 * Servicio para la gestión de clientes.
 * Contiene la lógica de negocio para crear y consultar clientes.
 */
@Service
public class CustomerService {
    
    private final CustomerRepository customerRepository;

    /**
     * Constructor del servicio.
     * @param customerRepository Repositorio de clientes
     */
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Crea un nuevo cliente con validaciones de negocio.
     * @param request Datos del cliente a crear
     * @return Cliente creado
     * @throws BusinessException si el documento o email ya existe
     */
    public Customer createCustomer(CustomerRequest request) {
        // Validar que no exista cliente con el mismo documento
        if (customerRepository.findByDocumentNumber(request.getDocumentNumber()).isPresent()) {
            throw new BusinessException("Ya existe un cliente con el número de documento: " + request.getDocumentNumber());
        }
        
        // Validar que no exista cliente con el mismo email
        if (customerRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BusinessException("Ya existe un cliente con el email: " + request.getEmail());
        }

        Customer customer = new Customer();
        customer.setDocumentType(request.getDocumentType());
        customer.setDocumentNumber(request.getDocumentNumber());
        customer.setFullName(request.getFullName());
        customer.setEmail(request.getEmail());

        return customerRepository.save(customer);
    }

    /**
     * Obtiene todos los clientes registrados.
     * @return Lista de clientes
     */
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * Busca un cliente por su ID.
     * @param id ID del cliente
     * @return Cliente encontrado
     * @throws BusinessException si el cliente no existe
     */
    public Customer getCustomerById(String id) {
        if (id == null || id.isEmpty()) {
            throw new BusinessException("El id del cliente no puede ser nulo o vacío");
        }
        return customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente no encontrado con id: " + id));
    }
}
