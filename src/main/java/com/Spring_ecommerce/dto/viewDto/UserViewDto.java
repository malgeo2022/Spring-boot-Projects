package com.Spring_ecommerce.dto.viewDto;


import com.Spring_ecommerce.model.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserViewDto implements Serializable {

    private static final Long serialVersionID =1L;

    private final String userName;
    private final String password;
    private final String email;

    public UserViewDto(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public static UserViewDto of(User user){
        return new UserViewDto(user.getUserName(), user.getPassword(), user.getEmail());
    }
}
