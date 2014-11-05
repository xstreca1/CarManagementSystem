/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.Tests;

import cz.muni.fi.pa165.persistence.Entities.Lease;
import cz.muni.fi.pa165.persistence.Entities.Person;
import cz.muni.fi.pa165.service.service.PersonServicesImpl;
import java.util.Date;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jrumanov
 */
public class PersonServicesImplTest {
    
    public PersonServicesImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of createPerson method, of class PersonServicesImpl.
     */
    @Test
    public void testCreatePerson() {
        System.out.println("createPerson");
        PersonServicesImpl instance = new PersonServicesImpl();
        boolean expResult = false;
        boolean result = instance.createPerson();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTravelStatistics method, of class PersonServicesImpl.
     */
    @Test
    public void testGetTravelStatistics() {
        System.out.println("getTravelStatistics");
        Person person = null;
        Date from = null;
        Date to = null;
        PersonServicesImpl instance = new PersonServicesImpl();
        List<Lease> expResult = null;
        List<Lease> result = instance.getTravelStatistics(person, from, to);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editPerson method, of class PersonServicesImpl.
     */
    @Test
    public void testEditPerson() {
        System.out.println("editPerson");
        Person person = null;
        Integer personID = null;
        PersonServicesImpl instance = new PersonServicesImpl();
        boolean expResult = false;
        boolean result = instance.editPerson(person, personID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deactivatePerson method, of class PersonServicesImpl.
     */
    @Test
    public void testDeactivatePerson() {
        System.out.println("deactivatePerson");
        Person person = null;
        PersonServicesImpl instance = new PersonServicesImpl();
        boolean expResult = false;
        boolean result = instance.deactivatePerson(person);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllPeople method, of class PersonServicesImpl.
     */
    @Test
    public void testFindAllPeople() {
        System.out.println("findAllPeople");
        boolean alsoInactive = false;
        PersonServicesImpl instance = new PersonServicesImpl();
        List<Person> expResult = null;
        List<Person> result = instance.findAllPeople(alsoInactive);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPeopleByName method, of class PersonServicesImpl.
     */
    @Test
    public void testGetPeopleByName() {
        System.out.println("getPeopleByName");
        String name = "";
        PersonServicesImpl instance = new PersonServicesImpl();
        List<Person> expResult = null;
        List<Person> result = instance.getPeopleByName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
