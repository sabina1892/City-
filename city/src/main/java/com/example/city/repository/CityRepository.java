package com.example.city.repository;

import com.example.city.Model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, String> {
    List<City> findAllByName(String name);
    Optional<City> findByName(String name);
}
