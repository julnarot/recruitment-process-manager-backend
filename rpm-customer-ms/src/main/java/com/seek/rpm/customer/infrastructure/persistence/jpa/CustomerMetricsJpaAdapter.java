package com.seek.rpm.customer.infrastructure.persistence.jpa;

import org.springframework.stereotype.Component;

import com.seek.rpm.customer.application.dto.CustomerMetricsDTO;
import com.seek.rpm.customer.application.port.out.CustomerMetricsPort;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class CustomerMetricsJpaAdapter implements CustomerMetricsPort{

    
     private final CustomerJpaRepository repository;

    @Override
    public CustomerMetricsDTO getMetrics() {
        Object[] result = repository.getCustomerMetrics();
        return new CustomerMetricsDTO(
            (Double) result[0],
            (Double) result[1]
        );
    }
} 