package com.devsu.user;

import com.devsu.user.domain.CustomerDto;
import com.devsu.user.domain.entity.Customer;
import com.devsu.user.domain.entity.Person;
import com.devsu.user.repository.customer.impl.ICustomerRepositoryFacade;
import com.devsu.user.repository.person.impl.IPersonRepositoryFacade;
import com.devsu.user.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    @Mock
    private IPersonRepositoryFacade personRepositoryFacade;

    @Mock
    private ICustomerRepositoryFacade customerRepositoryFacade;

    private CustomerService customerService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerService(personRepositoryFacade, customerRepositoryFacade);
    }

    @Test
    public void testCreateCustomer() {
        CustomerDto customerDto = new CustomerDto();
        Person person = new Person();
        when(personRepositoryFacade.createPerson(person)).thenReturn(person);
        Customer customer = new Customer();
        customer.setPerson(person);
        when(customerRepositoryFacade.createCustomer(customer)).thenReturn(customer);
        CustomerDto result = customerService.createCustomer(customerDto);
        assertEquals(customerDto.getCustomerId(), result.getCustomerId());
    }

    @Test
    public void testUpdateCustomer() {
        Long customerId = 1L;
        CustomerDto customerDto = new CustomerDto();
        Customer customer = new Customer();
        when(customerRepositoryFacade.getCustomerById(customerId)).thenReturn(Optional.of(customer));
        when(customerRepositoryFacade.updateCustomer(customer)).thenReturn(customer);
        CustomerDto result = customerService.updateCustomer(customerId, customerDto);
        assertEquals(customerDto, result);
    }

    @Test
    public void testGetCustomers() {
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        when(customerRepositoryFacade.getAllCustomers()).thenReturn(Arrays.asList(customer1, customer2));
        List<CustomerDto> result = customerService.getCustomers();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetCustomerById() {
        Long customerId = 1L;
        CustomerDto customer = new CustomerDto(1L,"test",true);
        when(customerRepositoryFacade.getCustomerById(customerId)).thenReturn(Optional.of(new Customer(1L,"test",true, new Person())));
        CustomerDto result = customerService.getCustomerById(customerId);
        assertEquals(customer.getCustomerId(), result.getCustomerId());
        assertEquals(customer.getPassword(), result.getPassword());
        assertEquals(customer.getState(), result.getState());
    }

    @Test
    public void testDeleteCustomerById() {
        Long customerId = 1L;
        Customer customer = new Customer();
        when(customerRepositoryFacade.getCustomerById(customerId)).thenReturn(Optional.of(customer));
        when(customerRepositoryFacade.updateCustomer(customer)).thenReturn(customer);
        customerService.deleteCustomerById(customerId);
        verify(customerRepositoryFacade).updateCustomer(customer);
    }
}