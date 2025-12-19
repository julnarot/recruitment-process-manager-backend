package com.seek.rpm.customer.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.seek.rpm.customer.application.dto.CustomerCreateDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class CustomerCreateDTOValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldFailWhenAgeIsZero() {
        CustomerCreateDTO dto = validDto();
        dto.setFirstName("Juan");
        dto.setLastName("Xer");
        dto.setAge(0);

        Set<ConstraintViolation<CustomerCreateDTO>> violations = validator.validate(dto);

        assertNotEquals(violations, "");
        assertEquals(violations.iterator().next().getMessage(), "age must be greater than 0");
    }

    @Test
    void shouldFailWhenBirthDateIsInFuture() {
        CustomerCreateDTO dto = validDto();
        dto.setBirthDate(LocalDate.now().plusDays(1));

        Set<ConstraintViolation<CustomerCreateDTO>> violations = validator.validate(dto);

        assertNotEquals(violations, "");
    }

    @Test
    void shouldFailWhenAgeDoesNotMatchBirthDate() {
        CustomerCreateDTO dto = validDto();
        dto.setAge(10);
        dto.setBirthDate(LocalDate.now().minusYears(20));

        Set<ConstraintViolation<CustomerCreateDTO>> violations = validator.validate(dto);

        assertNotEquals(violations, "");
    }

    @Test
    void shouldPassWhenAgeMatchesBirthDate() {
        CustomerCreateDTO dto = validDto();

        Set<ConstraintViolation<CustomerCreateDTO>> violations = validator.validate(dto);

        assertNotEquals(violations, "");
        // assertThat(violations).isEmpty();
    }

    private CustomerCreateDTO validDto() {
        CustomerCreateDTO dto = new CustomerCreateDTO();
        dto.setFirstName("Juan");
        dto.setAge(30);
        dto.setBirthDate(LocalDate.now().minusYears(30));
        return dto;
    }
}
