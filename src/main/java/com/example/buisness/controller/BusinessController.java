package com.example.buisness.controller;

import com.example.buisness.beans.Business;
import com.example.buisness.customExceptions.BusinessException;
import com.example.buisness.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.List;

@RestController
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @GetMapping
    public List<Business> getAll() {
        return businessService.get();
    }

    @GetMapping("/{id}")
    public Business getSingle(@PathVariable int id) {
        return businessService.getSingle(id);
    }

    @PostMapping
    public Business add(@RequestBody Business business) {
        return businessService.add(business);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) throws BusinessException {
        businessService.delete(id);
    }
    @PutMapping
    public Business update(@RequestBody Business business) {
        return businessService.update(business);
    }
}
