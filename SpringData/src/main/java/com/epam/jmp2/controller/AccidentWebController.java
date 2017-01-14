package com.epam.jmp2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.jmp2.entities.Accident;
import com.epam.jmp2.model.RoadAccident;
import com.epam.jmp2.service.AccidentService;

@Controller
public class AccidentWebController {

	@Autowired
	private AccidentService service;

	@RequestMapping("/list")
	public String list(@RequestParam(value = "page", required = false, defaultValue = "0") int page, Model model) {
		List<Accident> accidents = service.findAll(page);
		System.out.println(accidents.size());
//		model.addAttribute("accidentList", accidents);
		return "accident/list";
	}

	@RequestMapping("/edit")
	public String edit(@RequestParam(value = "id") String accidentId, Model model) {
		RoadAccident accident = service.getAccidentById(accidentId);
		model.addAttribute("accident", accident);
		return "accident/edit";
	}

	@RequestMapping("/add")
	public String add() {
		return "accident/add";
	}

}
