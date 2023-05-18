package com.Spring_ecommerce.requests;


import lombok.Data;

@Data
public class CampaignCreateRequest {

    int productId;
    int discountAmount;
}
