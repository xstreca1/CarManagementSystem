/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carmanagementsystem;

import cz.muni.fi.pa165.CMSPersistenceLayer.DAO.PersonDAOImpl;
import cz.muni.fi.pa165.CMSPersistenceLayer.Entities.Address;
import cz.muni.fi.pa165.CMSPersistenceLayer.Entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.transaction.NotSupportedException;
import org.junit.After;
import org.junit.Assert;
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

    private static PersonDAOImpl dao = 
            new PersonDAOImpl(Persistence.createEntityManagerFactory("carManagementSystem-unit"));

    private static Person person;
    private static Person person2;
    private static Person person3;
    private static Person person4;
    private static Person toInsert;

    @BeforeClass
    public static void setUpClass() {
        EntityManager em = dao.getEntityManagerFactory().createEntityManager();
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
        //dao = new PersonDAOImpl();
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

    @Test // PASS
    // test if entities created in setUp() method are persisted in database
    public void arePersisted() {

        EntityManager em = dao.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        List<Person> people = em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
        assertEquals(people.size(), 4);
        em.getTransaction().commit();
        em.close();
    }

    @Test // PASS
    // test if it is possible to persist person using method insertPerson()
    public void testInsertPerson() {
        // try if bad input results in IllegalArgument Exception
        try{
        dao.insertPerson(null); 
        fail("wrong input allowed!");
        }
        catch(IllegalArgumentException e){}
        
        // persist person toInsert
        dao.insertPerson(toInsert);
        // find person toInsert in database
        EntityManager em = dao.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Person pers = em.find(Person.class, toInsert.getId());
        Assert.assertTrue(em.contains(pers));
        em.getTransaction().commit();
        em.close();

    }

    @Test//FAIL
    // test if it is possible to get person from DB using getPersonById()
    public void testGetPersonById() {
        // try if bad input results in Exception
        try{
        dao.getPersonByID(null); 
        fail("wrong input allowed!");
        }        
        catch(IndexOutOfBoundsException e){}
        
        // get person using getPersonById method
        Person pers = dao.getPersonByID(person.getId());
        // pers should be not null
        assertNotNull(pers);
        // person and person1 should be the same
        assertEquals(person, pers);

    }

    @Test//FAIL
    // tests if person can be deleted using method deletePerson()
    public void testDeletePerson() {
        // try if bad input results in Exception
        try{
        dao.deletePerson(null); 
        fail("wrong input allowed!");
        }        
        catch(IndexOutOfBoundsException e){}
        
        // delete person
        dao.deletePerson(person.getId());
        // person shoul be deleted
        EntityManager em = dao.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Assert.assertFalse(em.contains(person));
        em.getTransaction().commit();
        em.close();
    }

    @Test//FAIL
    // tests if all people can be retrieved from database using metho getAllpeople()
    public void testGetAllPeople() {

        List<Person> people = dao.getAllPeople();
        assertEquals(people.size(), 4);

    }

    @Test//FAIL
    public void testUpdatePerson() {
        // try if bad input results in Exception
        try{
        dao.updatePerson(null, person.getId()); 
        fail("wrong input allowed!");
        }        
        catch(IllegalArgumentException e){}
        catch(NullPointerException e){}
        
        // try if bad input results in Exception
        try{
        dao.updatePerson(toInsert, null); 
        fail("wrong input allowed!");
        }        
        catch(IndexOutOfBoundsException e){}
        catch(IllegalArgumentException e){}
        
        // update persisted person with new non-persisted person toInsert
        dao.updatePerson(toInsert, person.getId());
        // attributes e of person should be updated now
        assertEquals(person.getName(), "TEST");
        assertEquals(person.getPosition(), "Developer");
        assertEquals(person.getNationality(), "US");
        assertEquals(person.getSalary(), 45_000);
    }

    @Test//FAIL
    public void testGetPeopleByName() {
        // try if bad input results in Exception
        try{
        dao.getPeopleByName(null); 
        fail("wrong input allowed!");
        }        
        catch(IllegalArgumentException e){}
        
        // get people by name
        List<Person> list = dao.getPeopleByName("JOE");
        // list should not be empty
        assertNotNull(list);
        // list should contain exactly 3 persons
        int persons = list.size();
        assertEquals(persons, 3);

    }
    
    
}
