package com.seek.rpm.customer.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.seek.rpm.customer.dto.CustomerCreateDTO;
import com.seek.rpm.customer.dto.CustomerDTO;
import com.seek.rpm.customer.mapper.CustomerMapper;
import com.seek.rpm.customer.repository.CustomerRepository;
import com.seek.rpm.customer.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    @Override
    public List<CustomerDTO> getAllCustomer() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public CustomerDTO createCustomer(CustomerCreateDTO dto) {
        return mapper.toDTO(repository.save(mapper.createToEntity(dto)));
    }

}
