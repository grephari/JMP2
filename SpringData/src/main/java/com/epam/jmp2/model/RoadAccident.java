package com.epam.jmp2.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import com.epam.jmp2.util.DateTimeUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Contains information about one road accident
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoadAccident {
	private String accidentId;
	private float longitude;
	private float latitude;
	private String policeForce;
	private String accidentSeverity;
	private int numberOfVehicles;
	private int numberOfCasualties;
	private LocalDate date;
	private LocalTime time;

	/*
	 * Added by Kevin, for spring-date repository to initiate query results. @see
	 * com.epam.jmp2.dbrepositories.AccidentRepository
	 */
	public RoadAccident(String accidentId, float longitude, float latitude, String policeForce,
			String accidentSeverity, int numberOfVehicles, int numberOfCasualties, String strDate, String strTime,
			int dayOfWeek, String districtAuthority, String lightConditions, String weatherConditions,
			String roadSurfaceConditions) {
		super();
		this.accidentId = accidentId;
		this.longitude = longitude;
		this.latitude = latitude;
		this.policeForce = policeForce;
		this.accidentSeverity = accidentSeverity;
		this.numberOfVehicles = numberOfVehicles;
		this.numberOfCasualties = numberOfCasualties;
		this.date = DateTimeUtils.toLocalDate(strDate);
		this.time = DateTimeUtils.toLocalTime(strTime);
		this.dayOfWeek = DayOfWeek.of(dayOfWeek);
		this.districtAuthority = districtAuthority;
		this.lightConditions = lightConditions;
		this.weatherConditions = weatherConditions;
		this.roadSurfaceConditions = roadSurfaceConditions;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	private DayOfWeek dayOfWeek;
	private String districtAuthority;
	private String lightConditions;
	private String weatherConditions;
	private String roadSurfaceConditions;

	public RoadAccident() {
	}

	RoadAccident(RoadAccidentBuilder builder) {
		this.accidentId = builder.accidentId;
		this.longitude = builder.longitude;
		this.latitude = builder.latitude;
		this.policeForce = builder.policeForce;
		this.accidentSeverity = builder.accidentSeverity;
		this.numberOfVehicles = builder.numberOfVehicles;
		this.numberOfCasualties = builder.numberOfCasualties;
		this.date = builder.date;
		this.time = builder.time;
		this.districtAuthority = builder.districtAuthority;
		this.lightConditions = builder.lightConditions;
		this.weatherConditions = builder.weatherConditions;
		this.roadSurfaceConditions = builder.roadSurfaceConditions;
	}

	public String getAccidentId() {
		return accidentId;
	}

	public void setAccidentId(String accidentId) {
		this.accidentId = accidentId;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public String getPoliceForce() {
		return policeForce;
	}

	public void setPoliceForce(String policeForce) {
		this.policeForce = policeForce;
	}

	public String getAccidentSeverity() {
		return accidentSeverity;
	}

	public void setAccidentSeverity(String accidentSeverity) {
		this.accidentSeverity = accidentSeverity;
	}

	public int getNumberOfVehicles() {
		return numberOfVehicles;
	}

	public void setNumberOfVehicles(int numberOfVehicles) {
		this.numberOfVehicles = numberOfVehicles;
	}

	public int getNumberOfCasualties() {
		return numberOfCasualties;
	}

	public void setNumberOfCasualties(int numberOfCasualties) {
		this.numberOfCasualties = numberOfCasualties;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public DayOfWeek getDayOfWeek() {
		return date.getDayOfWeek();
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getDistrictAuthority() {
		return districtAuthority;
	}

	public void setDistrictAuthority(String districtAuthority) {
		this.districtAuthority = districtAuthority;
	}

	public String getLightConditions() {
		return lightConditions;
	}

	public void setLightConditions(String lightConditions) {
		this.lightConditions = lightConditions;
	}

	public String getWeatherConditions() {
		return weatherConditions;
	}

	public void setWeatherConditions(String weatherConditions) {
		this.weatherConditions = weatherConditions;
	}

	public String getRoadSurfaceConditions() {
		return roadSurfaceConditions;
	}

	public void setRoadSurfaceConditions(String roadSurfaceConditions) {
		this.roadSurfaceConditions = roadSurfaceConditions;
	}

	public String toString() {
    	return "RoadAccident:" +
    			"\nid:" + this.accidentId +
    			"\ndistrictAuthority:" + this.districtAuthority+
    			"\npoliceForce:" + this.policeForce +
    			"\nroadSurfaceConditions:" + this.roadSurfaceConditions +
    			"\nweatherConditions:" + this.weatherConditions;
    			
    			
    			
    } 
}
