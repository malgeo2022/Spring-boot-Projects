package com.Spring_ecommerce.controllers;


import com.Spring_ecommerce.requests.PromoCodeCreateRequest;
import com.Spring_ecommerce.service.customer.PromoCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/promo-code/")
@RequiredArgsConstructor
@CrossOrigin
public class PromoCodeController {

private final PromoCodeService promoCodeService;


@PostMapping("create-code")
public ResponseEntity<?> createCode(@RequestBody PromoCodeCreateRequest promoCodeCreateRequest){
    promoCodeService.createPromoCode(promoCodeCreateRequest);
    return ResponseEntity.ok("success");
}


@DeleteMapping("delete-code/{id}")
public ResponseEntity<?> deleteById(@PathVariable Long id){
    promoCodeService.deleteById(id);
    return ResponseEntity.ok("success");
}

@GetMapping("getAll")
public ResponseEntity<?> getAll(){
    return ResponseEntity.ok(promoCodeService.getAll());
}

@GetMapping("getById/{id}")
public ResponseEntity<?> getById(@PathVariable Long id){
    return ResponseEntity.ok(promoCodeService.getById(id));
}



}
