package com.Spring_ecommerce.controllers;


import com.Spring_ecommerce.handler.msgHandler.EcommerceMessage;
import com.Spring_ecommerce.model.User;
import com.Spring_ecommerce.requests.UserCreateRequest;
import com.Spring_ecommerce.requests.UserDeleteRequest;
import com.Spring_ecommerce.requests.UserLoginRequest;
import com.Spring_ecommerce.security.JwtTokenProvider;
import com.Spring_ecommerce.service.customer.SendEmailService;
import com.Spring_ecommerce.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.AddressException;
import java.util.Date;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final SendEmailService sendEmailService;


    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, PasswordEncoder passwordEncoder, SendEmailService sendEmailService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.sendEmailService = sendEmailService;
    }


    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        User user = userService.getByUserName(loginRequest.getUserName());
        sendEmailService.sendEmails(user.getEmail(), EcommerceMessage.LOGIN_BODY, EcommerceMessage.LOGIN_TOPIC);
        return "The token .." + jwtToken;
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserCreateRequest user)throws AddressException{
        if (userService.findByEMail(user.getEmail()) != null){
            return new ResponseEntity<>(EcommerceMessage.EMAIL_ALREADY_IN_USE, HttpStatus.BAD_REQUEST);
        }

        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.setUserCreateDate(new Date());
        userService.add(newUser);
        sendEmailService.sendEmails(String.valueOf(user.getEmail()),EcommerceMessage.REGISTER_BODY, EcommerceMessage.REGISTER_TOPIC);
        return new ResponseEntity<>(EcommerceMessage.USER_CREATED, HttpStatus.CREATED);
    }


    @DeleteMapping("/deleteByUser")
    public ResponseEntity<?> deleteByUser(@RequestBody UserDeleteRequest userDeleteRequest){
        userService.authDeleteByUser(userDeleteRequest);
        sendEmailService.sendEmails(userDeleteRequest.getEmail(), EcommerceMessage.AUTH_DELETE_BODY, EcommerceMessage.AUTH_DELETE_TOPIC + EcommerceMessage.AUTH_DELETE_TOPIC_EMOJI);
        return new ResponseEntity<>(EcommerceMessage.USER_DELETED, HttpStatus.OK);
    }





}
