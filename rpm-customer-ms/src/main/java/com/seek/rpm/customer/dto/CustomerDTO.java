package com.seek.rpm.customer.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
    private String firstName;
    private String lastName;
    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    
    private LocalDate estimatedLifeEventDate;
}
