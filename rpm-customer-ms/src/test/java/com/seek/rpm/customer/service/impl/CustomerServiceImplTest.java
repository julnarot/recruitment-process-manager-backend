package com.seek.rpm.customer.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.seek.rpm.customer.domain.Customer;
import com.seek.rpm.customer.dto.CustomerCreateDTO;
import com.seek.rpm.customer.dto.CustomerDTO;
import com.seek.rpm.customer.mapper.CustomerMapper;
import com.seek.rpm.customer.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository repository;

    @Mock
    private CustomerMapper mapper;

    @InjectMocks
    private CustomerServiceImpl service;

    @Test
    void shouldCreateCustomerSuccessfully() {
        CustomerCreateDTO dto = new CustomerCreateDTO();
        dto.setFirstName("Juan");
        dto.setAge(30);
        dto.setBirthDate(LocalDate.now().minusYears(30));

        Customer entity = new Customer();
        Customer saved = new Customer();
        saved.setId(1L);

        CustomerDTO response = new CustomerDTO();
        response.setFirstName("Juan");

        when(mapper.createToEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(saved);
        when(mapper.toDTO(saved)).thenReturn(response);

        CustomerDTO result = service.createCustomer(dto);

        assertNotNull(result);
        assertEquals(response.getFirstName(), "Juan");

        verify(repository).save(entity);
    }
}
