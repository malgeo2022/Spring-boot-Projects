package com.Spring_ecommerce.service.customer.Impl;

import com.Spring_ecommerce.exception.NotFoundException;
import com.Spring_ecommerce.model.Customer;
import com.Spring_ecommerce.repository.CustomerRepository;
import com.Spring_ecommerce.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer add(Customer customer) {
        if (customer.getName().length() < 1 ){
            return null;
        }else {
            return customerRepository.save(customer);
        }

    }

    @Override
    public void deleteById(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getById(int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElseThrow(()-> new NotFoundException("Customer could not be found by following id: " + id));
    }
}
