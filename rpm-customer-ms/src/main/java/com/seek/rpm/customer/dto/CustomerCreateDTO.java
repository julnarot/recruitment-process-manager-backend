package com.seek.rpm.customer.dto;

import java.time.LocalDate;

import com.seek.rpm.customer.validation.ValidCustomerAge;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ValidCustomerAge
public class CustomerCreateDTO {
    @NotNull(message = "firstName is required")
    @NotBlank(message = "firstName not be blank")
    private String firstName;

    @NotNull(message = "lastName is required")
    @NotBlank(message = "lastName not be blank")
    private String lastName;

    @NotNull(message = "age is required")
    private Integer age;

    @NotNull(message = "bithDate is required")
    private LocalDate birthDate;
}
