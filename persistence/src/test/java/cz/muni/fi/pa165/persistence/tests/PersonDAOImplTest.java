/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.persistence.tests;

import cz.muni.fi.pa165.persistence.DAO.PersonDAOImpl;

import cz.muni.fi.pa165.persistence.Entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Martin Strecansky
 */
public class PersonDAOImplTest {

    //private EntityManagerFactory emf;
    private EntityManager em;
    private PersonDAOImpl dao;

    private static Person person;
    private static Person person2;
    private static Person person3;
    private static Person person4;
    private static Person toInsert;
    private static Person toUpdate;

    @Before
    public void setUpClass() {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("Test-unit");
        em = emf.createEntityManager();
        dao = new PersonDAOImpl(em);

        //create simple person        
        person = new Person();
        person.setName("JOHN");       
        person.setPosition("HR");
        person.setNationality("US");
        person.setSalary(25_000);
        person.setIsActive(true);
        person.setIdentificationNumber("EA123456");
        person.setUsername("jaro");

        person2 = new Person();
        person2.setName("JOE");        
        person2.setPosition("Marketing");
        person2.setNationality("US");
        person2.setSalary(30_000);
        person2.setIsActive(true);
        person2.setIdentificationNumber("EA212143");
        person2.setUsername("martin");

        person3 = new Person();
        person3.setName("JOE");
        person3.setPosition("Support");
        person3.setNationality("US");
        person3.setSalary(33_000);
        person3.setIsActive(true);
        person3.setIdentificationNumber("EA142234");
        person3.setUsername("stano");

        person4 = new Person();
        person4.setName("JOE");
        person4.setPosition("QA");
        person4.setNationality("US");
        person4.setSalary(38_000);
        person4.setIsActive(true);
        person4.setIdentificationNumber("EA654321");
        person4.setUsername("chruno");

        em.getTransaction().begin();

        //persist them
        em.persist(person);
        em.persist(person2);
        em.persist(person3);
        em.persist(person4);

        em.getTransaction().commit();

        //create DAO object
        //dao = new PersonDAOImpl();
        //create person which is not persisted in database to test insertPerson method
        toInsert = new Person();
        toInsert.setName("TEST");
        toInsert.setPosition("Developer");
        toInsert.setNationality("US");
        toInsert.setSalary(45_000);
        toInsert.setIsActive(true);
        toInsert.setIdentificationNumber("EA545454");

        toUpdate = new Person();
        toUpdate.setName("UPDATE");
        toUpdate.setPosition("Developer");
        toUpdate.setNationality("CZ");
        toUpdate.setSalary(46_000);
        toUpdate.setIsActive(true);
        toUpdate.setIdentificationNumber("EA342235");

    }

    @After
    public void tearDown() {

        em.getTransaction().begin();
        em.createQuery("DELETE FROM Person").executeUpdate();
        em.getTransaction().commit();

    }

    @Test 
    // test if entities created in setUp() method are persisted in database
    public void arePersisted() {

        em.getTransaction().begin();
        List<Person> people = em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
        assertEquals(people.size(), 4);
        em.getTransaction().commit();

    }

    @Test
    // test if it is possible to persist person using method insertPerson()
    public void testInsertPerson() {
        // try if bad input results in IllegalArgument Exception
        try {
            dao.insertPerson(null);
            fail("wrong input allowed!");
        } catch (IllegalArgumentException e) {
        }

        em.getTransaction().begin();
        // persist person toInsert
        dao.insertPerson(toInsert);
        // find person toInsert in database

        Person pers = em.find(Person.class, toInsert.getId());
        Assert.assertTrue(em.contains(pers));
        em.getTransaction().commit();

    }

    @Test
    // test if it is possible to get person from DB using getPersonById()
    public void testGetPersonById() {
        // try if bad input results in Exception
        try {
            dao.getPersonByID(null);
            fail("wrong input allowed!");
        } catch (IndexOutOfBoundsException e) {
        }

        // get person using getPersonById method
        Person pers = dao.getPersonByID(person.getId());
        // pers should be not null
        assertNotNull(pers);
        // person and person1 should be the same
        assertEquals(person, pers);

    }

    @Test
    public void testUpdatePerson() {
        // try if bad input results in Exception
        try {
            dao.updatePerson(null, person2.getId());
            fail("wrong input allowed!");
        } catch (IllegalArgumentException e) {
        } catch (NullPointerException e) {
        }

        // try if bad input results in Exception
        try {
            dao.updatePerson(toUpdate, null);
            fail("wrong input allowed!");
        } catch (IndexOutOfBoundsException e) {
        } catch (IllegalArgumentException e) {
        }

        // update persisted person with new non-persisted person toInsert
        dao.updatePerson(toUpdate, person2.getId());

        em.getTransaction().begin();

        Person updatedPerson = em.find(Person.class, person2.getId());
        // attributes e of person should be updated now
        assertEquals(updatedPerson.getName(), toUpdate.getName());
        assertEquals(updatedPerson.getPosition(), toUpdate.getPosition());
        assertEquals(updatedPerson.getNationality(), toUpdate.getNationality());
        assertEquals(updatedPerson.getSalary(), toUpdate.getSalary());
        em.getTransaction().commit();

    }

    @Test
    public void testGetPeopleByName() {
        // try if bad input results in Exception
        try {
            dao.getPeopleByName(null);
            fail("wrong input allowed!");
        } catch (IllegalArgumentException e) {
        }

        // get people by name
        List<Person> list = dao.getPeopleByName("JOE");
        // list should not be empty
        assertNotNull(list);
        // list should contain exactly 3 persons
        int persons = list.size();
        assertEquals(persons, 3);

    }

}
