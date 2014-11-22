/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.web;

import cz.muni.fi.pa165.service.dto.PersonDTO;
import cz.muni.fi.pa165.service.service.CarServiceInterface;
import cz.muni.fi.pa165.service.service.LeaseServiceInterface;
import cz.muni.fi.pa165.service.service.PersonServices;
import cz.muni.fi.pa165.service.service.ServiceCheckInterface;
import java.util.List;
import static javax.swing.text.StyleConstants.ModelAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jozef.puchly
 */
@Controller
@RequestMapping("/person")
public class PersonController {

	CarServiceInterface carService;
	LeaseServiceInterface leaseService;
	ServiceCheckInterface serviceCheckService;
	PersonServices personService;

	
	public PersonController(CarServiceInterface carService,
			ServiceCheckInterface serviceCheckService,
                        LeaseServiceInterface leaseService,
			PersonServices personService) {
		this.carService = carService;
		this.leaseService = leaseService;
		this.serviceCheckService = serviceCheckService;
		this.personService = personService;
        }

	@RequestMapping(value = "/listPeople", method = RequestMethod.GET)
	public ModelAndView listPeople(ModelMap model,
			@RequestParam("isInactive") boolean isInactive) {

		List<PersonDTO> personNew = personService.findAllPeople(isInactive);
		model.addAttribute("personNew", personNew);

		return new ModelAndView("listPeople");
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("person") PersonDTO person,
			BindingResult result, ModelMap model) {
		
		personService.createPerson(person);

		return "redirect:list";
	}
}
