package com.Spring_ecommerce.service.product.Impl;

import com.Spring_ecommerce.model.Product;
import com.Spring_ecommerce.model.ProductComment;
import com.Spring_ecommerce.repository.ProductCommentRepository;
import com.Spring_ecommerce.service.product.ProductCommentService;
import com.Spring_ecommerce.service.product.ProductService;
import com.sun.jdi.PrimitiveValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductCommentServiceImpl implements ProductCommentService {

    private final ProductCommentRepository productCommentRepository;
    private final ProductService productService;

    @Override
    public ProductComment add(ProductComment productComment) {
        if (productComment.getRating() < 1 ){
            return null;
        }else {
            return productCommentRepository.save(productComment);
        }

    }

    @Override
    public void deleteById(int id) {
      productCommentRepository.deleteById(id);
    }

    @Override
    public ProductComment getById(int id) {
        Optional<ProductComment> productComment = productCommentRepository.findById(id);
        return productComment.orElse(null);
    }

    @Override
    public List<ProductComment> getAll() {
        return productCommentRepository.findAll();
    }

    @Override
    public List<ProductComment> getCommentsByProduct(int id) {
        Product product = productService.getById(id);
        List<ProductComment> responseComment = new ArrayList<>();
        for (ProductComment productComment : product.getProductComment()){
            responseComment.add(productComment);
            return responseComment;
        }
        return null;
    }
}
