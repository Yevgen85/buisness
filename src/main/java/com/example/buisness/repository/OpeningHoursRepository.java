package com.example.buisness.repository;

import com.example.buisness.beans.OpeningHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpeningHoursRepository extends JpaRepository<OpeningHours, Integer> {


    @Query(value = "SELECT * FROM opening_hours h WHERE h.business_id = ?", nativeQuery = true)
    List<OpeningHours> findAllByBusinessId(int businessId);

    @Query(value = "SELECT * FROM opening_hours h WHERE h.business_id = ? AND h.is_open = true", nativeQuery = true)
    List<OpeningHours> findAllByBusinessIdAndOpenIsTrue(int businessId);

    boolean existsById(int id);
}
