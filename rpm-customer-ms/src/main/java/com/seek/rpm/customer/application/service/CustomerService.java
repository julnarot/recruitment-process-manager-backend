package com.seek.rpm.customer.application.service;

import java.util.List;

import com.seek.rpm.customer.application.dto.CustomerCreateDTO;
import com.seek.rpm.customer.application.dto.CustomerDTO;
import com.seek.rpm.customer.application.dto.CustomerMetricsDTO;

public interface CustomerService {

    List<CustomerDTO> getAllCustomer();

    CustomerDTO createCustomer(CustomerCreateDTO dto);

    CustomerMetricsDTO getMetrics();

}
