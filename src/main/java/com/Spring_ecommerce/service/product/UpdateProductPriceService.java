package com.Spring_ecommerce.service.product;

import com.Spring_ecommerce.requests.CampaignCreateRequest;
import com.Spring_ecommerce.requests.PriceIncreaseRequest;

public interface UpdateProductPriceService {

    void createCampaign(CampaignCreateRequest campaignCreateRequest);
    void priceIncrease(PriceIncreaseRequest priceIncreaseRequest);
}
