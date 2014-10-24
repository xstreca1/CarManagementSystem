/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carmanagementsystem;

import cz.muni.fi.pa165.carmanagementsystem.DAO.PersonDAOImpl;
import cz.muni.fi.pa165.carmanagementsystem.Entities.Address;
import cz.muni.fi.pa165.carmanagementsystem.Entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Martin Strecansky
 */
public class PersonDAOImplTest {

    @PersistenceUnit
    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("carManagementSystem-unit");
    private Person person;
    private Person person2;
    private Person person3;
    private Person person4;
    private Person toInsert;
    private PersonDAOImpl dao;

    @Before
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        //create simple person        
        person = new Person();
        person.setName("JOHN");
        person.setAddress(new Address());
        person.setPosition("HR");
        person.setNationality("US");
        person.setSalary(25_000);

        //create people with same name
        person2 = new Person();
        person2.setName("JOE");
        person2.setAddress(new Address());
        person2.setPosition("Marketing");
        person2.setNationality("US");
        person2.setSalary(30_000);

        person3 = new Person();
        person3.setName("JOE");
        person3.setAddress(new Address());
        person3.setPosition("Support");
        person3.setNationality("US");
        person3.setSalary(33_000);

        person4 = new Person();
        person4.setName("JOE");
        person4.setAddress(new Address());
        person4.setPosition("QA");
        person4.setNationality("US");
        person4.setSalary(38_000);

        //persist them
        em.persist(person);
        em.persist(person2);
        em.persist(person3);
        em.persist(person4);
        em.getTransaction().commit();
        em.close();

        //create DAO object
        dao = new PersonDAOImpl();
        //create person which is not persisted in database to test insertPerson method
        toInsert = new Person();
        toInsert.setName("TEST");
        toInsert.setAddress(new Address());
        toInsert.setPosition("Developer");
        toInsert.setNationality("US");
        toInsert.setSalary(45_000);

    }

    @After
    public void tearDown() {

    }

    @Test
    // test if entities created in setUp() method are persisted in database
    public void arePersisted() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Person> people = em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
        assertEquals(people.size(), 4);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    // test if it is possible to persist person using method insertPerson()
    public void testInsertPerson() {
        // persist person toInsert
        dao.insertPerson(toInsert);
        // find person toInsert in database
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Person pers = em.find(Person.class, toInsert.getId());
        Assert.assertTrue(em.contains(pers));
        em.getTransaction().commit();
        em.close();        
       
    }

    @Test // FAILUJE
    public void testGetAllPeople() {

        PersonDAOImpl dao = new PersonDAOImpl();
        List<Person> people = dao.getAllPeople();
        assertEquals(people.size(), 1);

    }

    @Test // OK
    public void testGetAllPeople2() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Person> people = em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
        assertEquals(people.size(), 1);
        em.getTransaction().commit();
        em.close();

    }
}
