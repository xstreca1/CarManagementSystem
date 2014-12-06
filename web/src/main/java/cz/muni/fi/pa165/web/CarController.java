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

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    CarServiceInterface carService;
    
    @Autowired
    LeaseServiceInterface leaseService;
    
    @Autowired
    ServiceCheckInterface serviceCheckService;
    
    @Autowired
    PersonServices personService;

    @Autowired
    public CarController(CarServiceInterface carService,
            ServiceCheckInterface serviceCheckService,
            LeaseServiceInterface leaseService,
            PersonServices personService) {
        this.carService = carService;
        this.leaseService = leaseService;
        this.serviceCheckService = serviceCheckService;
        this.personService = personService;
    }

    @ModelAttribute("car")
      public CarDTO getCar()
      {
        CarDTO car = new CarDTO();
        
        return car;
    }
     
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String carsHome(Model model)
    {
        
        return "carListCars";
    }
    
    @RequestMapping(value = "/listCars", method = RequestMethod.GET)
    public ModelAndView listCars(ModelMap model,
            @RequestParam(value = "isInactive", required = false) boolean isInactive) {

        List<CarDTO> cars = carService.findAllCars(isInactive);
        model.addAttribute("cars", cars);
        
        //send mav to jsp page
        return new ModelAndView("cars");
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addCar(@ModelAttribute CarDTO car,
             ModelMap model) {

        model.addAttribute("car", new CarDTO());
        
        carService.createCar(car);

        return "/car/listCars";
    }
    
//    @RequestMapping(method = RequestMethod.POST)
//    public String home() {
//        return "index";
//    }

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
        } catch (DataAccessException | NumberFormatException | NullPointerException e) {

            deleted = false;
            errorMsg = e.getMessage();
        }

        model.addAttribute("deleteStatus", deleted);
        if (errorMsg != null) {
            model.addAttribute("errorMessage", errorMsg);
        }
        return "redirect:/car/listCars";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update_form(@PathVariable Integer id, ModelMap model) {
        CarDTO car = carService.getCarByID(id);
        model.addAttribute("car", car);
        return "car/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateCar(@ModelAttribute CarDTO car,
            BindingResult result, ModelMap model, @PathVariable Integer id) {

        carService.updateCar(car, id);

        return "redirect:/car/listCars";
    }

}
