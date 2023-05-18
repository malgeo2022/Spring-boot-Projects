package com.Spring_ecommerce.service.category.Impl;

import com.Spring_ecommerce.model.Category;
import com.Spring_ecommerce.repository.CategoryRepository;
import com.Spring_ecommerce.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @Nullable
    private final CategoryRepository categoryRepository;

    @Override
    public Category add(Category category) {
        if (categoryRepository.findByCategoryName(category.getCategoryName()) !=null){
            return null;
        }
        this.categoryRepository.save(category);
        return category;
    }

    @Override
    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public List<Category> getByCategoryName(String categoryName) {
        return this.categoryRepository.getByCategoryName(categoryName);
    }
}
