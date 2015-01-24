/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.web;

import cz.muni.fi.pa165.service.dto.CarDTO;
import cz.muni.fi.pa165.service.dto.LeaseDTO;

import cz.muni.fi.pa165.service.service.CarServiceInterface;
import cz.muni.fi.pa165.service.service.LeaseServiceInterface;
import cz.muni.fi.pa165.service.service.PersonServices;
import cz.muni.fi.pa165.service.service.ServiceCheckInterface;
import java.util.ArrayList;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 *
 * @author jozef.puchly
 */
@Controller
@RequestMapping("/lease")
public class LeaseController {

    CarServiceInterface carService;
    LeaseServiceInterface leaseService;
    ServiceCheckInterface serviceCheckService;
    PersonServices personService;

    @Autowired
    public LeaseController(CarServiceInterface carService,
            ServiceCheckInterface serviceCheckService,
            LeaseServiceInterface leaseService,
            PersonServices personService) {
        this.carService = carService;
        this.leaseService = leaseService;
        this.serviceCheckService = serviceCheckService;
        this.personService = personService;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String leaseHome(ModelMap model) {               
        
        List<LeaseDTO> leases = leaseService.findAllLeases();
        model.addAttribute("leases", leases);

        return "leaseListLeases";
    }
    @ModelAttribute("lease")
    public LeaseDTO getLease() {
        LeaseDTO lease = new LeaseDTO();

        return lease;
    }
   
    @RequestMapping(value = "/return/{id}", method = RequestMethod.GET)
    public String return_form(@PathVariable Integer id, HttpServletRequest request) {

        List<LeaseDTO> leases = new ArrayList();
        leases.add(leaseService.getLeaseByID(id));
        request.setAttribute("leases", leases);        
        return "leaseReturn";
    }

    @RequestMapping(value = "/confirmReturn/{id}", method = RequestMethod.POST)
    public String returnCar(@ModelAttribute("lease") LeaseDTO lease, @PathVariable Integer id,
            BindingResult result, ModelMap model) {
        
     
        lease.setIsClosed(true);
        leaseService.updateLease(lease, id);
        LeaseDTO updatedLease = leaseService.getLeaseByID(id);
        CarDTO car = updatedLease.getCar();
        car.setAvailibility(true);
        Integer carId = car.getCarID();
        int originalMileage = car.getMileage();
        int newMileage = lease.getDistance();
        int actualMileage = originalMileage + newMileage;
        car.setMileage(actualMileage);
        carService.updateCar(car, carId);

        return "redirect:/lease/";

    }

}
