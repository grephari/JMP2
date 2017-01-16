package com.epam.jmp2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.jmp2.entities.Accident;
import com.epam.jmp2.service.AccidentService;

@Controller
@RequestMapping(value = "/accident")
public class AccidentController {

	@Autowired
	private AccidentService accidentService;

	@RequestMapping("/roadCondition")
	public ModelAndView  getAllAccidentsByRoadCondition(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer label,
			Model model) {
		Iterable<Accident> accidents = accidentService.getAllAccidentsByRoadCondition(label);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("accident/list");
		mav.addObject("accidentList",accidents);
		return mav;
	}

	@RequestMapping("/edit")
	public ModelAndView editAccidentByAccidentId(@RequestParam(value = "accidentId") String accidentId,
			Model model) {
		Accident accident = accidentService.findOne(accidentId);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("accident/edit");
		mav.addObject("accident",accident);
		return mav;
	}

	@RequestMapping("/add")
	public String addAccident() {
		return "accident/add";
	}

}
