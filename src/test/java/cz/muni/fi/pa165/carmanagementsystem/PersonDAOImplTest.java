/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carmanagementsystem;

import cz.muni.fi.pa165.carmanagementsystem.DAO.PersonDAOImpl;
import cz.muni.fi.pa165.carmanagementsystem.Entities.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Martin Strecansky
 */
public class PersonDAOImplTest {
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testInsertPerson() {
        // create correct person
        Person person = new Person();
        // create new PersonDAO
        PersonDAOImpl dao = new PersonDAOImpl();
        // persist this person
        dao.insertPerson(person);
        // get this person from database
        Person person1 = dao.getPersonByID(person.getId());
        // person1 should be not null
        assertNotNull(person1);
        // person and person1 should be the same
        assertEquals(person, person1);
        
    }
    @Test
    public void testUpdatePerson(){
        // create correct person
        Person person = new Person();
        person.setName("JOHN");
        // create new PersonDAO
        PersonDAOImpl dao = new PersonDAOImpl();
        // persist this person
        dao.insertPerson(person);
        // create updated person
        Person person1 = new Person();
        person1.setName("JOHNY");
        // update person
        dao.updatePerson(person1, person.getId());
        // name of person should be JOHNY now
        assertEquals(person.getName(), "JOHNY");        
        
    }
    
    @Test
    public void testDeletePerson(){
        // create correct person
        Person person = new Person();
        // create new PersonDAO
        PersonDAOImpl dao = new PersonDAOImpl();
        // persist this person
        dao.insertPerson(person);
        // delete this person
        dao.deletePerson(person.getId());
        // person shoul be deleted
        assertNull(person);
        
        
    }
}
