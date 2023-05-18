package com.Spring_ecommerce.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "customer_comment")
public class CustomerComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "rating")
    private int rating;

    @Column(name = "create_date")
    private Date createDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Customer customer;

    public CustomerComment(String title, String body, int rating, Date createDate) {
        this.title = title;
        this.body = body;
        this.rating = rating;
        this.createDate = createDate;
    }
}
