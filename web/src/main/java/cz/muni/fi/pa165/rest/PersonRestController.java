/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.rest;

import cz.muni.fi.pa165.rest_messages.ReceiveActivePeopleListMessage;
import cz.muni.fi.pa165.rest_messages.ReceivePersonMessage;
import cz.muni.fi.pa165.service.dto.PersonDTO;
import cz.muni.fi.pa165.service.service.PersonServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jrumanov
 */
@RestController
@RequestMapping("/rest/person")
public class PersonRestController {

    @Autowired
    PersonServices service;

    @RequestMapping("/get")
    public ReceivePersonMessage getPersonById(@RequestParam(required = true) int id) {
        PersonDTO person;
        ReceivePersonMessage result = new ReceivePersonMessage();

        try {
            person = service.getPersonByID(id);
            result.setObject(person);
            result.setSuccess(true);
        } catch (Exception ex) {
            result.setMessage(ex.getMessage());
            result.setObject(null);
            result.setSuccess(false);
        }

        return result;
    }

    @RequestMapping("/getByName")
    public ReceiveActivePeopleListMessage getPersonByName(@RequestParam(required = true) String name) {
        PersonDTO person;
        ReceiveActivePeopleListMessage result = new ReceiveActivePeopleListMessage();

        try {
            List<PersonDTO> list = service.getPeopleByName(name);
            result.setList(list);
            result.setSuccess(true);
        } catch (Exception ex) {
            result.setMessage(ex.getMessage());
            result.setList(null);
            result.setSuccess(false);
        }

        return result;
    }

    @RequestMapping("/getAll")
    public ReceiveActivePeopleListMessage getAllPeople() {
        ReceiveActivePeopleListMessage result = new ReceiveActivePeopleListMessage();

        try {
            List<PersonDTO> list = service.findAllPeople(true);
            result.setList(list);
            result.setSuccess(true);
        } catch (Exception ex) {
            result.setMessage(ex.getMessage());
            result.setList(null);
            result.setSuccess(false);
        }

        return result;
    }

    //have to be clarified if the ID can be passed that way
    @RequestMapping("/update")
    public ReceivePersonMessage updateCar(@RequestBody PersonDTO person) {

        ReceivePersonMessage result = new ReceivePersonMessage();

        try {
            Integer id = person.getId();
            service.editPerson(person, id);
            result.setObject(person);
            result.setSuccess(true);

        } catch (Exception ex) {
            result.setMessage(ex.getMessage());
            result.setObject(null);
            result.setSuccess(false);
        }

        return result;
    }

    @RequestMapping("/delete")
    public ReceivePersonMessage deletePerson(@RequestBody PersonDTO person) {
        /*ReceivePersonMessage result = new ReceivePersonMessage();
        
         try
         {
         service.(person);
         result.setObject(person);
         }
         catch(Exception ex)
         {
         result.setMessage(ex.getMessage());
         result.setObject(null);
         }
        
         return result;*/

        //!!!! missing delete method in service layer - ask xstreca1
        return null;
    }

    @RequestMapping("/add")
    public ReceivePersonMessage addPerson(@RequestBody PersonDTO person) {
        ReceivePersonMessage result = new ReceivePersonMessage();

        try {
           
            PersonDTO newPerson = service.createPerson(person);
            result.setObject(newPerson);
            result.setSuccess(true);
        } catch (Exception ex) {
            result.setMessage(ex.getMessage());
            result.setObject(null);
            result.setSuccess(false);
        }

        return result;
    }
}
