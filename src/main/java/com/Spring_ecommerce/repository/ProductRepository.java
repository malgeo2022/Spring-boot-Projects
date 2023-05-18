package com.Spring_ecommerce.repository;

import com.Spring_ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> getByProductName(String productName);
    List<Product> getByProductBrand(String productBrand);
    void deleteById(int id);
}
