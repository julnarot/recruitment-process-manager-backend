package com.seek.rpm.customer.application.port.out;

import java.util.List;

import com.seek.rpm.customer.domain.Customer;

public interface CustomerRepositoryPort {

    Customer save(Customer customer);

    List<Customer> findAll();

}
