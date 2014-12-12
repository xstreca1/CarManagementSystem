/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.web;

import cz.muni.fi.pa165.service.dto.LeaseDTO;
import cz.muni.fi.pa165.service.dto.PersonDTO;
import cz.muni.fi.pa165.service.service.CarServiceInterface;
import cz.muni.fi.pa165.service.service.LeaseServiceInterface;
import cz.muni.fi.pa165.service.service.PersonServices;
import cz.muni.fi.pa165.service.service.ServiceCheckInterface;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/delete/{id}")
    public String deleteLease(@PathVariable String id, ModelMap model) {
        boolean deleted = false;
        String errorMsg = null;
        LeaseDTO lease = new LeaseDTO();
        try {
            Integer leaseID = Integer.valueOf(id);

            lease = leaseService.getLeaseByID(leaseID);
            leaseService.deleteLease(lease);
            deleted = true;
        } catch (DataAccessException | NumberFormatException | NullPointerException e) {

            deleted = false;
            errorMsg = e.getMessage();
        }

        model.addAttribute("deleteStatus", deleted);
        if (errorMsg != null) {
            model.addAttribute("errorMessage", errorMsg);
        }
        return "redirect:/lease/list";
    }

    @RequestMapping(value = "/listLeases", method = RequestMethod.GET)
    public ModelAndView listLeases(ModelMap model,
            @RequestParam("dateFrom") Date dateFrom,
            @RequestParam("dateTo") Date dateTo) {

        List<LeaseDTO> leases = leaseService.getAllLeases(dateFrom, dateTo);
        model.addAttribute("leases", leases);

        return new ModelAndView("listLeases");
    }

    @RequestMapping(value = "/getTravelStatistics", method = RequestMethod.GET)
    public ModelAndView getTravelStatistics(ModelMap model,
            @RequestParam("person") PersonDTO person,
            @RequestParam("dateFrom") Date dateFrom,
            @RequestParam("dateTo") Date dateTo) {

        List<LeaseDTO> travelStatistics = leaseService.getTravelStatistics(person, dateFrom, dateTo);
        model.addAttribute("travelStatistics", travelStatistics);
        //add if requestParam == null then default
        return new ModelAndView("getTravelStatistics");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addLease(ModelMap model,
            @ModelAttribute("lease") LeaseDTO lease) {

        leaseService.createLease(lease);

        return "redirect:list";
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

        return "redirect:/lease/";

    }

}
