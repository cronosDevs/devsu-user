package com.devsu.user.service;

import com.devsu.user.domain.CustomerDto;

import java.util.List;

public interface ICustomerService {


    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto updateCustomer(Long customerId, CustomerDto customerDto);

    List<CustomerDto> getCustomers();

    CustomerDto getCustomerById(Long customerId);

    void deleteCustomerById(Long customerId);
}
