package com.seek.rpm.customer.application.port.out;

import com.seek.rpm.customer.application.dto.CustomerMetricsDTO;

public interface CustomerMetricsPort {
    CustomerMetricsDTO getMetrics();
}
