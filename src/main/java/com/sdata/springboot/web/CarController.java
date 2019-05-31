package com.sdata.springboot.web;

import com.sdata.springboot.persistance.Car;
import com.sdata.springboot.persistance.CarRepository;
import com.sdata.springboot.web.exception.CarIdMismatchException;
import com.sdata.springboot.web.exception.CarNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping("/all")
    public Iterable<Car> findAll() {
        return carRepository.findAll();
    }

    @GetMapping("/model/{carModel}")
    public List<Car> findByModel(@PathVariable String carModel) {
        return carRepository.findByModel(carModel);
    }

    @GetMapping("/{id}")
    public Car findOne(@PathVariable long id) {
        return carRepository.findById(id)
                .orElseThrow(CarNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Car create(@RequestBody Car car) {
        Car car1 = carRepository.save(car);
        return car1;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        carRepository.findById(id)
                .orElseThrow(CarNotFoundException::new);
        carRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Car updateBook(@RequestBody Car car, @PathVariable long id) {
        if (car.getId() != id) {
            throw new CarIdMismatchException();
        }
        carRepository.findById(id)
                .orElseThrow(CarNotFoundException::new);
        return carRepository.save(car);
    }
}
