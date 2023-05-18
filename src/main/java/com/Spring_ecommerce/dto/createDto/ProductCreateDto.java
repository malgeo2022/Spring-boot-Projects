package com.Spring_ecommerce.dto.createDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDto {

    private String productName;
    private String productBrand;
    private String productDetails;
    private double productPrice;
    private int stock;
    private String productImageUrl;

}
