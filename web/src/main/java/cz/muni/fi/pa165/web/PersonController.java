/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.web;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import cz.muni.fi.pa165.persistence.Entities.Person;
import cz.muni.fi.pa165.service.dto.LeaseDTO;
import cz.muni.fi.pa165.service.dto.PersonDTO;
import cz.muni.fi.pa165.service.service.CarServiceInterface;
import cz.muni.fi.pa165.service.service.LeaseServiceInterface;
import cz.muni.fi.pa165.service.service.PersonServices;
import cz.muni.fi.pa165.service.service.ServiceCheckInterface;
import java.io.IOException;
import static java.lang.Math.log;
import static java.rmi.server.LogStream.log;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static javax.swing.text.StyleConstants.ModelAttribute;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

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

    /*@RequestMapping(value = "/listPeople", method = RequestMethod.GET)
     public ModelAndView listPeople(ModelMap model,
     @RequestParam(value = "isInactive", required = false) boolean isInactive) {

     List<PersonDTO> people = personService.findAllPeople(isInactive);
     model.addAttribute("people", people);

     return new ModelAndView("listPeople");
     }*/
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

    @RequestMapping(value = "/delete/{id}")
    public String deletePerson(@PathVariable Integer id, ModelMap model) {

        PersonDTO person1 = personService.getPersonByID(id);
        model.addAttribute("person1", person1);
        person1.setIsActive(false);
        personService.editPerson(person1, id);

        return "redirect:/person/";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update_form(@PathVariable Integer id, HttpServletRequest request) {

        List<PersonDTO> people = new ArrayList();
        people.add(personService.getPersonByID(id));
        request.setAttribute("people", people);
        //model.addAttribute("person2", person2);
        return "personEdit";
    }

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

    @RequestMapping(value = "/statistics/{id}", method = RequestMethod.GET)
    public String getStatistics(ModelMap model, @PathVariable Integer id) {

        PersonDTO person = personService.getPersonByID(id);
        List<LeaseDTO> leases = leaseService.getLeaseByPerson(person);
        model.addAttribute("leases", leases);

        return "personStatistics";
    }

    /*@RequestMapping(value = "/update", method = RequestMethod.POST)
     public String updatePerson(@ModelAttribute("person") PersonDTO person,
     BindingResult result, ModelMap model, @PathVariable Integer id) {

     if (result.hasErrors()) {
     for (ObjectError ge : result.getGlobalErrors()) {

     }
     for (FieldError fe : result.getFieldErrors()) {

     }
     return person.getId() == null ? "person/list" : "person/edit";
     }

     personService.editPerson(person, id);

     return "redirect:/person/listPeople";
     }

     @RequestMapping(value = "/listPeopleByName", method = RequestMethod.GET)
     public ModelAndView listPeopleByName(ModelMap model,
     @RequestParam("name") String name) {

     List<PersonDTO> namedPersonNew = personService.getPeopleByName(name);
     model.addAttribute("getPeopleByName", namedPersonNew);

     return new ModelAndView("getPeopleByName");
     }*/
}
