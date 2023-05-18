package com.Spring_ecommerce.requests;


import lombok.Data;

@Data
public class UserUpdateNotificationPermissionRequest {

    private int userId;
    private boolean permission;

}
