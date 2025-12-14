package com.seek.rpm.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.seek.rpm.customer.domain.Customer;
import com.seek.rpm.customer.dto.CustomerMetricsDTO;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("""
                SELECT new com.seek.rpm.customer.dto.CustomerMetricsDTO(
                    AVG(c.age),
                    STDDEV_POP(c.age)
                )
                FROM Customer c
            """)
    CustomerMetricsDTO getCustomerMetrics();
}
