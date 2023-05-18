package com.Spring_ecommerce.controllers;


import com.Spring_ecommerce.handler.msgHandler.EcommerceMessage;
import com.Spring_ecommerce.model.CustomerComment;
import com.Spring_ecommerce.requests.CustomerCommentCreateRequest;
import com.Spring_ecommerce.service.customer.CustomerCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/customer/comments/")
@RequiredArgsConstructor
@CrossOrigin
public class CustomerCommentController {

    private final CustomerCommentService customerCommentService;


    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody CustomerCommentCreateRequest customerCommentCreateRequest){
        customerCommentService.add(new CustomerComment(customerCommentCreateRequest.getTitle(), customerCommentCreateRequest.getBody(), customerCommentCreateRequest.getRating(), new Date()));
        return ResponseEntity.ok(EcommerceMessage.SELLER_COMMENT_CREATED);
    }

    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id){
        customerCommentService.deleteById(id);
        return ResponseEntity.ok(EcommerceMessage.SELLER_COMMENT_DELETED);
    }

    @GetMapping("getAll")
    public  ResponseEntity<?> getAll(){
        return ResponseEntity.ok(customerCommentService.getAll());
    }

    @GetMapping("getCustomerCommentById/{id}")
    public ResponseEntity<?> getCustomerCommentById(@PathVariable int id){
        return ResponseEntity.ok(customerCommentService.getById(id));
    }
}
