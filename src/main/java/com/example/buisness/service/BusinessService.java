package com.example.buisness.service;

import com.example.buisness.beans.Business;
import com.example.buisness.customExceptions.BusinessException;
import com.example.buisness.customExceptions.ErrorMsg;
import com.example.buisness.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {
    @Autowired
    private BusinessRepository businessRepository;

    public Business add(Business business) {
        return businessRepository.save(business);
    }

    public List<Business> get() {
        return businessRepository.findAll();
    }

    public Business update(Business business) {
            return businessRepository.save(business);
    }

    public Business getSingle(int businessId) {
        return businessRepository.findById(businessId).orElse(null);
    }

    public void delete(int businessId) throws BusinessException {
        if (!isExist(businessId))
            throw new BusinessException(ErrorMsg.NOT_EXIST);
        businessRepository.deleteById(businessId);
    }

    public boolean isExist(int id) {
        return businessRepository.existsById(id);
    }

}
