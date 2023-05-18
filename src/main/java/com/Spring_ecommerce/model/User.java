package com.Spring_ecommerce.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Please enter field Name !")
    @Column(name = "username")
    private String userName;

    @NotNull(message = "Please enter a password is Mandatory!")
    @Column(name = "password")
    private String password;


    @NotNull(message = "Please enter a your email is Mandatory!")
    @Column(name = "email")
    private String email;

    @Column(name = "createDate")
    private Date userCreateDate;

    @Column(name = "notificationPermission")
    private boolean notificationPermission = true;

    @ManyToOne
    private CreditCard creditCard;

    @OneToMany
    private List<CustomerComment> customerComment;

    @OneToMany
    private List<Address> address;

    @OneToMany
    private List<ProductComment> productComment;

    public User(String userName, String password, String email, Date userCreateDate, boolean notificationPermission) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.userCreateDate = userCreateDate;
        this.notificationPermission = notificationPermission;
    }

}
