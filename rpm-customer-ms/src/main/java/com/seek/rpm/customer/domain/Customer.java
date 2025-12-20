package com.seek.rpm.customer.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {

    private Long id;

    private String firstName;

    private String lastName;

    private Integer age;

    private LocalDate birthDate;

}