package com.example.customer.service;

import com.example.customer.model.Customer;
import com.example.customer.record.CustomerRecord;
import com.example.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(UUID customerId) {
        return customerRepository.findById(customerId);
    }

    public Customer createCustomer(CustomerRecord customerRecord) {
        Customer customer = new Customer();
        customer.setName(customerRecord.name());
        customer.setEmail(customerRecord.email());
        customer.setAge(customerRecord.age());
        return customerRepository.save(customer);
    }

    public Optional<Customer> updateCustomer(UUID customerId, CustomerRecord customerRecord) {
        return customerRepository.findById(customerId).map(
                customer -> {
                    customer.setName(customerRecord.name());
                    customer.setEmail(customerRecord.email());
                    customer.setAge(customerRecord.age());
                    return customerRepository.save(customer);
                }
        );
    }

    public void deleteCustomer(UUID customerId) {
        customerRepository.deleteById(customerId);
    }

}
