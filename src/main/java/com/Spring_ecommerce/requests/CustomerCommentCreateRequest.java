package com.Spring_ecommerce.requests;


import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.lang.ref.PhantomReference;
import java.util.Date;

@Data
public class CustomerCommentCreateRequest {
      private String title;
      private String body;

      @Min(value = 1)
      @NotNull
      private int rating;

      private Date createDate;

    public CustomerCommentCreateRequest(String title, String body, int rating, Date createDate) {
        this.title = title;
        this.body = body;
        this.rating = rating;
        this.createDate = createDate;
    }
}
