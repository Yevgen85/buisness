package com.example.buisness.controller;

import com.example.buisness.beans.Worker;
import com.example.buisness.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @GetMapping
    public List<Worker> getAllByBusinessId(@RequestParam int businessId){
        return workerService.getAllByBusiness(businessId);
    }

    @GetMapping("/{id}")
    public Worker getSingle(@PathVariable int id) {
        return workerService.getSingle(id);
    }
    @GetMapping("/filter")
    public List<Worker> getByNameStartsWith(@RequestParam String startsWith) {
        return workerService.getByStartsWith(startsWith);
    }

    @PostMapping("/{businessId}")
    public Worker add(@RequestBody Worker worker, @PathVariable int businessId) {
        return workerService.add(worker, businessId );
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        workerService.delete(id);
    }
}


//    @PostMapping
//    public Worker add(@RequestBody Worker worker, @RequestParam int businessId) {
//        return workerService.add(worker, businessId );
//    }