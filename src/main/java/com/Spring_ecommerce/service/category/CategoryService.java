package com.Spring_ecommerce.service.category;

import com.Spring_ecommerce.model.Category;

import java.util.List;

public interface CategoryService {

    Category add(Category category);
    List<Category> getAll();
    List<Category> getByCategoryName(String categoryName);
}
