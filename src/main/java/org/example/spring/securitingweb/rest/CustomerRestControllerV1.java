package org.example.spring.securitingweb.rest;


import org.example.spring.securitingweb.model.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/api/v1/customers")
public class CustomerRestControllerV1 {

    private final List<Customer> customers = Stream.of(
            new Customer(1L, "Eugene", "Bay"),
            new Customer(2L, "Michael", "Bay"),
            new Customer(3L, "Daria", "Zakurko")
    ).toList();

    @GetMapping
    public List<Customer> getAllCustomer() {
        return customers;
    }

    @GetMapping(value = "/{id}")
    public Customer getCustomerById(@PathVariable(value = "id") long id) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Customer " + id + " doesn't exist!"));
    }
}
