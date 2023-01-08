package com.example.customer.controller;

import com.example.customer.model.Customer;
import com.example.customer.record.CustomerRecord;
import com.example.customer.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/{customerId}")
    public Optional<Customer> getCustomer(
            @PathVariable UUID customerId
    ) {
        return customerService.getCustomerById(customerId);
    }

    @PostMapping
    public Customer postCustomer(
            @RequestBody CustomerRecord customerRecord
    ) {
        return customerService.createCustomer(customerRecord);
    }

    @PutMapping("/{customerId}")
    public Optional<Customer> putCustomer(
            @PathVariable UUID customerId,
            @RequestBody CustomerRecord customerRecord
    ) {
        return customerService.updateCustomer(customerId, customerRecord);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(
            @PathVariable UUID customerId
    ) {
        customerService.deleteCustomer(customerId);
    }
}
