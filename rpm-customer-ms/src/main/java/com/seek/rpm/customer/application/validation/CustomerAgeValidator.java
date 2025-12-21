package com.seek.rpm.customer.application.validation;

import java.time.LocalDate;
import java.time.Period;

import com.seek.rpm.customer.application.dto.CustomerCreateDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomerAgeValidator
        implements ConstraintValidator<ValidCustomerAge, CustomerCreateDTO> {

    @Override
    public boolean isValid(CustomerCreateDTO dto, ConstraintValidatorContext context) {

        if (dto == null) {
            return true;
        }

        Integer age = dto.getAge();
        LocalDate birthDate = dto.getBirthDate();

        if (age == null || birthDate == null) {
            return true;
        }

        if (age <= 0) {
            addViolation(context, "age", "age must be greater than 0");
            return false;
        }

        if (birthDate.isAfter(LocalDate.now())) {
            addViolation(context, "birthDate", "birthDate cannot be in the future");
            return false;
        }

        int calculatedAge = Period.between(birthDate, LocalDate.now()).getYears();

        if (!calculatedAgeEquals(age, calculatedAge)) {
            addViolation(context, "age",
                    "age does not match birthDate");
            return false;
        }

        return true;
    }

    private boolean calculatedAgeEquals(Integer provided, int calculated) {
        return provided == calculated || provided == calculated + 1;
    }

    private void addViolation(
            ConstraintValidatorContext context,
            String field,
            String message
    ) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(field)
                .addConstraintViolation();
    }
}
