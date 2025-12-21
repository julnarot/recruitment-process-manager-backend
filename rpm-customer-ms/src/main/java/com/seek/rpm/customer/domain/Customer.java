package com.seek.rpm.customer.domain;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class Customer {

    private Long id;

    private String firstName;

    private String lastName;

    private Integer age;

    private LocalDate birthDate;

}