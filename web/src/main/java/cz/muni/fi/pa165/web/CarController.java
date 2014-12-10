/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.web;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.Lease;
import cz.muni.fi.pa165.service.dto.CarDTO;
import cz.muni.fi.pa165.service.dto.LeaseDTO;
import cz.muni.fi.pa165.service.service.CarServiceInterface;
import cz.muni.fi.pa165.service.service.LeaseServiceInterface;
import cz.muni.fi.pa165.service.service.PersonServices;
import cz.muni.fi.pa165.service.service.ServiceCheckInterface;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
    public CarDTO getCar() {
        CarDTO car = new CarDTO();

        return car;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String carsHome(HttpServletRequest request) {
        List<CarDTO> cars = carService.findAllCars(true);
        request.setAttribute("cars", cars);

        return "carListCars";
    }

    /*@RequestMapping(value = "/listCars", method = RequestMethod.GET)
     public ModelAndView listCars(ModelMap model,
     @RequestParam(value = "isInactive", required = false) boolean isInactive) {

     List<CarDTO> cars = carService.findAllCars(isInactive);
     model.addAttribute("cars", cars);

     //send mav to jsp page
     return new ModelAndView("cars");
     }*/
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCar(@ModelAttribute CarDTO car,
            ModelMap model) {

        model.addAttribute("car", new CarDTO());

        carService.createCar(car);

        return "redirect:/car/";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteCar(@PathVariable Integer id, ModelMap model) {

        CarDTO car1 = carService.getCarByID(id);
        model.addAttribute("car1", car1);
        car1.setIsActive(false);
        carService.updateCar(car1, id);

        return "redirect:/car/";
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public String home() {
//        return "index";
//    }

    /*@RequestMapping(value = "/delete/{id}")
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
     }*/
    /*@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
     public String update_form(@PathVariable Integer id, ModelMap model) {
     CarDTO car = carService.getCarByID(id);
     model.addAttribute("car", car);
     return "car/update";
     }

     /*@RequestMapping(value = "/update", method = RequestMethod.POST)
     public String updateCar(@ModelAttribute CarDTO car,
     BindingResult result, ModelMap model, @PathVariable Integer id) {

     carService.updateCar(car, id);

     return "redirect:/car/listCars";
     }*/
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update_form(@PathVariable Integer id, HttpServletRequest request) {

        List<CarDTO> cars = new ArrayList();
        cars.add(carService.getCarByID(id));
        request.setAttribute("cars", cars);
        //model.addAttribute("person2", person2);
        return "carEdit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editCar(@ModelAttribute("car") CarDTO car, @PathVariable Integer id,
            BindingResult result, ModelMap model) {

        carService.updateCar(car, id);

        return "redirect:/car/";

    }

    @RequestMapping(value = "/lease/{id}", method = RequestMethod.GET)
    public String leaseCar(@PathVariable Integer id, ModelMap model) {

        List<CarDTO> cars = new ArrayList();
        cars.add(carService.getCarByID(id));
        model.addAttribute("cars", cars);

        LeaseDTO lease = new LeaseDTO();
        leaseService.createLease(lease);
        model.addAttribute("lease", lease);
        //model.addAttribute("person2", person2);
        return "leaseCar";
    }
    
    @RequestMapping(value = "/confirmLease/{id}", method = RequestMethod.POST)
    public String confirmLeaseCar(@PathVariable Integer id,@ModelAttribute("lease") LeaseDTO lease,
            ModelMap model) {

        model.addAttribute("lease", lease);

        leaseService.createLease(lease);
        
        return "redirect:/lease/";
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
