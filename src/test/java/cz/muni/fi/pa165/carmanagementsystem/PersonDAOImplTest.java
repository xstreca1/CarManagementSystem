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
import org.junit.After;
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

    private EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("carManagementSystem-unit");

    @Before
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        //create simple person        
        Person person = new Person();
        person.setName("JOHN");
        person.setAddress(new Address());
        person.setPosition("NIKTOS");
        person.setNationality("SR");
        person.setSalary(0);
        

       

        //persist them
        em.persist(person);
       // em.persist(p1);
        //em.persist(p2);
        //em.persist(p3);
        em.getTransaction().commit();
        em.close();

    }

    @After
    public void tearDown() {
        
        


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