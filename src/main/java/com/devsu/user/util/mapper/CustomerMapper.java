package com.devsu.user.util.mapper;

import com.devsu.user.domain.CustomerDto;
import com.devsu.user.domain.entity.Customer;
import com.devsu.user.domain.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Person customerDtoToPerson(CustomerDto customerDto);

    Customer customerDtoToCustomer(CustomerDto customerDto);

    CustomerDto customerToCustomerDto(Person person, Customer customer);

}
