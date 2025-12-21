package com.seek.rpm.customer.application.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.seek.rpm.customer.application.dto.CustomerCreateDTO;
import com.seek.rpm.customer.application.dto.CustomerDTO;
import com.seek.rpm.customer.application.dto.CustomerMetricsDTO;
import com.seek.rpm.customer.application.mapper.CustomerApplicationMapper;
import com.seek.rpm.customer.application.port.out.CustomerMetricsPort;
import com.seek.rpm.customer.application.port.out.CustomerRepositoryPort;
import com.seek.rpm.customer.application.service.CustomerService;
import com.seek.rpm.customer.domain.Customer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    
    private final CustomerMetricsPort metricsPort;
    private final CustomerRepositoryPort customerPort;

    private final CustomerApplicationMapper applicationMapper;

    @Override
    public List<CustomerDTO> getAllCustomer() {
        return customerPort.findAll()
                .stream()
                .map(applicationMapper::toDTO)
                .toList();
    }

    @Override
    public CustomerDTO createCustomer(CustomerCreateDTO dto) {

        Customer customer =
            applicationMapper.toDomain(dto);

        Customer saved =
            customerPort.save(customer);

        return applicationMapper.toDTO(saved);
    }

    @Override
    public CustomerMetricsDTO getMetrics() {
        return metricsPort.getMetrics();
    }

}
