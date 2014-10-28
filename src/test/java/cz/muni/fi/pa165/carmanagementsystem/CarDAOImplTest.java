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
import org.junit.Assert;
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
    private static final CarDAOImpl carDAO
            = new CarDAOImpl(Persistence.createEntityManagerFactory("carManagementSystem-unit"));

    private static Car car1 = new Car();
    private static Car carForInsert = new Car();
    private static Car carForUpdate = new Car();

    @BeforeClass
    public static void setUpClass() {
        EntityManager em = carDAO.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

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

        carForInsert.setAvailibility(false);
        carForInsert.setBrand("Porsche");
        carForInsert.setTypeName("918");
        carForInsert.setVIN("SAKSD2232DNF");
        carForInsert.setVehicleRegPlate("TN-918EE");
        carForInsert.setYearOfManufacture(2014);
        carForInsert.setEngineDisplacement((float) 4.6);
        carForInsert.setEnginePower(887);
        carForInsert.setGasConsumption((float) 19);
        carForInsert.setMileage(559);
        carForInsert.setNumberOfSeats(2);
        carForInsert.setTransmission(true);

        carForUpdate.setAvailibility(true);
        carForUpdate.setBrand("Lada");
        carForUpdate.setTypeName("2101");
        carForUpdate.setVIN("SDFGK345NFMN");
        carForUpdate.setVehicleRegPlate("ZC-002AA");
        carForUpdate.setYearOfManufacture(1972);
        carForUpdate.setEngineDisplacement((float) 1.2);
        carForUpdate.setEnginePower(44);
        carForUpdate.setGasConsumption((float) 10);
        carForUpdate.setMileage(101200);
        carForUpdate.setNumberOfSeats(5);
        carForUpdate.setTransmission(true);

        em.persist(car1);

        em.getTransaction().commit();
        em.close();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void isInDB() {
        EntityManager em = carDAO.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        List<Car> c = em.createQuery("SELECT c FROM Car c", Car.class).getResultList();
        assertEquals(c.size(), 1);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testCreateCar() {
        carDAO.createCar(carForInsert);

        EntityManager em = carDAO.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Car car = em.find(Car.class, carForInsert.getId());
        Assert.assertTrue(em.contains(car));
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testUpdateCar() {
        carDAO.updateCar(carForUpdate, car1.getVehicleRegPlate());

        //Jozo, chyba ti tam getCarByID
        EntityManager em = carDAO.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Car updatedCar = em.find(Car.class, car1.getId());
        Assert.assertEquals("2101", updatedCar.getTypeName());
        em.getTransaction().commit();
        em.close();

    }

    @Test
    public void testDeleteCar() {
        Integer id = car1.getCarID();
        carDAO.deleteCar(car1.getVehicleRegPlate());
        assertNull(carDAO.getCarByID(id));

    }

    @Test
    public void testListAllAvailableCars() {
        List<Car> cars = carDAO.listAllAvailableCars();
        assertEquals(1, cars.size());
    }

    @Test
    public void testGetCarBySeats() {
        List<Car> carsBySeats = carDAO.getCarBySeats(5);
        assertEquals(1, carsBySeats.size());
    }

    /*

     @Test
     public void testGetCarByCategory(int Category) {
         
     }
     @Test
     public void testGetCarByBodyStyle(Car.bodyStyle bs) {

     }
     */
}
