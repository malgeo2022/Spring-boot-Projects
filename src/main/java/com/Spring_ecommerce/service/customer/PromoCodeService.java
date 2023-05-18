package com.Spring_ecommerce.service.customer;


import com.Spring_ecommerce.requests.PromoCodeCreateRequest;
import com.Spring_ecommerce.model.PromoCode;

import java.util.List;

public interface PromoCodeService {
    String createPromoCode(PromoCodeCreateRequest promoCodeCreateRequest);
    List<PromoCode> getAll();
    PromoCode getById(Long id);
    void deleteById(Long id);
}
