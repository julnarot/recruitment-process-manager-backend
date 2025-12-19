package com.seek.rpm.customer.infrastructure.persistence.jpa;

import org.springframework.stereotype.Component;

import com.seek.rpm.customer.application.port.out.CustomerPersistencePort;
import com.seek.rpm.customer.domain.Customer;
import com.seek.rpm.customer.mapper.CustomerMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomerJpaAdapter implements CustomerPersistencePort {

    private final CustomerJpaRepository repository;
    private final CustomerMapper mapper;

    @Override
    public Customer save(Customer customer) {
        CustomerJpaEntity entity =
            mapper.toJpaEntity(customer);

        CustomerJpaEntity saved =
            repository.save(entity);

        return mapper.toDomain(saved);
    }
}