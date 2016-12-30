package com.epam.jmp2.dbrepositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.jmp2.entities.WeatherCondition;
import com.epam.jmp2.model.RoadAccident;

@Repository
public interface WeatherConditionRepository extends CrudRepository<WeatherCondition, Integer> {

	Iterable<RoadAccident> findAccidentsByCode(String code);

	@Query(value = "select w.code from WeatherCondition w")
	List<Integer> findAllCodes();

}
