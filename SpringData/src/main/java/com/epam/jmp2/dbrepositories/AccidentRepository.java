package com.epam.jmp2.dbrepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.epam.jmp2.entities.Accident;
import com.epam.jmp2.model.RoadAccident;

public interface AccidentRepository extends JpaRepository<Accident, String> {

	static final String QUERY_ROAD_ACCIDENT_PREFIX = "select new com.epam.jmp2.model.RoadAccident("
			+ "  a.accidentIndex, a.longitude, a.latitude, a.policeForce.label, a.accidentSeverity.label, "
			+ "  a.numberOfVehicles, a.numberOfCasualties, a.date, a.Time, a.dayOfWeek, a.localDistrictAuthority.label, "
			+ "  a.lightCondition.label, a.weatherCondition.label, a.roadSurfaceCondition.label ) "
			+ "from Accident a ";

	Accident findOne(String accidentId);

	@Query(value = QUERY_ROAD_ACCIDENT_PREFIX + "where a.roadSurfaceCondition.code = :roadSurfaceCode")
	Iterable<RoadAccident> queryByRoadSurfaceCondition(@Param("roadSurfaceCode") Integer road_surface);

	@Query(value = QUERY_ROAD_ACCIDENT_PREFIX
			+ "where a.weatherCondition.code = :weatherConditionCode and a.date like %:strYear%")
	Iterable<RoadAccident> queryByWeatherConditionAndYear(@Param("weatherConditionCode") Integer weatherCondition,
			@Param("strYear") String year);

	@Query(value = QUERY_ROAD_ACCIDENT_PREFIX + "where a.date = :accidentDate")
	Iterable<RoadAccident> findByDate(@Param("accidentDate") String date);

}
