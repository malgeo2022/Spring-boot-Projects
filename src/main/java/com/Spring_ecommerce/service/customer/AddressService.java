package com.Spring_ecommerce.service.customer;


import com.Spring_ecommerce.model.Address;

import java.util.List;

public interface AddressService {

     Address add(Address address);
     List<Address> getAll();
     void deleteById(int id);
     List<Address> getAddressByUserId(int userId);
     Address getById(int id);

}
