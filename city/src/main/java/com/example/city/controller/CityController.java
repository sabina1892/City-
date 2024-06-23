package com.example.city.controller;

import com.example.city.Model.City;
import com.example.city.exceptions.CityNotFoundException;
import com.example.city.repository.CityRepository;
import com.example.city.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/cities")
@AllArgsConstructor
public class CityController {

   private final CityService cityService;

    @GetMapping()
    public ResponseEntity<List<City>> getCities(@RequestParam(required = false) String name) {
        return new ResponseEntity<>(cityService.getCities(name),OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCity(@PathVariable String id){
            return new ResponseEntity<>(getById(id),OK);
        }

    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody City newCity){
        return  new ResponseEntity<>(cityService.createCity(newCity), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> getCity(@PathVariable String id, @RequestBody City newCity){
        cityService.updateCity(id, newCity);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable String id){

        cityService.deleteCity(id);
        return new ResponseEntity<>(OK);
    }
    private City getById(String id) {
        return cityService.getById(id);
    }
    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<String> handleCityNotFoundException(CityNotFoundException exp){
        return new ResponseEntity<>(exp.getMessage(),NOT_FOUND);
    }

}
