package com.Spring_ecommerce.controllers;


import com.Spring_ecommerce.handler.msgHandler.EcommerceMessage;
import com.Spring_ecommerce.model.Customer;
import com.Spring_ecommerce.requests.CustomerCreateRequest;
import com.Spring_ecommerce.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer/")
@CrossOrigin
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("getAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(customerService.getAll());
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@Valid @RequestBody CustomerCreateRequest customerCreateRequest){
        customerService.add(new Customer(customerCreateRequest.getName(),  customerCreateRequest.getProfilePictureUrl(), customerCreateRequest.getEmail(), new Date()));
        return ResponseEntity.ok(EcommerceMessage.SELLER_SAVED);
    }


    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id){
        customerService.deleteById(id);
        return ResponseEntity.ok(EcommerceMessage.SELLER_DELETED);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        return ResponseEntity.ok(customerService.getById(id));
    }

}