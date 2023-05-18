package com.Spring_ecommerce.service.product;


import com.Spring_ecommerce.requests.ConfirmCartRequest;
import com.Spring_ecommerce.dto.createDto.ProductCreateDto;
import com.Spring_ecommerce.dto.viewDto.ProductViewDto;
import com.Spring_ecommerce.model.Cart;
import com.Spring_ecommerce.model.ConfirmedOrder;
import com.Spring_ecommerce.model.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ProductService {


    List<Product> getAll();
    Product getById(int id);
    ProductCreateDto add(ProductCreateDto productCreateDto);
    List<Product> getByProductName(String productName);
    List<Product> getByProductBrand(String productBrand);
    void deleteByiD(int id);
    void updateByProductDetails(int productId, String productDetails);
    List<Product> slice(Pageable pageable);
    List<ProductViewDto> getDto();
    Cart addToCart(int id);
    List<Cart> getCart();
    void removeFromCart(int id);
    ConfirmedOrder confirmCart(ConfirmCartRequest confirmCartRequest);
    List<ConfirmedOrder> getAllConformedOrderById();
    ConfirmedOrder getConfirmedOrderById(int id);
    ConfirmedOrder getConfirmedOrderByOrderNumber(Long orderNumber);
    Map<Integer, Object> searchByProduct(String productName);
    void addFavorite(int productId);
    int getNumberOfFavorite(int productId);
    void removeFromFavorites(int productId);
}
