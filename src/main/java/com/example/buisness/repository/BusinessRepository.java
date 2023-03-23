package com.example.buisness.repository;

import com.example.buisness.beans.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Integer> {

    boolean existsById(int id);

}
