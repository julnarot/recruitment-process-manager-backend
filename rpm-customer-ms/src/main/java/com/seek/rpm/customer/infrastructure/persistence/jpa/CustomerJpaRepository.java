package com.seek.rpm.customer.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.seek.rpm.customer.domain.Customer;
// import com.seek.rpm.customer.dto.CustomerMetricsDTO;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {

    @Query("""
                SELECT
                    AVG(c.age),
                    STDDEV_POP(c.age)
                FROM Customer c
            """)
    Object[] getCustomerMetrics();

}
