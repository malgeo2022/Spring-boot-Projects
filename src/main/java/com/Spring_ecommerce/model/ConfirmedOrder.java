package com.Spring_ecommerce.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "confirmed_order")
public class ConfirmedOrder {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @Column(name = "orderNumber")
   private int orderNumber;

   @Column(name = "productName")
   private String productName;

    @Column(name = "productBrand")
    private String productBrand;

    @Column(name = "productDetails")
    private String productDetails;

    @Column(name = "productPrice")
    private double productPrice;

    @Column(name = "productImageUrl")
    private String productImageUrl;

    @OneToOne
    private Address address;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private User user;
}
