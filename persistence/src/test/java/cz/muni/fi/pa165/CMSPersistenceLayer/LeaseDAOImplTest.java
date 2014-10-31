/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.CMSPersistenceLayer;

import cz.muni.fi.pa165.persistence.DAO.LeaseDAOImpl;
import cz.muni.fi.pa165.persistence.Entities.Address;
import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.Lease;
import cz.muni.fi.pa165.persistence.Entities.Person;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jozef.puchly
 */
public class LeaseDAOImplTest {
    
     private static LeaseDAOImpl dao = 
            new LeaseDAOImpl(Persistence.createEntityManagerFactory("carManagementSystem-unit"));

    private static Lease lease;
    private static Lease lease2;
    private static Lease toInsert;
    private static Person person;
    private static Date date1 = new Date(1500000);
    private static Date date2 = new Date(1600000);
    private static Date date3 = new Date(1700000);
    private static Date date4 = new Date(1800000);
    private static Car  car1 = new Car();
    
    
    @BeforeClass
    public static void setUpClass() {
        EntityManager em = dao.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        //create lease - two of them       
        lease = new Lease();
        lease.setCarMileage(car1.getMileage());
        lease.setDateOfLease(date1);
        lease.setDateOfReturn(date2);
        lease.setIsClosed(true);
        lease.setCar(car1);
        
        lease2 = new Lease();
        lease2.setCarMileage(car1.getMileage());
        lease.setDateOfLease(date3);
        lease.setDateOfReturn(date4);
        lease.setIsClosed(true);
        lease.setCar(car1);
        
        //create car
        car1.setAvailibility(true);
        car1.setBrand("Aston Martin");
        car1.setTypeName("DB9");
        car1.setVIN("AJGSKA1234FX");
        car1.setVehicleRegPlate("ZC-111BA");
        car1.setYearOfManufacture(2014);
        car1.setEngineDisplacement((float) 3.6);
        car1.setEnginePower(202);
        car1.setGasConsumption((float) 10.3);
        car1.setMileage(55000);
        car1.setNumberOfSeats(4);
        car1.setTransmission(true);
        
        //create person
        person = new Person();
        person.setName("JOHN");
        person.setAddress(new Address());
        person.setPosition("HR");
        person.setNationality("US");
        person.setSalary(25_000);
        
        //persist leases
        em.persist(lease);
        em.persist(lease2);
        em.persist(car1);
        em.persist(person);
        em.getTransaction().commit();
        em.close();

        //create DAO object
        toInsert = new Lease();
        toInsert.setCarMileage(car1.getMileage());
        toInsert.setDateOfLease(date3);
        toInsert.setDateOfReturn(date4);
        toInsert.setIsClosed(true);
        toInsert.setCar(car1);
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

    /**
     * Test of createLease method, of class LeaseDAOImpl.
     */
    @Test
    public void testIsInDB() {
        EntityManager em = dao.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        List<Lease> leases = em.createQuery("SELECT l FROM Lease l", Lease.class).getResultList();
        assertEquals(leases.size(), 2);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testCreateLease() {
        dao.createLease(toInsert);

        EntityManager em = dao.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Lease lease3 = em.find(Lease.class, toInsert.getId());
        Assert.assertTrue(em.contains(lease3));
        em.getTransaction().commit();
        em.close();
    }
    
    /**
     * Test of updateLease method, of class LeaseDAOImpl.
     */
/**    @Test
    public void testUpdateLease() {
        System.out.println("updateLease");
        Lease lease = null;
        int leaseId = 0;
        LeaseDAOImpl instance = new LeaseDAOImpl();
        instance.updateLease(lease, leaseId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
    /**
     * Test of deleteLease method, of class LeaseDAOImpl.
     */
    @Test
    public void testDeleteLease() {
        Integer id = lease.getLeaseId();
        dao.deleteLease(lease.getLeaseId());
        assertNull(dao.getLeaseByID(id));
    }

    /**
     * Test of getLeasesByPerson method, of class LeaseDAOImpl.
     */
    @Test
    public void testGetLeasesByPerson() {
        List<Lease> leasesByPerson = dao.getLeasesByPerson(person.getId());
        assertEquals(1, leasesByPerson.size());
    }

    /**
     * Test of getAllLeases method, of class LeaseDAOImpl.
     */
    @Test
    public void testGetAllLeases() {
       List<Lease> leases = dao.getAllLeases(date1, date4);
       assertEquals(1, leases.size());
    }
    
}
