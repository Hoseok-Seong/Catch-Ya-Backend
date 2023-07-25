package com.example.catchya.police.repository;

import com.example.catchya.police.entity.Police;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PoliceRepository extends JpaRepository<Police, Long> {
    @Query(value = "SELECT id, address, city, district, division, latitude, longitude, name, phone, " +
            "(6371 * acos(cos(radians(:lat)) * cos(radians(latitude)) * cos(radians(longitude) - radians(:lon)) " +
            "+ sin(radians(:lat)) * sin(radians(latitude)))) AS distance " +
            "FROM police " +
            "GROUP BY id, address, city, district, division, latitude, longitude, name, phone " +
            "HAVING distance <= :radius " +
            "ORDER BY distance " +
            "LIMIT 0, 100", nativeQuery = true)
    List<Police> findPoliceByDistance(double lat, double lon, Integer radius);
}
