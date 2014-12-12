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
import java.util.Calendar;
import java.util.Date;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String scHome(ModelMap model) {
        List<ServiceCheckDTO> checks = serviceCheckService.findAllChecks();
        model.addAttribute("checks", checks);

        return "scListServiceChecks";
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
            if (errorMsg != null) {
                errorMsg = e.getMessage();
            }
        }
        if (errorMsg != null) {
            model.addAttribute("errorMessage", errorMsg);
        }
        return "redirect:list";
    }

    @RequestMapping(value = "/listServiceChecks", method = RequestMethod.GET)
    public ModelAndView listServiceChecks(ModelMap model,
            @RequestParam("car") CarDTO car) {

        List<ServiceCheckDTO> checks = serviceCheckService.getServiceChecksForCar(car);
        model.addAttribute("checks", checks);

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

    @RequestMapping(value = "/perform/{id}", method = RequestMethod.GET)
    public String addCheck(@PathVariable Integer id, ModelMap model) {

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
}
