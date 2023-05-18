package com.Spring_ecommerce.service.product;

import com.Spring_ecommerce.model.ProductComment;

import java.util.List;

public interface ProductCommentService {

    ProductComment add(ProductComment productComment);
    void deleteById(int id);
    ProductComment getById(int id);
    List<ProductComment> getAll();
    List<ProductComment> getCommentsByProduct(int id);
}
