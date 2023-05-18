package com.Spring_ecommerce.service.customer.Impl;

import com.Spring_ecommerce.requests.PromoCodeCreateRequest;
import com.Spring_ecommerce.exception.NotFoundException;
import com.Spring_ecommerce.model.PromoCode;
import com.Spring_ecommerce.model.User;
import com.Spring_ecommerce.repository.PromoCodeRepository;
import com.Spring_ecommerce.service.customer.CreateCodeService;
import com.Spring_ecommerce.service.customer.PromoCodeService;
import com.Spring_ecommerce.service.customer.SendEmailService;
import com.Spring_ecommerce.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PromoCodeServiceImpl implements PromoCodeService {

   private final UserService userService;
   private final PromoCodeRepository promoCodeRepository;
   private final CreateCodeService createCodeService;
   private final SendEmailService sendEmailService;


    @Override
    public String createPromoCode(PromoCodeCreateRequest promoCodeCreateRequest) {
        Optional<User> user = Optional.ofNullable(userService.getById(promoCodeCreateRequest.getUserId()));
        if (user.isPresent()){
            PromoCode promoCode = new PromoCode();
            promoCode.setCode(createCodeService.createCode() + promoCodeCreateRequest.getAmount());
            promoCode.setCreateDate(new Date());
            promoCode.setAmount(promoCodeCreateRequest.getAmount());
            promoCode.setExpirationDate(new Date(promoCodeCreateRequest.getYear(), promoCodeCreateRequest.getMonth(), promoCodeCreateRequest.getDate()));
            promoCode.setUser(user.get());

            promoCodeRepository.save(promoCode);
            userService.add(user.get());

            sendEmailService.sendEmails(user.get().getEmail(), "You have earned your promo code \n Expiration Date:" + promoCode.getExpirationDate() + "\nCode: "
            + promoCode.getCode(), "You have Promo Code");
            return promoCode.getCode();

        }
        return "failed";
    }

    @Override
    public List<PromoCode> getAll() {
        return promoCodeRepository.findAll();
    }

    @Override
    public PromoCode getById(Long id) {
        return promoCodeRepository.findById(id).orElseThrow(()-> new NotFoundException("promo code not found by given id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        promoCodeRepository.deleteById(id);
    }
}
