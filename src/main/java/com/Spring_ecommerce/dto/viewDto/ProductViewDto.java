package com.Spring_ecommerce.dto.viewDto;


import com.Spring_ecommerce.model.Product;
import lombok.Getter;

@Getter
public class ProductViewDto {

    private final String productName;
    private final String productBrand;
    private final String productDetails;
    private final double productPrice;
    private final String productImageUrl;

    private ProductViewDto(String productName, String productBrand, String productDetails, double productPrice, String productImageUrl) {
        this.productName = productName;
        this.productBrand = productBrand;
        this.productDetails = productDetails;
        this.productPrice = productPrice;
        this.productImageUrl = productImageUrl;
    }

    public static ProductViewDto of(Product product) {
        return new ProductViewDto(product.getProductName(), product.getProductBrand(),
                product.getProductDetails(), product.getProductPrice(), product.getProductImageUrl());
    }



}
