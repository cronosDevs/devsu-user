package com.devsu.user.service;

import com.devsu.user.domain.CustomerDto;
import com.devsu.user.domain.entity.Customer;
import com.devsu.user.domain.entity.Person;
import com.devsu.user.repository.customer.impl.ICustomerRepositoryFacade;
import com.devsu.user.repository.person.impl.IPersonRepositoryFacade;
import com.devsu.user.util.Utils;
import com.devsu.user.util.exception.domain.BadRequestException;
import com.devsu.user.util.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {

    private final IPersonRepositoryFacade personRepositoryFacade;
    private final ICustomerRepositoryFacade customerRepositoryFacade;

    @Autowired
    public CustomerService(IPersonRepositoryFacade personRepositoryFacade, ICustomerRepositoryFacade customerRepositoryFacade) {
        this.personRepositoryFacade = personRepositoryFacade;
        this.customerRepositoryFacade = customerRepositoryFacade;
    }

    @Override
    @Transactional
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Person person = CustomerMapper.INSTANCE.customerDtoToPerson(customerDto);
        person = personRepositoryFacade.createPerson(person);

        Customer customer = CustomerMapper.INSTANCE.customerDtoToCustomer(customerDto);
        customer.setPerson(person);
        customer = customerRepositoryFacade.createCustomer(customer);
        return CustomerMapper.INSTANCE.customerToCustomerDto(person, customer);
    }

    @Override
    public CustomerDto updateCustomer(Long customerId, CustomerDto customerDto) {
        Optional<Customer> customerOptional = customerRepositoryFacade.getCustomerById(customerId);
        return customerOptional.map(customer -> {
            Utils.mergeBeanObjects(customer, customerDto);
            Customer customerResponse = customerRepositoryFacade.updateCustomer(customer);
            return CustomerMapper.INSTANCE.customerToCustomerDto(customerResponse.getPerson(), customerResponse);
        }).orElseThrow(() -> new BadRequestException("Customer not found", null));
    }

    @Override
    public List<CustomerDto> getCustomers() {
        return customerRepositoryFacade.getAllCustomers().stream()
                .map(customer -> CustomerMapper.INSTANCE.customerToCustomerDto(customer.getPerson(), customer))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(Long customerId) {
        Optional<Customer> customerOptional = customerRepositoryFacade.getCustomerById(customerId);
        return customerOptional.map(customer -> CustomerMapper.INSTANCE.customerToCustomerDto(customer.getPerson(), customer)).orElse(null);
    }

    @Override
    public void deleteCustomerById(Long customerId) {
        Optional<Customer> customerOptional = customerRepositoryFacade.getCustomerById(customerId);
        customerOptional.map(customer -> {
            customer.setState(false);
            return customerRepositoryFacade.updateCustomer(customer);
        }).orElseThrow(() -> new BadRequestException("Customer not found", null));
    }
}
