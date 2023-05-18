package com.Spring_ecommerce.service.customer;

import com.Spring_ecommerce.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer add(Customer customer);
    void deleteById(int id);
    List<Customer> getAll();
    Customer getById(int id);
}
