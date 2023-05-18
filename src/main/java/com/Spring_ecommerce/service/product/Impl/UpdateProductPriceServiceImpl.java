package com.Spring_ecommerce.service.product.Impl;

import com.Spring_ecommerce.requests.CampaignCreateRequest;
import com.Spring_ecommerce.requests.PriceIncreaseRequest;
import com.Spring_ecommerce.model.Product;
import com.Spring_ecommerce.model.User;
import com.Spring_ecommerce.repository.ProductRepository;
import com.Spring_ecommerce.service.customer.SendEmailService;
import com.Spring_ecommerce.service.product.UpdateProductPriceService;
import com.Spring_ecommerce.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UpdateProductPriceServiceImpl implements UpdateProductPriceService {

    private final ProductRepository productRepository;
    private final SendEmailService sendEmailService;
    private final UserService userService;

    @Override
    public void createCampaign(CampaignCreateRequest campaignCreateRequest) {
        Optional<Product> product = productRepository.findById(campaignCreateRequest.getProductId());
        List<User> users = userService.getAll();
        if (product.isPresent()){
            product.get().setProductPrice(product.get().getProductPrice() - campaignCreateRequest.getDiscountAmount());
            productRepository.save(product.get());

            for (User user: users){
                if (user.isNotificationPermission()){
                    sendEmailService.sendEmails(user.getEmail(), "Big Discount", product.get().getProductBrand() + " " + product.get().getProductName()
                    + "Big Discount on Product!");
                }
            }
        }
    }

    @Override
    public void priceIncrease(PriceIncreaseRequest priceIncreaseRequest) {
            Optional<Product> product = productRepository.findById(priceIncreaseRequest.getProductId());

            if (product.isPresent()){
                product.get().setProductPrice(product.get().getProductPrice() + priceIncreaseRequest.getAmount());
                productRepository.save(product.get());
            }
    }
}
