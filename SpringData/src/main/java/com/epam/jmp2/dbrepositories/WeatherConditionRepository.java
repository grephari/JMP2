package com.epam.jmp2.dbrepositories;

import org.springframework.data.repository.CrudRepository;

import com.epam.jmp2.entities.WeatherCondition;
import com.epam.jmp2.model.RoadAccident;


public interface WeatherConditionRepository extends CrudRepository<WeatherCondition,Integer>{
    Iterable<RoadAccident> findAccidentsByCode(String code);
}
