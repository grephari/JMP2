package com.epam.jmp2.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.jmp2.entities.Accident;
import com.epam.jmp2.model.RoadAccident;
import com.epam.jmp2.model.RoadAccidentBuilder;
import com.epam.jmp2.service.AccidentService;

@Controller
@RequestMapping("/webapp")
public class RoadAccidentController2 {
	
	@Autowired
	private AccidentService accidentService;
	
	@RequestMapping("/home")
	public String home(){
		return "home";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	@RequestMapping("/list")
	public String getAllRoadAccidents(Model model){
		List<RoadAccident> roadAccidentList = accidentService.getAllAccidents();
		model.addAttribute("accidentList", roadAccidentList);
		return "accidentList";
	}
	
	@RequestMapping("/add")
	public String addAccident(Model model){
		 model.addAttribute("roadAccident", new RoadAccident());
		return "addAccident";
	}
	
	@RequestMapping("/save")
	public ModelAndView saveAccident(@ModelAttribute RoadAccident roadAccident){
		Accident accident = new Accident();
		accident.setAccidentSeverity(Integer.valueOf(roadAccident.getAccidentSeverity()));
		accident.setLatitude(roadAccident.getLatitude());
		accident.setLongitude(roadAccident.getLongitude());
		accident.setNumberOfVehicles(roadAccident.getNumberOfVehicles());
		//accidentService.saveAccident(accident);
		return new ModelAndView("redirect:/webapp/list");
	}
}
