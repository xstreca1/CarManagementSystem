/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.persistence.tests;

import cz.muni.fi.pa165.persistence.Entities.Address;
import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.Lease;
import cz.muni.fi.pa165.persistence.Entities.Person;
import cz.muni.fi.pa165.persistence.DAO.LeaseDAOImpl;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jozef.puchly
 */
public class LeaseDAOImplTest {

    private EntityManager em;
    private LeaseDAOImpl dao;

    private static Lease lease;
    private static Lease lease2;
    private static Lease toInsert;
    private static Lease toUpdate;
    private static Person person;
    private static Date date1;
    private static Date date2;
    private static Date date3;
    private static Date date4;
    private static Car car1;
    private static Car car2;

    @Before
    public void setUpClass() {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("Test-unit");
        em = emf.createEntityManager();
        dao = new LeaseDAOImpl(em);

        date1 = new Date(2014, 1, 1);
        date2 = new Date(2014, 1, 2);
        date3 = new Date(2014, 1, 3);
        date4 = new Date(2014, 1, 4);

        //create car
        car1 = new Car();
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
        car1.setIsActive(true);

        car2 = new Car();
        car2.setAvailibility(false);
        car2.setBrand("Citroen");
        car2.setTypeName("Berlingo");
        car2.setVIN("AJGSKA1234GF");
        car2.setVehicleRegPlate("TN-112CA");
        car2.setYearOfManufacture(1996);
        car2.setEngineDisplacement((float) 1.4);
        car2.setEnginePower(66);
        car2.setGasConsumption((float) 7.3);
        car2.setMileage(56000);
        car2.setNumberOfSeats(4);
        car2.setTransmission(true);
        car2.setIsActive(true);

        //create person
        person = new Person();
        person.setName("JOHN");
       // person.setAddress(new Address());
        person.setPosition("HR");
        person.setNationality("US");
        person.setSalary(25_000);
        person.setIsActive(true);
        person.setIdentificationNumber("EB123456");

        //create lease - two of them       
        lease = new Lease();
        lease.setDistance(car1.getMileage());
        lease.setDateOfLease(date1);
        lease.setDateOfReturn(date2);
        lease.setIsClosed(true);
        lease.setCar(car1);
        lease.setPerson(person);

        lease2 = new Lease();
        lease2.setDistance(car1.getMileage());
        lease2.setDateOfLease(date3);
        lease2.setDateOfReturn(date4);
        lease2.setIsClosed(true);
        lease2.setCar(car1);

        em.getTransaction().begin();

        //persist entities
        em.persist(person);

        em.persist(car1);
        em.persist(car2);

        em.persist(lease);
        em.persist(lease2);

        em.getTransaction().commit();

        //create DAO object
        toInsert = new Lease();
        toInsert.setDistance(car1.getMileage());
        toInsert.setDateOfLease(date3);
        toInsert.setDateOfReturn(date4);
        toInsert.setIsClosed(true);
        toInsert.setCar(car1);

        toUpdate = new Lease();
        toUpdate.setDistance(car2.getMileage());
        toUpdate.setDateOfLease(date3);
        toUpdate.setDateOfReturn(date4);
        toUpdate.setIsClosed(false);
        toUpdate.setCar(car2);
    }

    @After
    public void tearDownClass() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Lease").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Test of createLease method, of class LeaseDAOImpl.
     */
    @Test
    public void testIsInDB() {
        em.getTransaction().begin();
        List<Lease> leases = em.createQuery("SELECT l FROM Lease l", Lease.class).getResultList();
        assertEquals(leases.size(), 2);
        em.getTransaction().commit();
    }

    @Test
    public void testCreateLease() {

        em.getTransaction().begin();
        dao.createLease(toInsert);
        Lease lease3 = em.find(Lease.class, toInsert.getId());
        Assert.assertTrue(em.contains(lease3));
        em.getTransaction().commit();
    }

    /**
     * Test of updateLease method, of class LeaseDAOImpl.
     */
    @Test
    public void testUpdateLease() {
        dao.updateLease(toUpdate, lease2.getLeaseId());
        em.getTransaction().begin();
        Lease updatedLease = em.find(Lease.class, lease2.getLeaseId());
        Assert.assertEquals(updatedLease.getCar(), toUpdate.getCar());
        Assert.assertEquals(updatedLease.getIsClosed(), toUpdate.getIsClosed());
        Assert.assertEquals(date3, toUpdate.getDateOfLease());
        Assert.assertEquals(updatedLease.getDateOfReturn(), toUpdate.getDateOfReturn());
        Assert.assertEquals(updatedLease.getDistance(), toUpdate.getDistance());
        em.getTransaction().commit();
    }

    /**
     * Test of deleteLease method, of class LeaseDAOImpl.
     */
    @Test
    public void testDeleteLease() {
        Integer id = lease.getLeaseId();
        dao.deleteLease(lease.getLeaseId());
        em.getTransaction().begin();
        Assert.assertFalse(em.contains(lease));
        em.getTransaction().commit();
    }

    /**
     * Test of getLeasesByPerson method, of class LeaseDAOImpl.
     */
    @Test
    public void testGetLeasesByPerson() {
        em.getTransaction().begin();
        List<Lease> leasesByPerson = dao.getLeasesByPerson(person);
        em.getTransaction().commit();
        assertEquals(1, leasesByPerson.size());
    }

    /**
     * Test of getLeasesById method, of class LeaseDAOImpl.
     */
   /* @Test
    public void testGetLeaseByID() {
        em.getTransaction().begin();
        Lease leaseByID = dao.getLeaseByID(lease.getId());
        em.getTransaction().commit();
        assertEquals(lease, leaseByID);
    }*/

    /**
     * Test of getAllLeases method, of class LeaseDAOImpl.
     */
    @Test
    public void testGetAllLeases() {
        em.getTransaction().begin();
        List<Lease> leases = dao.getAllLeases(date1, date4);
        em.getTransaction().commit();
        assertEquals(2, leases.size());
    }

}
