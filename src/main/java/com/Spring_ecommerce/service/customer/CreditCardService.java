package com.Spring_ecommerce.service.customer;

import com.Spring_ecommerce.model.CreditCard;

import java.util.List;

public interface CreditCardService {
    List<CreditCard> getAll();
    CreditCard add(CreditCard creditCard);
    List<CreditCard> getCreditCardByUserId(int id);
    CreditCard getById(int id);
}
