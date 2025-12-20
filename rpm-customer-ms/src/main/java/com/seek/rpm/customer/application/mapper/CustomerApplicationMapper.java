package com.seek.rpm.customer.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.seek.rpm.customer.application.dto.CustomerCreateDTO;
import com.seek.rpm.customer.application.dto.CustomerDTO;
import com.seek.rpm.customer.domain.Customer;

@Mapper(componentModel = "spring")
public interface CustomerApplicationMapper {
    CustomerApplicationMapper INSTANCE = Mappers.getMapper(CustomerApplicationMapper.class);
    int LIFE_EXPECTANCY_YEARS = 80;

    @Mapping(target = "estimatedLifeEventDate", expression = "java(entity.getBirthDate().plusYears(LIFE_EXPECTANCY_YEARS))")
    CustomerDTO toDTO(Customer entity);

    @Mapping(target = "id", ignore = true)
    Customer toDomain(CustomerCreateDTO dto);
}
