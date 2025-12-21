package com.seek.rpm.customer.application.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.seek.rpm.customer.application.dto.CustomerCreateDTO;
import com.seek.rpm.customer.application.dto.CustomerDTO;
import com.seek.rpm.customer.application.mapper.CustomerApplicationMapper;
import com.seek.rpm.customer.application.port.out.CustomerRepositoryPort;
import com.seek.rpm.customer.application.service.impl.CustomerServiceImpl;
import com.seek.rpm.customer.domain.Customer;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepositoryPort customerRepositoryPort;

    @Mock
    private CustomerApplicationMapper applicationMapper;

    @InjectMocks
    private CustomerServiceImpl service;

    @Test
    void shouldCreateCustomerSuccessfully() {

        CustomerCreateDTO dto = new CustomerCreateDTO();

        Customer domain = new Customer();
        Customer saved = new Customer();

        CustomerDTO response = new CustomerDTO();

        when(applicationMapper.toDomain(dto)).thenReturn(domain);
        when(customerRepositoryPort.save(domain)).thenReturn(saved);
        when(applicationMapper.toDTO(saved)).thenReturn(response);

        CustomerDTO result = service.createCustomer(dto);

        assertNotNull(result);
        verify(customerRepositoryPort).save(domain);
    }
}
