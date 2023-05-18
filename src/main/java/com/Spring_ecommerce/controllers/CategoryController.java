package com.Spring_ecommerce.controllers;


import com.Spring_ecommerce.handler.msgHandler.EcommerceMessage;
import com.Spring_ecommerce.model.Category;
import com.Spring_ecommerce.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category/")
@RequiredArgsConstructor
@CrossOrigin
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping("getAll")
  public List<Category> getAll(){
      return this.categoryService.getAll();
  }


  @PostMapping("add")
  public ResponseEntity<?> add(@RequestBody Category category){
      this.categoryService.add(category);
      return ResponseEntity.ok(EcommerceMessage.CATEGORY_NAME_ALREADY_IN_USE);
  }


  @GetMapping("getByCategoryName")
  public List<Category> getByCategoryName(String categoryName){
      return this.categoryService.getByCategoryName(categoryName);
  }





}
