package com.Spring_ecommerce.controllers;


import com.Spring_ecommerce.dto.createDto.ProductCreateDto;
import com.Spring_ecommerce.dto.viewDto.ProductViewDto;
import com.Spring_ecommerce.handler.msgHandler.EcommerceMessage;
import com.Spring_ecommerce.model.Product;
import com.Spring_ecommerce.requests.CampaignCreateRequest;
import com.Spring_ecommerce.requests.ConfirmCartRequest;
import com.Spring_ecommerce.requests.PriceIncreaseRequest;
import com.Spring_ecommerce.requests.ProductDetailsUpdateRequest;
import com.Spring_ecommerce.service.product.ProductService;
import com.Spring_ecommerce.service.product.UpdateProductPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products/")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {

    private final ProductService productService;
    private final UpdateProductPriceService updateProductPriceService;


    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody @Valid ProductCreateDto productCreateDto) {
        this.productService.add(productCreateDto);
        return ResponseEntity.ok(EcommerceMessage.PRODUCT_SAVED);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return ResponseEntity.ok(productService.getById(id));
    }


    @GetMapping("getAll")
    public ResponseEntity<?> getAll() {
        final List<Product> products = productService.getAll();
        if (products.size() <= 0) {
            return ResponseEntity.ok(EcommerceMessage.PRODUCT_NOT_FOUND);
        }
        return ResponseEntity.ok(products);
    }


    @GetMapping("getByProductName/{productName}")
    public ResponseEntity<?> getByProductName(@PathVariable String productName) {
        String productsName = " ";
        List<Product> products = this.productService.getByProductName(productName);
        for (Product product : products) {
            productsName = product.getProductName();
        }
        if (!productName.equals(productName)) {
            return ResponseEntity.ok(EcommerceMessage.NOT_FOUND_THIS_NAME);
        }
        return ResponseEntity.ok(products);
    }


    @GetMapping("getProductBrand/{productBrand}")
    public List<Product> getProductBrand(@PathVariable String productBrand) {
        return this.productService.getByProductBrand(productBrand);
    }


    @GetMapping("slice")
    public ResponseEntity<List<Product>> slice(Pageable pageable) {
        final List<Product> products = this.productService.slice(pageable);
        return ResponseEntity.ok(products);
    }


    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        this.productService.deleteByiD(id);
        return ResponseEntity.ok(EcommerceMessage.PRODUCT_DELETED);
    }


    @GetMapping("getDto")
    public ResponseEntity<?> getDto() {
        List<ProductViewDto> products = this.productService.getDto();
        if (products.size() <= 0) {
            return ResponseEntity.ok(EcommerceMessage.PRODUCT_NOT_FOUND);
        } else {
            return ResponseEntity.ok(products);
        }
    }


    @PostMapping("addToCart/{id}")
    public ResponseEntity<?> addToCart(@RequestBody @PathVariable int id) {
        productService.addToCart(id);
        return ResponseEntity.ok(EcommerceMessage.ADD_TO_CART);
    }


    @GetMapping("getCart")
    public ResponseEntity<?> getCart() {
        return ResponseEntity.ok(productService.getCart());

    }


    @DeleteMapping("removeFromCart/{id}")
    public ResponseEntity<?> removeFromCart(@PathVariable int id) {
        productService.removeFromCart(id);
        return ResponseEntity.ok(EcommerceMessage.REMOVE_FROM_CART);
    }


    @GetMapping("searchByProductName/{productName}")
    public ResponseEntity<?> searchByPorduct(@PathVariable String productName) {
        Map<Integer, Object> result = productService.searchByProduct(productName);
        return ResponseEntity.ok(result);
    }


    @GetMapping("confirmCart")
    public ResponseEntity<?> confirmCart(@RequestBody ConfirmCartRequest confirmCartRequest) {
        productService.confirmCart(confirmCartRequest);
        return ResponseEntity.ok(EcommerceMessage.ITEMS_IN_THE_CART_HAVE_BEEN_PURCHASED);
    }


    @GetMapping("getConfirmedOrderById/{id}")
    public ResponseEntity<?> getConfirmedOrderById(@PathVariable int id) {
        return ResponseEntity.ok(productService.getConfirmedOrderById(id));
    }


    @GetMapping("getConfirmedOrderByOrderNumber/{orderNumber}")
    public ResponseEntity<?> getConfirmedOrderByOrderNumber(@PathVariable Long orderNumber) {
        return ResponseEntity.ok(productService.getConfirmedOrderByOrderNumber(orderNumber));
    }


    @PutMapping("createCampaign")
    public ResponseEntity<?> createCampaign(@RequestBody CampaignCreateRequest campaignCreateRequest) {
        updateProductPriceService.createCampaign(campaignCreateRequest);
        return ResponseEntity.ok("success");
    }


    @GetMapping("priceIncrease")
    public ResponseEntity<?> priceIncrease(@RequestBody PriceIncreaseRequest priceIncreaseRequest) {
        updateProductPriceService.priceIncrease(priceIncreaseRequest);
        return ResponseEntity.ok("success");
    }


    @PutMapping("update-product-details")
    public ResponseEntity<?> updateByProductDetails(@RequestBody ProductDetailsUpdateRequest updateRequest) {
        productService.updateByProductDetails(updateRequest.getProductId(), updateRequest.getProductDetails());
        return ResponseEntity.ok("success");
    }


    @PutMapping("addFavorite/{productId}")
    public ResponseEntity<?> addFavorite(@PathVariable int productId) {
        productService.addFavorite(productId);
        return ResponseEntity.ok(EcommerceMessage.ADDED_TO_FAVORITES);
    }


    @GetMapping("getNumberOfFavorite/{productId}")
    public ResponseEntity<?> getNumberOfFavorite(@PathVariable int productId) {
        return ResponseEntity.ok(productService.getNumberOfFavorite(productId));
    }


    @PutMapping("removeFromFavorite/{productId}")
    public ResponseEntity<?> removeFromFavorite(@PathVariable int productId) {
        productService.removeFromFavorites(productId);
        return ResponseEntity.ok(EcommerceMessage.REMOVE_FROM_FAVORITES);
    }

}
