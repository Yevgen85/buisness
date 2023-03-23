package com.example.buisness.service;

import com.example.buisness.beans.Business;
import com.example.buisness.beans.OpeningHours;
import com.example.buisness.customExceptions.BusinessException;
import com.example.buisness.customExceptions.ErrorMsg;
import com.example.buisness.repository.OpeningHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpeningHoursService {
    @Autowired
    private OpeningHoursRepository openingHoursRepository;
    @Autowired
    private BusinessService businessService;

    public List<OpeningHours> getByBusiness(int businessId) {
        return openingHoursRepository.findAllByBusinessId(businessId);
    }

    public List<OpeningHours> getByBusinessAndByOpenedDays(int businessId) {
        return openingHoursRepository.findAllByBusinessIdAndOpenIsTrue(businessId);
    }
}
