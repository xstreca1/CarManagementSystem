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
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.validation.Valid;
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
@RequestMapping("/person")
public class PersonController {

    CarServiceInterface carService;
    LeaseServiceInterface leaseService;
    ServiceCheckInterface serviceCheckService;
    PersonServices personService;

    @Autowired
    public PersonController(CarServiceInterface carService,
            ServiceCheckInterface serviceCheckService,
            LeaseServiceInterface leaseService,
            PersonServices personService) {
        this.carService = carService;
        this.leaseService = leaseService;
        this.serviceCheckService = serviceCheckService;
        this.personService = personService;
    }

    //Controlelr displaying table of all users
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String carsHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<PersonDTO> people = personService.findAllPeople(true);
        request.setAttribute("people", people);

        return "personListPeople";
    }

    @ModelAttribute("person")
    public PersonDTO getPerson() {
        PersonDTO person = new PersonDTO();

        return person;
    }

    // Add person form
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("person") @Valid PersonDTO person,
            BindingResult result, ModelMap model, HttpServletRequest request) {

        if (result.hasErrors()) {

            List<PersonDTO> people = personService.findAllPeople(true);
            request.setAttribute("people", people);
            return "personListPeople";
        } else {

            personService.createPerson(person);

            return "redirect:/person/";
        }
    }

    // delete person 
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/delete/{id}")
    public String deletePerson(@PathVariable Integer id, ModelMap model) {

        PersonDTO person1 = personService.getPersonByID(id);
        model.addAttribute("person1", person1);
        person1.setIsActive(false);
        personService.editPerson(person1, id);

        return "redirect:/person/";
    }

    // Update person form
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update_form(@PathVariable Integer id, HttpServletRequest request) {

        List<PersonDTO> people = new ArrayList();
        people.add(personService.getPersonByID(id));
        request.setAttribute("people", people);
        //model.addAttribute("person2", person2);
        return "personEdit";
    }

    
    // confirm update button
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editPerson(@PathVariable Integer id, @ModelAttribute("person") @Valid PersonDTO person,
            BindingResult result, ModelMap model, HttpServletRequest request) {

        if (result.hasErrors()) {
            List<PersonDTO> people = new ArrayList();
            people.add(personService.getPersonByID(id));
            request.setAttribute("people", people);
            //model.addAttribute("person2", person2);
            return "personEdit";

        } else {
            personService.editPerson(person, id);

            return "redirect:/person/";
        }
    }

    // Statistisc for user
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/statistics/{id}", method = RequestMethod.GET)
    public String getStatistics(ModelMap model, @PathVariable Integer id) {

        PersonDTO person = personService.getPersonByID(id);
        List<LeaseDTO> leases = leaseService.getLeaseByPerson(person);
        model.addAttribute("leases", leases);

        return "personStatistics";
    }

 }
