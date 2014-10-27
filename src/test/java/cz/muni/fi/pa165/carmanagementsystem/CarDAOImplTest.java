/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carmanagementsystem;

import cz.muni.fi.pa165.carmanagementsystem.DAO.CarDAOImpl;
import cz.muni.fi.pa165.carmanagementsystem.Entities.Car;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
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
    private EntityManagerFactory emf;
    private CarDAOImpl carDAO = new CarDAOImpl(Persistence.createEntityManagerFactory("carManagementSystem-unit"));

    @BeforeClass
    public void setUpClass() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Car car1 = new Car();
        car1.setAvailibility(true);
        car1.setBrand("Citroen");
        car1.setTypeName("Berlingo");
        car1.setVIN("AJGSKA1234FF");
        car1.setVehicleRegPlate("TN-112BA");
        car1.setYearOfManufacture(1996);
        car1.setEngineDisplacement((float) 1.4);
        car1.setEnginePower(66);
        car1.setGasConsumption((float) 7.3);
        car1.setMileage(55000);
        car1.setNumberOfSeats(5);
        car1.setTransmission(true);
        em.persist(car1);
        
        em.getTransaction().commit();
        em.close();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void isInDB() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Car> c = em.createQuery("SELECT c FROM Car c", Car.class).getResultList();
        assertEquals(c.size(), 1);
        em.getTransaction().commit();
        em.close();
    }

}
