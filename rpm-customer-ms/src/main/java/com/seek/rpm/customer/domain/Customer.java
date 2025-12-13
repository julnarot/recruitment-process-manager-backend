package com.seek.rpm.customer.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 100)
    // @NotEmpty(message = "FirstName is required.")
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    // @NonEmpty(message = "Lastname is required.")
    private String lastName;

    @Column(name = "age", nullable = false)
    // @Min(value = 0, message = "La edad no puede ser negativa.")
    // @Max(value = 120, message = "La edad no puede superar los 120 años.")
    private Integer age;

    @Column(name = "birth_date", nullable = false)
    // @Past(message = "La fecha de nacimiento debe ser en el pasado.")
    private LocalDate birthDate;

}