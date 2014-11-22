/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.web;


import cz.muni.fi.pa165.service.dto.CarDTO;
import cz.muni.fi.pa165.service.service.CarServiceInterface;
import cz.muni.fi.pa165.service.service.LeaseServiceInterface;
import cz.muni.fi.pa165.service.service.PersonServices;
import cz.muni.fi.pa165.service.service.ServiceCheckInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Machine controller implementation
 * 
 * @author Jozef Puchly
 * 
 */
@Controller
@RequestMapping("/car")
public class CarController {

	CarServiceInterface carService;
	LeaseServiceInterface leaseService;
	ServiceCheckInterface serviceCheckService;
	PersonServices personService;

	
	public CarController(CarServiceInterface carService,
			ServiceCheckInterface serviceCheckService,
                        LeaseServiceInterface leaseService,
			PersonServices personService) {
		this.carService = carService;
		this.leaseService = leaseService;
		this.serviceCheckService = serviceCheckService;
		this.personService = personService;
        }

	@RequestMapping(value = "/listCars", method = RequestMethod.GET)
	public ModelAndView listCars(ModelMap model,
			@RequestParam("isInactive") boolean isInactive) {

		List<CarDTO> carNew = carService.findAllCars(isInactive);
		model.addAttribute("carNew", carNew);

		return new ModelAndView("listCars");
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addCar(@ModelAttribute("car") CarDTO car,
			BindingResult result, ModelMap model) {
		
		carService.createCar(car);

		return "redirect:list";
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteCar(@PathVariable String id, ModelMap model) {
		boolean deleted = false;
		String errorMsg = null;
		CarDTO car = new CarDTO();
		try {
			Integer carID = Integer.valueOf(id);
                        
                        car = carService.getCarByID(carID);
			carService.deleteCar(car);
			deleted = true;
		} catch (DataAccessException | NumberFormatException
				| NullPointerException e) {
			
			deleted = false;
			errorMsg = e.getMessage();
		}

		model.addAttribute("deleteStatus", deleted);
		if (errorMsg != null) {
			model.addAttribute("errorMessage", errorMsg);
		}
		return "redirect:/car/list";
	}


	
}

