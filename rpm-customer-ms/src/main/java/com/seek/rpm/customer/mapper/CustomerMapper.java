package com.seek.rpm.customer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.seek.rpm.customer.domain.Customer;
import com.seek.rpm.customer.dto.CustomerCreateDTO;
import com.seek.rpm.customer.dto.CustomerDTO;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO toDTO(Customer entity);

    @Mapping(target = "id", ignore = true)
    Customer createToEntity(CustomerCreateDTO dto);
}
