package com.Spring_ecommerce.controllers;


import com.Spring_ecommerce.requests.PushAllUserEmailRequest;
import com.Spring_ecommerce.requests.PushEmailRequest;
import com.Spring_ecommerce.service.customer.SendEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/email/")
@CrossOrigin
@RequiredArgsConstructor
public class EmailController {


    private final SendEmailService sendEmailService;


    @PostMapping("pushEmail")
    public ResponseEntity<?> pushEmail(@Valid @RequestBody PushEmailRequest pushEmailRequest){
        sendEmailService.sendEmails(pushEmailRequest.getEmail(), pushEmailRequest.getBody(), pushEmailRequest.getTitle());
        return ResponseEntity.ok("success");
    }

    @PostMapping("pushEmailToAllUser")
    public ResponseEntity<?> pushEmailToAllUser(@Valid @RequestBody PushAllUserEmailRequest pushAllUserEmailRequest){
        sendEmailService.sendEmailAllUser(pushAllUserEmailRequest.getBody(), pushAllUserEmailRequest.getTitle());
        return ResponseEntity.ok("success");
    }
}
