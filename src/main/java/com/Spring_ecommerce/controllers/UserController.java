package com.Spring_ecommerce.controllers;


import com.Spring_ecommerce.dto.viewDto.UserViewDto;
import com.Spring_ecommerce.handler.msgHandler.GenericResponse;
import com.Spring_ecommerce.model.User;
import com.Spring_ecommerce.requests.UserNameUpdateRequest;
import com.Spring_ecommerce.requests.UserUpdateNotificationPermissionRequest;
import com.Spring_ecommerce.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users/")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

  private UserService userService;


  @PostMapping("add")
  public ResponseEntity<?>add(@RequestBody @Valid User userCreateDto){
      this.userService.add(userCreateDto);
      return ResponseEntity.ok(new GenericResponse("User created"));
  }


  @GetMapping("getAll")
  public ResponseEntity<List<User>> getAll(){
      List<User> users = this.userService.getAll();
      return ResponseEntity.ok(users);
  }

  @GetMapping("getById/{id}")
  public User getById(@PathVariable int id){
      return this.userService.getById(id);
  }


  @GetMapping("slice")
  public ResponseEntity<List<User>> slice(Pageable pageable){
      List<User> users = this.userService.slice(pageable);
      return ResponseEntity.ok(users);
  }

  @DeleteMapping("delete")
  public ResponseEntity<?> deleteById(int id){
      this.userService.deleteById(id);
      return  ResponseEntity.ok(new GenericResponse("User Deleted..!"));
  }


  @GetMapping("getDto")
  public ResponseEntity<List<UserViewDto>> getDto() {
      List<UserViewDto> users = this.userService.getUserViewDto();
      return ResponseEntity.ok(users);
  }


  @PutMapping("updateByUserName")
  public ResponseEntity<?> updateByUserName(@RequestBody UserNameUpdateRequest userNameUpdateRequest){
      userService.updateByUserName(userNameUpdateRequest.getUserId(), userNameUpdateRequest.getUserName());
      return ResponseEntity.ok("sucess");
  }


  @PutMapping("updateBynotificationPermission")
  public ResponseEntity<?> updateBynotificationPermission(UserUpdateNotificationPermissionRequest request){
      userService.updateByNotificationPermission(request.getUserId(), request.isPermission());
      return ResponseEntity.ok("success");
  }







}
