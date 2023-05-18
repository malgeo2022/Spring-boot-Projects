package com.Spring_ecommerce.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "profilePictureUrl")
    private String profilePictureUrl;

    @Column(name = "email")
    private String email;

    @Column(name = "create_date")
    private Date CreateDate;

    @OneToMany
    private List<CustomerComment> customerComment;

    public Customer(String name, String profilePictureUrl, String email, Date createDate) {
        this.name = name;
        this.profilePictureUrl = profilePictureUrl;
        this.email = email;
        this.CreateDate = createDate;
    }
}
