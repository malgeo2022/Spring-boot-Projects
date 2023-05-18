package com.Spring_ecommerce.requests;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PushAllUserEmailRequest {

    @NotNull
    private String body;

    @NotNull
    private String title;


}
