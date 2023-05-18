package com.Spring_ecommerce.controllers;


import com.Spring_ecommerce.handler.msgHandler.EcommerceMessage;
import com.Spring_ecommerce.model.ProductComment;
import com.Spring_ecommerce.service.product.ProductCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/products/comments/")
@CrossOrigin
@RequiredArgsConstructor
public class ProductCommentController {

    @Autowired
    private final ProductCommentService productCommentService;


    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody ProductComment productComment){
        productCommentService.add(new ProductComment(productComment.getTitle(), productComment.getBody(), productComment.getRating(), new Date()));
        return ResponseEntity.ok(EcommerceMessage.PRODUCT_COMMENT_CREATED);
    }

    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id){
        productCommentService.deleteById(id);
        return ResponseEntity.ok(EcommerceMessage.PRODUCT_COMMENT_DELETED);
    }

    @GetMapping("getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(productCommentService.getAll());
    }


    @GetMapping("getById/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        return ResponseEntity.ok(productCommentService.getById(id));
    }


    @GetMapping("getCommentsByProduct/{id}")
    public ResponseEntity<?> getCommentsByProduct(@PathVariable int id){
        return ResponseEntity.ok(productCommentService.getCommentsByProduct(id));
    }









}
