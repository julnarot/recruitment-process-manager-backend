package com.seek.rpm.customer.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerJpaEntity, Long> {

    @Query("""
                SELECT
                    AVG(c.age),
                    STDDEV_POP(c.age)
                FROM CustomerJpaEntity c
            """)
    Object[] getCustomerMetrics();

}
