/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carmanagementsystem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jrumanov
 */
public class CarDAOImplTest {

    public CarDAOImplTest() {
    }

    private EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("carManagementSystem-unit");

    @Before
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        em.getTransaction().commit();
        em.close();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
