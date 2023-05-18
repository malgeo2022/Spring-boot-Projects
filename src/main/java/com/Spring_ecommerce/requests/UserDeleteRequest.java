package com.Spring_ecommerce.requests;


import lombok.Data;

@Data
public class UserDeleteRequest {
    private String email;
    private String password;

}
