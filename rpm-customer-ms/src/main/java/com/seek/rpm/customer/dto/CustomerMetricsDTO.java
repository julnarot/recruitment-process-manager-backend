package com.seek.rpm.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerMetricsDTO {

    private Double averageAge;
    private Double ageStandardDeviation;

}
