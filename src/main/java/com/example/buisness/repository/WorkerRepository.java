package com.example.buisness.repository;

import com.example.buisness.beans.Worker;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {

    List<Worker> findAllByBusinessId(int businessId);

    List<Worker> findAllByFirstNameStartingWith(String startsWith);

}
