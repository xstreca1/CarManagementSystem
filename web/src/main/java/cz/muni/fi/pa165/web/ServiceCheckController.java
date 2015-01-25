/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.web;


import cz.muni.fi.pa165.service.dto.ServiceCheckDTO;
import cz.muni.fi.pa165.service.service.CarServiceInterface;
import cz.muni.fi.pa165.service.service.LeaseServiceInterface;
import cz.muni.fi.pa165.service.service.PersonServices;
import cz.muni.fi.pa165.service.service.ServiceCheckInterface;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    // List of SC
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String scHome(ModelMap model) {
        List<ServiceCheckDTO> checks = serviceCheckService.findAllChecks();

        Collections.sort(checks, new DateComparator());
        model.addAttribute("checks", checks);

        return "scListServiceChecks";
    }

    // Performing SC - last check is set to actual date, next check is calculated ad lastcheck + interval
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/perform/{id}", method = RequestMethod.GET)
    public String perform(@PathVariable Integer id, ModelMap model) {

        ServiceCheckDTO check = serviceCheckService.getCheckByID(id);

        // Create calendar and get currnet date
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // set current Date as Date of Last control
        check.setLastCheck(currentDate);

        //find out interval of check
        int interval = check.getServiceInterval();
        //add value of interval to currnet date

        Calendar nextControl = Calendar.getInstance();
        nextControl.setTime(currentDate);
        nextControl.add(Calendar.MONTH, interval);

        // get date with added interval
        Date addedMonths = nextControl.getTime();

        // set new date of next control        
        check.setNextCheck(addedMonths);

        serviceCheckService.updateCheck(check, id);

        return "redirect:/serviceCheck/";
    }

    //Performing SC from cars view (Cars - display SC - Perform)
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/performFromCar/{id}", method = RequestMethod.GET)
    public String performFromCarView(@PathVariable Integer id, ModelMap model) {

        ServiceCheckDTO check = serviceCheckService.getCheckByID(id);

        // Create calendar and get currnet date
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // set current Date as Date of Last control
        check.setLastCheck(currentDate);

        //find out interval of check
        int interval = check.getServiceInterval();
        //add value of interval to currnet date

        Calendar nextControl = Calendar.getInstance();
        nextControl.setTime(currentDate);
        nextControl.add(Calendar.MONTH, interval);

        // get date with added interval
        Date addedMonths = nextControl.getTime();

        // set new date of next control        
        check.setNextCheck(addedMonths);

        serviceCheckService.updateCheck(check, id);

        return "redirect:/car/";
    }

}
