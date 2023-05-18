package com.Spring_ecommerce.service.cart;


import com.Spring_ecommerce.model.Cart;

import java.util.List;

public interface CartService {

    Cart getById(int id);
    void deleteById(int id);
    List<Cart> getAll();
    void add(Cart cart);
}
