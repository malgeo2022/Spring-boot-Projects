package com.Spring_ecommerce.service.product.Impl;

import com.Spring_ecommerce.requests.ConfirmCartRequest;
import com.Spring_ecommerce.dto.createDto.ProductCreateDto;
import com.Spring_ecommerce.dto.viewDto.ProductViewDto;
import com.Spring_ecommerce.exception.NotFoundException;
import com.Spring_ecommerce.model.*;
import com.Spring_ecommerce.repository.ConfirmedOrderRepository;
import com.Spring_ecommerce.repository.ProductRepository;
import com.Spring_ecommerce.repository.PromoCodeRepository;
import com.Spring_ecommerce.service.cart.CartService;
import com.Spring_ecommerce.service.customer.CreditCardService;
import com.Spring_ecommerce.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CartService cartService;
    private final CreditCardService creditCardService;
    private final ConfirmedOrderRepository confirmedOrderRepository;
    private final PromoCodeRepository promoCodeRepository;
    //

    @Override
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getById(int id) {
        Product product = productRepository.findById(id).orElseThrow(()->new NotFoundException("Product with the given id could not be found :" + id));
        return product;
    }

    @Override
    public ProductCreateDto add(ProductCreateDto productCreateDto) {
        this.productRepository.save(new Product(productCreateDto.getProductName(), productCreateDto.getProductBrand(),
                productCreateDto.getProductDetails(), productCreateDto.getProductPrice(), productCreateDto.getStock(), productCreateDto.getProductImageUrl()));
        return productCreateDto;
    }

    @Override
    public List<Product> getByProductName(String productName) {
        return this.productRepository.getByProductName(productName);
    }

    @Override
    public List<Product> getByProductBrand(String productBrand) {
        return this.productRepository.getByProductBrand(productBrand);
    }

    @Override
    public void deleteByiD(int id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public void updateByProductDetails(int productId, String productDetails) {
        Optional<Product> product = productRepository.findById(productId);

        product.ifPresent(value -> value.setProductDetails(productDetails) );
        productRepository.save(product.get());
    }

    @Override
    public List<Product> slice(Pageable pageable) {
        final List<Product> products = this.productRepository.findAll(pageable).stream().collect(Collectors.toList());
        return products;
    }

    @Override
    public List<ProductViewDto> getDto() {
        final List<ProductViewDto> products = this.productRepository.findAll().stream().map(ProductViewDto::of).collect(Collectors.toList());
        return products;
    }

    @Override
    public Cart addToCart(int id) {
        Product product = productRepository.getById(id);
        Cart cart = new Cart();
        cart.setId(product.getId());
        cart.setProductBrand(product.getProductBrand());
        cart.setProductName(product.getProductName());
        cart.setProductDetails(product.getProductDetails());
        cart.setProductPrice(product.getProductPrice());
        cart.setProductImageUrl(product.getProductImageUrl());
        cart.setCustomer(product.getCustomer());
        cart.setQuantity(0);

        cart.setQuantity(cart.getQuantity() + 1 );
        product.setStock(product.getStock() - 1 );

        if (product.getStock() == 0){
            productRepository.deleteById(product.getId());
        }
        cartService.add(cart);
        return cart;
    }

    @Override
    public List<Cart> getCart() {
        return cartService.getAll();
    }

    @Override
    public void removeFromCart(int id) {
        Cart cart = cartService.getById(id);
        cartService.deleteById(cart.getId());
    }

    @Override
    public ConfirmedOrder confirmCart(ConfirmCartRequest confirmCartRequest) {
        Optional<Cart> cart = Optional.ofNullable(cartService.getById(confirmCartRequest.getId()));

        if (cart.isPresent()){
            Optional<PromoCode> code = promoCodeRepository.findPromoCodeByCode(confirmCartRequest.getPromoCode());
            ConfirmedOrder confirmedOrder = new ConfirmedOrder();
            confirmedOrder.setProductBrand(cart.get().getProductBrand());
            confirmedOrder.setProductDetails(cart.get().getProductDetails());
            confirmedOrder.setProductName(cart.get().getProductName());
            confirmedOrder.setProductImageUrl(cart.get().getProductImageUrl());
            confirmedOrder.setCustomer(cart.get().getCustomer());

            if (code.isPresent()){
                confirmedOrder.setProductPrice(cart.get().getProductPrice() - code.get().getAmount());
                promoCodeRepository.deleteById(code.get().getId());
            }else {
                confirmedOrder.setProductPrice(cart.get().getProductPrice());
            }

            creditCardService.add(new CreditCard(confirmCartRequest.getCardNumber(), confirmCartRequest.getCvv(),
                    confirmCartRequest.getNameAndSurname(),confirmCartRequest.getExpirationDate()));
            confirmedOrderRepository.save(confirmedOrder);
            cartService.deleteById(cart.get().getId());

            return confirmedOrder;
        }
        return null;
    }

    @Override
    public List<ConfirmedOrder> getAllConformedOrderById() {
        return confirmedOrderRepository.findAll();
    }

    @Override
    public ConfirmedOrder getConfirmedOrderById(int id) {
        return confirmedOrderRepository.findById(id).orElseThrow(()-> new NotFoundException("confirmed order isn't present!" + id));
    }

    @Override
    public ConfirmedOrder getConfirmedOrderByOrderNumber(Long orderNumber) {
        return confirmedOrderRepository.findConfirmedOrderByOrderNumber(orderNumber);
    }

    @Override
    public Map<Integer, Object> searchByProduct(String productName) {
        Map<Integer, Object> searchResult = new HashMap<>();
        List<Product> products = new ArrayList<>();

        for (Product product : productRepository.findAll()){
            if (product.getProductName().contains(productName)){
                products.add(product);
                searchResult.put(products.size(), products);
                return searchResult;
            }
        }
        return null;
    }

    @Override
    public void addFavorite(int productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()){
            product.get().setFavoriteNumber(product.get().getFavoriteNumber() + 1);
            productRepository.save(product.get());
        }else{
            throw new NotFoundException("product could not be found with the given id: " + productId);
        }
    }

    @Override
    public int getNumberOfFavorite(int productId) {
        return productRepository.findById(productId).get().getFavoriteNumber();
    }

    @Override
    public void removeFromFavorites(int productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()){
            product.get().setFavoriteNumber(product.get().getFavoriteNumber() - 1);
            productRepository.save(product.get());
        }else {
            throw new NotFoundException("product could not be found with the given id: " + productId);
        }

    }
}
