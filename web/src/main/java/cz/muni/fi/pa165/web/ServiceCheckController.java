/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.web;

import cz.muni.fi.pa165.service.dto.CarDTO;
import cz.muni.fi.pa165.service.dto.ServiceCheckDTO;
import cz.muni.fi.pa165.service.service.CarServiceInterface;
import cz.muni.fi.pa165.service.service.LeaseServiceInterface;
import cz.muni.fi.pa165.service.service.PersonServices;
import cz.muni.fi.pa165.service.service.ServiceCheckInterface;
import static java.lang.Math.log;
import java.util.List;
import java.util.Locale;
import static javax.swing.text.StyleConstants.ModelAttribute;
import javax.validation.Valid;
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
 *
 * @author jozef.puchly
 */
@Controller
@RequestMapping("/serviceCheck")
public class ServiceCheckController {
    
        CarServiceInterface carService;
	LeaseServiceInterface leaseService;
	ServiceCheckInterface serviceCheckService;
	PersonServices personService;
        
        @Autowired
	public ServiceCheckController(CarServiceInterface carService,
			ServiceCheckInterface serviceCheckService,
                        LeaseServiceInterface leaseService,
			PersonServices personService) {
		this.carService = carService;
		this.leaseService = leaseService;
		this.serviceCheckService = serviceCheckService;
		this.personService = personService;
        }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addServiceCheck(@ModelAttribute("serviceCheck") ServiceCheckDTO sc,
			BindingResult result, ModelMap model) {
            
                String errorMsg = null;

		// empty object with id only
		CarDTO car = sc.getCar();

		try {
			car = carService.getCarByID(car.getCarID());
			if (car == null) {
				errorMsg = "Machine not found";
			}
			
			sc.setCar(car);

			serviceCheckService.createServiceCheck(sc);
		} catch (DataAccessException e) {
			if (errorMsg != null)
				errorMsg = e.getMessage();
		}
		if (errorMsg != null) {
			model.addAttribute("errorMessage", errorMsg);
		}
		return "redirect:list";
	}
        
    @RequestMapping(value = "/listServiceChecks", method = RequestMethod.GET)
        public ModelAndView listServiceChecks(ModelMap model,
                @RequestParam("car") CarDTO car) {
            
                List<ServiceCheckDTO> serviceCheckNew = serviceCheckService.getServiceChecksForCar(car);
		model.addAttribute("serviceCheckNew", serviceCheckNew);

		return new ModelAndView("listServiceChecks");
        }
        
    @RequestMapping(value = "/getDaysToNextSC", method = RequestMethod.GET)
        public ModelAndView getDaysToNextSC(ModelMap model,
                @RequestParam("serviceCheck") ServiceCheckDTO sc) {
            
                int days;
                
                days = serviceCheckService.getDaysToNextServiceCheck(sc);
                
                model.addAttribute("days", days);
                
                return new ModelAndView("getDaysToNextSC");
                        
                        
        }
     
}
