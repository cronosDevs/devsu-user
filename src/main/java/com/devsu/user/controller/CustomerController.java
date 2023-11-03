package com.devsu.user.controller;

import com.devsu.user.domain.CustomerDto;
import com.devsu.user.service.ICustomerService;
import com.devsu.user.util.endpoint.CustomerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = CustomerEndpoint.BASE_PATH)
@CrossOrigin(
        origins = "*",
        allowedHeaders = "*",
        methods = {
                RequestMethod.GET,
                RequestMethod.DELETE,
                RequestMethod.PATCH,
                RequestMethod.POST,
                RequestMethod.PUT,
                RequestMethod.OPTIONS
        })
public class CustomerController {

    private final ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(CustomerEndpoint.CUSTOMERS)
    public ResponseEntity<CustomerDto>
    createCustomer(@Valid @RequestBody CustomerDto customerDto) throws IllegalAccessException {
        CustomerDto response = customerService.createCustomer(customerDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(CustomerEndpoint.CUSTOMERS_BY_ID)
    public ResponseEntity<CustomerDto>
    updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDto customerDto) throws IllegalAccessException {
        CustomerDto response = customerService.updateCustomer(customerId, customerDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(CustomerEndpoint.CUSTOMERS)
    public ResponseEntity<List<CustomerDto>> getCustomers() throws IllegalAccessException {
        List<CustomerDto> customerDtoList = customerService.getCustomers();
        return new ResponseEntity<>(customerDtoList, HttpStatus.OK);

    }

    @GetMapping(CustomerEndpoint.CUSTOMERS_BY_ID)
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long customerId) throws IllegalAccessException {
        CustomerDto response = customerService.getCustomerById(customerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(CustomerEndpoint.CUSTOMERS_BY_ID)
    public ResponseEntity<CustomerDto> deleteCustomerById(@PathVariable Long customerId) throws IllegalAccessException {
        customerService.deleteCustomerById(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
