package com.example.buisness.service;

import com.example.buisness.beans.Business;
import com.example.buisness.beans.Worker;
import com.example.buisness.repository.BusinessRepository;
import com.example.buisness.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private BusinessService businessService;

    public Worker add(Worker worker, int businessId) {
        if (isExist(worker.getId()) || businessService.isExist(businessId))
        worker.setBusiness(businessService.getSingle(businessId));
        return workerRepository.save(worker);
    }

    public List<Worker> getAllByBusiness(int businessId) {
        return workerRepository.findAllByBusinessId(businessId);
    }

    public Worker getSingle(int workerId) {
        return workerRepository.findById(workerId).orElse(null);
    }

    public void delete(int workerId) {
        workerRepository.deleteById(workerId);
    }

    public List<Worker> getByStartsWith(String startsWith) {
        return workerRepository.findAllByFirstNameStartingWith(startsWith);
    }

    public boolean isExist(int id) {
        return workerRepository.existsById(id);
    }
}
