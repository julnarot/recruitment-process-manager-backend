package com.seek.rpm.customer.service;

import java.util.List;

import com.seek.rpm.customer.dto.CustomerCreateDTO;
import com.seek.rpm.customer.dto.CustomerDTO;
import com.seek.rpm.customer.dto.CustomerMetricsDTO;

public interface CustomerService {

    List<CustomerDTO> getAllCustomer();

    CustomerDTO createCustomer(CustomerCreateDTO dto);

    CustomerMetricsDTO getMetrics();

}
