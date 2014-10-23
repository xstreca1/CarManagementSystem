/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carmanagementsystem;

import cz.muni.fi.pa165.carmanagementsystem.DAO.PersonDAOImpl;
import cz.muni.fi.pa165.carmanagementsystem.Entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
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

    //@PersistenceUnit
    //EntityManagerFactory emf;
    private EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("carManagementSystem-unit");

    @Before
    public void setUp() {
        Person person = new Person();
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        em.close();

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
    public void testUpdatePerson() {
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
    public void testDeletePerson() {
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

    @Test
    public void testGetPersonByID() {
        // create several persons
        Person p1 = new Person();
        Person p2 = new Person();
        Person p3 = new Person();
        // create dao
        PersonDAOImpl dao = new PersonDAOImpl();
        // persist persons
        dao.insertPerson(p1);
        dao.insertPerson(p2);
        dao.insertPerson(p3);
        // get persons by ID, they should be equal
        Person p1ByID = dao.getPersonByID(p1.getId());
        assertEquals(p1, p1ByID);
        Person p2ByID = dao.getPersonByID(p2.getId());
        assertEquals(p1, p1ByID);
        Person p3ByID = dao.getPersonByID(p3.getId());
        assertEquals(p1, p1ByID);

    }

    @Test
    public void testGetPeopleByName() {
        // create several persons with same name
        Person p1 = new Person();
        Person p2 = new Person();
        Person p3 = new Person();
        p1.setName("JOHN");
        p2.setName("JOHN");
        p3.setName("JOHN");
        //create several persons with other name
        Person p4 = new Person();
        p4.setName("X");
        Person p5 = new Person();
        p5.setName("Y");
        // create dao
        PersonDAOImpl dao = new PersonDAOImpl();
        // persist persons
        dao.insertPerson(p1);
        dao.insertPerson(p2);
        dao.insertPerson(p3);
        // get persons by ID
        List<Person> list = dao.getPeopleByName("JOHN");
        // list should not be empty
        assertNotNull(list);
        // list should contain exactly 3 persons
        int persons = list.size();
        assertEquals(persons, 3);
    }

    @Test
    public void testGetAllPeople() {
        // create several persons
        Person p1 = new Person();
        Person p2 = new Person();
        Person p3 = new Person();
        // create dao
        PersonDAOImpl dao = new PersonDAOImpl();
        // persist persons
        dao.insertPerson(p1);
        dao.insertPerson(p2);
        dao.insertPerson(p3);
        // get persons 
        List<Person> list = dao.getAllPeople();
        // list should not be empty
        assertNotNull(list);
        // list should contain exactly 3 persons
        int persons = list.size();
        assertEquals(persons, 3);
    }
}
