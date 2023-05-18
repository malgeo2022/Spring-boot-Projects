package com.Spring_ecommerce.service.customer.Impl;

import com.Spring_ecommerce.exception.NotFoundException;
import com.Spring_ecommerce.model.Customer;
import com.Spring_ecommerce.model.CustomerComment;
import com.Spring_ecommerce.repository.CustomerCommentRepository;
import com.Spring_ecommerce.service.customer.CustomerCommentService;
import com.Spring_ecommerce.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomerCommentServiceImpl implements CustomerCommentService {

    private final CustomerCommentRepository customerCommentRepository;
    private final CustomerService customerService;

    @Override
    public CustomerComment add(CustomerComment customerComment) {
        if (customerComment.getRating() < 1 ){
            return null;
        }else {
            return customerCommentRepository.save(customerComment);
        }
    }

    @Override
    public void deleteById(int id) {
        customerCommentRepository.deleteById(id);
    }

    @Override
    public List<CustomerComment> getAll() {
        return customerCommentRepository.findAll();
    }

    @Override
    public CustomerComment getById(int id) {
        Optional<CustomerComment> customerComment = customerCommentRepository.findById(id);
        return customerComment.orElseThrow(()-> new NotFoundException("Customer comment could not be found by given id: " + id));
    }

    @Override
    public List<CustomerComment> getCustomerCommentsByCustomer(int id) {
        Optional<Customer> customer = Optional.ofNullable(customerService.getById(id));
        List<CustomerComment> responseCustomerComments = new ArrayList<>();

        if (customer.isPresent()){
            for (CustomerComment customerComment : customer.get().getCustomerComment()){
                responseCustomerComments.add(customerComment);
                return responseCustomerComments;
            }
        }
        return null;
    }
}
