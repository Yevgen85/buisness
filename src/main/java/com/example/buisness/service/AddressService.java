package com.example.buisness.service;

import com.example.buisness.beans.Address;
import com.example.buisness.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    private Address add(Address address) {
        return addressRepository.save(address);
    }
}
