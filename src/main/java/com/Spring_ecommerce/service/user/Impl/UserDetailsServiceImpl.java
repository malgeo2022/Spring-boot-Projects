package com.Spring_ecommerce.service.user.Impl;

import com.Spring_ecommerce.model.User;
import com.Spring_ecommerce.security.JwtUserDetails;
import com.Spring_ecommerce.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUserName(username);
        return JwtUserDetails.create(user);
    }

    public UserDetails loadUserById(int id){
        User user = userService.getById(id);
        return JwtUserDetails.create(user);
    }
}
