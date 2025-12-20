package com.seek.rpm.customer.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.seek.rpm.customer.application.dto.CustomerCreateDTO;
import com.seek.rpm.customer.domain.Customer;
import com.seek.rpm.customer.infrastructure.persistence.jpa.CustomerJpaEntity;

@Mapper(componentModel = "spring")
public interface CustomerPersistenceMapper {
    CustomerPersistenceMapper INSTANCE = Mappers.getMapper(CustomerPersistenceMapper.class);

    Customer createToEntity(CustomerCreateDTO dto);

    CustomerJpaEntity toJpaEntity(Customer customer);
    Customer toDomain(CustomerJpaEntity entity);
}
