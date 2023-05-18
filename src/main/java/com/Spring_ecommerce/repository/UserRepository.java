package com.Spring_ecommerce.repository;


import com.Spring_ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User getById(int id);

    User findByUserName(String userName);

    @Query("from User where email=:email")
    User findByEmail(String email);
}
