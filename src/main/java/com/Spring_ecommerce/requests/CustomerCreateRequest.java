package com.Spring_ecommerce.requests;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CustomerCreateRequest {

    @NotNull
    private String name;

    private String profilePictureUrl;

    @NotNull
    @Email
    private String email;

    private Date createDate;

    public CustomerCreateRequest(String name, String profilePictureUrl, String email, Date createDate) {
        this.name = name;
        this.profilePictureUrl = profilePictureUrl;
        this.email = email;
        this.createDate = createDate;
    }
}
