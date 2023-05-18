package com.Spring_ecommerce.requests;


import lombok.Data;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class PushEmailRequest {

    @Email
    private String email;

    @NotNull
    private String body;

    @NotNull
    private String title;
}
