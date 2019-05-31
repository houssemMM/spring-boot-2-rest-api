package com.sdata.springboot.persistance;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findByModel(String model);
}