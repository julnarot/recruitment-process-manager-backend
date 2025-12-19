package com.seek.rpm.customer.application.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.seek.rpm.customer.application.dto.CustomerCreateDTO;
import com.seek.rpm.customer.application.dto.CustomerDTO;
import com.seek.rpm.customer.application.dto.CustomerMetricsDTO;
import com.seek.rpm.customer.application.port.out.CustomerMetricsPort;
import com.seek.rpm.customer.application.port.out.CustomerPersistencePort;
import com.seek.rpm.customer.application.service.CustomerService;
import com.seek.rpm.customer.mapper.CustomerMapper;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerMetricsPort metricsPort; // private final CustomerRepository repository;
    private final CustomerPersistencePort persistencePort;
    private final CustomerMapper mapper;

    @Override
    public List<CustomerDTO> getAllCustomer() {
        // TODO use own port and adapter
        return repository.findAll()
        .stream()
        .map(mapper::toDTO)
        .toList();
    }
    
    @Override
    public CustomerDTO createCustomer(CustomerCreateDTO dto) {
        return mapper.toDTO(persistencePort.save(mapper.createToEntity(dto)));
    }

    @Override
    public CustomerMetricsDTO getMetrics() {
        return metricsPort.getMetrics();
    }

}
