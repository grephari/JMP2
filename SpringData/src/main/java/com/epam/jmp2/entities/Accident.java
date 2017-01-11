package com.epam.jmp2.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "accidents")
public class Accident implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "accident_Index", nullable = false)
	private String accidentIndex;

	@Column(name = "Longitude", nullable = false, length = 10)
	private Float longitude;
	@Column(name = "Latitude", nullable = false, length = 10)
	private Float latitude;

	@Column(name = "Accident_Severity", nullable = false)
	private Integer accidentSeverity;
	@Column(name = "Number_of_Vehicles", nullable = false)
	private Integer numberOfVehicles;
	@Column(name = "Number_of_Casualties", nullable = false)
	private Integer numberOfCasualties;
	@Column(name = "Date", nullable = false)
	// @Temporal(TemporalType.DATE)
	private String Date;
	@Column(name = "Day_of_Week  ", nullable = false)
	private Integer dayOfWeek;
	@Column(name = "Time", nullable = false)
	private String Time;
	@Column(name = "Year", nullable = false)
	private String year;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Local_Authority_District")
	private DistrictAuthority localDistrictAuthority;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Police_Force")
	private PoliceForce policeForce;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Light_Conditions")
	private LightCondition lightCondition;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Weather_Conditions")
	private WeatherCondition weatherCondition;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Road_Surface_Conditions")
	private RoadSurfaceCondition roadSurfaceCondition;

	public String getAccidentIndex() {
		return accidentIndex;
	}

	public void setAccidentIndex(String accidentIndex) {
		this.accidentIndex = accidentIndex;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public PoliceForce getPoliceForce() {
		return policeForce;
	}

	public void setPoliceForce(PoliceForce policeForce) {
		this.policeForce = policeForce;
	}

	public Integer getAccidentSeverity() {
		return accidentSeverity;
	}

	public void setAccidentSeverity(Integer accidentSeverity) {
		this.accidentSeverity = accidentSeverity;
	}

	public Integer getNumberOfVehicles() {
		return numberOfVehicles;
	}

	public void setNumberOfVehicles(Integer numberOfVehicles) {
		this.numberOfVehicles = numberOfVehicles;
	}

	public Integer getNumberOfCasualties() {
		return numberOfCasualties;
	}

	public void setNumberOfCasualties(Integer numberOfCasualties) {
		this.numberOfCasualties = numberOfCasualties;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public Integer getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public LightCondition getLightCondition() {
		return lightCondition;
	}

	public void setLightCondition(LightCondition lightCondition) {
		this.lightCondition = lightCondition;
	}

	public String toString() {
		return "Acccident:" + "\nid:" + this.accidentIndex +
		// "\nroadSurfaceConditions:" + this.roadSurfaceCondition+
				"\nweatherConditions:" + this.weatherCondition;
	}

	public WeatherCondition getWeatherCondition() {
		return weatherCondition;
	}

	public void setWeatherCondition(WeatherCondition weatherCondition) {
		this.weatherCondition = weatherCondition;
	}

	public RoadSurfaceCondition getRoadSurfaceCondition() {
		return roadSurfaceCondition;
	}

	public void setRoadSurfaceCondition(RoadSurfaceCondition roadSurfaceCondition) {
		this.roadSurfaceCondition = roadSurfaceCondition;
	}

	public DistrictAuthority getLocalDistrictAuthority() {
		return localDistrictAuthority;
	}

	public void setLocalDistrictAuthority(DistrictAuthority localDistrictAuthority) {
		this.localDistrictAuthority = localDistrictAuthority;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
