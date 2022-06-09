package org.example.spring.securitingweb.rest;


import org.example.spring.securitingweb.model.Customer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/api/v1/customers")
public class CustomerRestControllerV1 {

    private final List<Customer> customers = new ArrayList<>();

    {
        customers.add(new Customer(1L, "Eugene", "Bay"));
        customers.add(new Customer(2L, "Michael", "Bay"));
        customers.add(new Customer(3L, "Daria", "Zakurko"));
    }

    @GetMapping
    @PreAuthorize(value = "hasAuthority('read')")
    public List<Customer> getAllCustomer() {
        return customers;
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize(value = "hasAuthority('read')")
    public Customer getCustomerById(@PathVariable(value = "id") long id) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Customer " + id + " doesn't exist!"));
    }

    @PostMapping
    @PreAuthorize(value = "hasAuthority('write')")
    public String saveCustomer(@RequestBody Customer customer) {
        customers.add(customer);
        return "Saved!";
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize(value = "hasAuthority('write')")
    public String deleteCustomerById(@PathVariable(value = "id") long id) {
        customers.removeIf(customer -> customer.getId().equals(id));
        return "Deleted!";
    }
}
