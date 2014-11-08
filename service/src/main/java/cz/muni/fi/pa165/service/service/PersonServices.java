/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.persistence.Entities.Lease;
import cz.muni.fi.pa165.persistence.Entities.Person;
import cz.muni.fi.pa165.service.dto.PersonDTO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jakub Rumanovsky
 */
public interface PersonServices {
    
    /**
     * Method creates a new person instance and stores it to database
     * @param all the mandatory attributes for person
     * @return true if no error occurs
     */
    boolean createPerson(PersonDTO PersonDto);
    
    /**
     * return all leases (travel stats) for selected person
     * @param person person, for which travel stats will be returned
     * @param from the date from which the travel stats will be returned
     * @param to the date to which the travel stats will be returned
     * @return 
     */

    /**
     * edit the person selected by personID
     * @param person instance that will replace the current instace 
     * @param personID id of current instance
     * @return 
     */
    boolean editPerson(PersonDTO personDto, Integer personID);
    //spytat sa ci je to takto vporiadku

    /**
     * if person no longer works in a company, 
     * it should not be deleted (because of travel statistics binded to it)
     * so the system only deactivate the person
     * @param person person to deactivate
     * @return true if everything is ok
     */

    /**
     * get all people from the database
     * @param alsoInactive if true, it returns also people, who are currently 
     * deactivated, if false, it returns only people who are active in a company
     * @return list of people
     */
    List<PersonDTO> findAllPeople(boolean alsoInactive);

    /**
     * return people who has a name as in a parameter
     * @param name name of a person searched for
     * @return list of people, who have selected name
     */
    List<PersonDTO> getPeopleByName(String name);

}
