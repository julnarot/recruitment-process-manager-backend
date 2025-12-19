package com.seek.rpm.customer.application.port.out;

import com.seek.rpm.customer.domain.Customer;

public interface CustomerPersistencePort {

    Customer save(Customer customer);

}
