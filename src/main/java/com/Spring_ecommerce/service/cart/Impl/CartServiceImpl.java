package com.Spring_ecommerce.service.cart.Impl;

import com.Spring_ecommerce.exception.NotFoundException;
import com.Spring_ecommerce.model.Cart;
import com.Spring_ecommerce.repository.CartRepository;
import com.Spring_ecommerce.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public Cart getById(int id) {
        Optional<Cart> cart = cartRepository.findById(id);
        return cart.orElseThrow(()->new NotFoundException("Cart could not found by following id: " + id));
    }

    @Override
    public void deleteById(int id) {
        cartRepository.deleteById(id);
    }

    @Override
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    @Override
    public void add(Cart cart) {
       cartRepository.save(cart);
    }
}
