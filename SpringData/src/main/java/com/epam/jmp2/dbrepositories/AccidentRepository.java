package com.epam.jmp2.dbrepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.epam.jmp2.entities.Accident;
import com.epam.jmp2.model.RoadAccident;

public interface AccidentRepository extends JpaRepository<Accident, String> {
    Accident findOne(String accidentId);

 
    List<Accident> findByRoadSurfaceCondition(Integer road_surface);
    
    static final String Query_String = "select new com.epam.jmp2.model.RoadAccident("
			+ "  a.accidentIndex, a.longitude, a.latitude, a.policeForce.label, a.accidentSeverity.label, "
			+ "  a.numberOfVehicles, a.numberOfCasualties, a.date, a.time, a.dayOfWeek, a.localDistrictAuthority.label, "
			+ "  a.lightCondition.label, a.weatherCondition.label, a.roadSurfaceCondition.label ) "
			+ "from Accident a ";


	@Query(value = Query_String + "where a.roadSurfaceCondition.code = :roadSurfaceCode")
	Iterable<RoadAccident> queryByRoadSurfaceCondition(@Param("roadSurfaceCode") Integer road_surface);

	@Query(value = Query_String
			+ "where a.weatherCondition.code = :weatherConditionCode and a.date like %:strYear%")
	Iterable<RoadAccident> queryByWeatherConditionAndYear(@Param("weatherConditionCode") Integer weatherCondition,
			@Param("strYear") String year);

	@Query(value = Query_String + "where a.date = :accidentDate")
	Iterable<RoadAccident> findByDate(@Param("accidentDate") String date);
	
	@Query(value = Query_String + "where a.accidentIndex=:accidentId")
	RoadAccident queryById(@Param("accidentId") String accidentId);


}
