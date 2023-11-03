package com.devsu.user.repository.customer.impl;

import com.devsu.user.domain.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepositoryFacade {
    Customer createCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Optional<Customer> getCustomerById(Long customerId);
    Customer updateCustomer(Customer customer);
}
