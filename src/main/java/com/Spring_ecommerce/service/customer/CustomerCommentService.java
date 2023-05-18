package com.Spring_ecommerce.service.customer;

import com.Spring_ecommerce.model.CustomerComment;

import java.util.List;

public interface CustomerCommentService {

    CustomerComment add(CustomerComment customerComment);
    void deleteById(int id);
    List<CustomerComment> getAll();
    CustomerComment getById(int id);
    List<CustomerComment> getCustomerCommentsByCustomer(int id);
}
