package com.example.buisness.controller;

import com.example.buisness.beans.Business;
import com.example.buisness.beans.OpeningHours;
import com.example.buisness.service.OpeningHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/opening-hours")
public class OpeningHoursController {

    @Autowired
    private OpeningHoursService openingHoursService;

    @GetMapping("/{businessId}")
    public List<OpeningHours> getOpeningHoursByBusiness(@PathVariable int businessId) {
        return openingHoursService.getByBusiness(businessId);
    }

    @GetMapping("/filter/{businessId}")
    public List<OpeningHours> getOpeningHoursByBusinessAndIsOpen(@PathVariable int businessId) {
        return openingHoursService.getByBusinessAndByOpenedDays(businessId);
    }

}
