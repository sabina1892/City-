package com.example.city.service;

import com.example.city.Model.City;
import com.example.city.exceptions.CityNotFoundException;
import com.example.city.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public List<City> getCities(String name){
        if(name == null){
            return cityRepository.findAll();
        }
        else{
            return cityRepository.findAllByName(name);
        }
    }

    public City createCity(City newCity) {
        cityRepository.findByName(newCity.getName())
                .orElseThrow(() -> new CityNotFoundException("Var, yeniden yazdin"));
        return cityRepository.save(newCity);
    }

    public void deleteCity(String id) {
        cityRepository.deleteById(id);
    }

    public City getById(String id) {
       return cityRepository.findById(id)
               .orElseThrow(()-> new CityNotFoundException("City not found"));
    }

    public void updateCity(String id, City newCity) {
        City oldcity = getById(id);
        oldcity.setName(newCity.getName());
        cityRepository.save(oldcity);
    }
}
