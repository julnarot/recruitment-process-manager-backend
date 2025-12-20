package com.seek.rpm.customer.infrastructure.persistence.jpa;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.seek.rpm.customer.application.mapper.CustomerApplicationMapper;
import com.seek.rpm.customer.application.port.out.CustomerRepositoryPort;
import com.seek.rpm.customer.domain.Customer;
import com.seek.rpm.customer.infrastructure.persistence.mapper.CustomerPersistenceMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomerJpaAdapter implements CustomerRepositoryPort {

    private final CustomerJpaRepository repository;
    private final CustomerPersistenceMapper persistenceMapper;
    private final CustomerApplicationMapper applicationMapper;

    @Override
    public Customer save(Customer customer) {
        CustomerJpaEntity entity = persistenceMapper.toJpaEntity(customer);

        CustomerJpaEntity saved = repository.save(entity);

        return applicationMapper.toDomain(saved);
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll().stream().map(applicationMapper::toDomain).collect(Collectors.toList());
    }
}