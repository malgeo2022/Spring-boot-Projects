package com.Spring_ecommerce.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_brand")
    private String productBrand;

    @Column(name = "product_details")
    private String productDetails;

    @Min(value = 1,message = "put product price mandatory")
    @Column(name = "product_price")
    private double productPrice;

    @Min(value = 1,message = "Quantity stock mandatory")
    @Column(name = "stock")
    private int stock;

    @Column(name = "image")
    private String productImageUrl;

    @Min(value = 0)
    @Column(name = "favoriteNumber")
    private int favoriteNumber = 0;

    @ManyToOne
    private Customer customer;

    @OneToMany
    private List<ProductComment> productComment;

    @ManyToOne
    private Category category;

    public Product(String productName,String productBrand, String productDetails, double productPrice, int stock, String productImageUrl){
        this.productName=productName;
        this.productBrand=productBrand;
        this.productDetails=productDetails;
        this.productPrice=productPrice;
        this.stock=stock;
        this.productImageUrl = productImageUrl;
    }


}
