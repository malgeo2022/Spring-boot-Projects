package com.Spring_ecommerce.controllers;


import com.Spring_ecommerce.handler.msgHandler.EcommerceMessage;
import com.Spring_ecommerce.model.Address;
import com.Spring_ecommerce.service.customer.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/address")
public class AddressController {


    private final AddressService addressService;

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody Address address){
        addressService.add(address);
        return ResponseEntity.ok(EcommerceMessage.ADDRESS_CREATED);
    }


    @GetMapping("getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(addressService.getAll());
    }


    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id){
        addressService.deleteById(id);
        return ResponseEntity.ok(EcommerceMessage.ADDRESS_DELETED);
    }


    @GetMapping("getAddressByUserId/{id}")
    public ResponseEntity<?> getAddressByUserId(@PathVariable int userId){
        return ResponseEntity.ok(addressService.getAddressByUserId(userId));
    }


    @GetMapping("getById/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        return ResponseEntity.ok(addressService.getById(id));
    }


}
