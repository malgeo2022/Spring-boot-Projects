package com.Spring_ecommerce.repository;

import com.Spring_ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Integer> {
    List<Category> getByCategoryName(String categoryName);

   // @Query("from category where categoryName=:categoryName")
    String findByCategoryName(String categoryName);


}
