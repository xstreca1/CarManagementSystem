/*
 * Unit test for Data access object of entity class Car
 */
package cz.muni.fi.pa165.CMSPersistenceLayer;

import cz.muni.fi.pa165.persistence.DAO.CarDAOImpl;
import cz.muni.fi.pa165.persistence.Entities.Car;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author jrumanov
 */
public class CarDAOImplTest {

    public CarDAOImplTest() {
    }
    //private EntityManagerFactory emf;
    private EntityManager em;
    private CarDAOImpl testedObject;

    private static Car car1;
    private static Car car2;
    private static Car carForInsert;
    private static Car carForUpdate;

    @Before
    public void setUpClass() {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("carManagementSystem-unit");
        em = emf.createEntityManager();
        testedObject = new CarDAOImpl(em);

        car1 = new Car();
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
        car2.setIsActive(false);

        carForInsert = new Car();
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
        carForInsert.setIsActive(true);

        em.getTransaction().begin();
        em.persist(car1);
        em.persist(car2);

        em.getTransaction().commit();
        carForUpdate = new Car();
        carForUpdate.setAvailibility(false);
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
        carForUpdate.setIsActive(true);
    }

    @After
    public void tearDown() {

        em.getTransaction().begin();
        em.createQuery("DELETE FROM Car").executeUpdate();
        em.getTransaction().commit();
    }

    @Test
    public void isInDB() {
        em.getTransaction().begin();
        List<Car> c = em.createQuery("SELECT c FROM Car c", Car.class).getResultList();
        assertEquals(c.size(), 2);
        em.getTransaction().commit();

    }

    @Test
    public void testCreateCar() {
   //     testedObject.createCar(carForInsert);

        //System.out.println("HALOOOOOOOOOOOOOOOOOOOOOOO" + carForInsert.toString());
     em.getTransaction().begin();
        //     em.persist(carForInsert);
        testedObject.createCar(carForInsert);
        Car car = em.find(Car.class, carForInsert.getCarID());
        Assert.assertTrue(em.contains(car));
       em.getTransaction().commit();

    }

    @Test
    public void testUpdateCar() {
        testedObject.updateCar(carForUpdate, car1.getCarID());
        em.getTransaction().begin();
        Car updatedCar = em.find(Car.class, car1.getId());
        Assert.assertEquals(carForUpdate.getMileage(), updatedCar.getMileage());
        Assert.assertEquals(carForUpdate.isAvailibility(), updatedCar.isAvailibility());
        Assert.assertEquals(carForUpdate.isIsActive(), updatedCar.isIsActive());
        em.getTransaction().commit();

    }

    @Test
    public void testDeleteCar() {
        Integer id = car2.getCarID();
        testedObject.deleteCar(car2.getCarID());
        em.getTransaction().begin();
        Assert.assertFalse(em.contains(car2));
        em.getTransaction().commit();

    }

    @Test
    public void testListAllAvailableCars() {
        List<Car> cars = testedObject.listAllAvailableCars();
        assertEquals(1, cars.size());
    }

    @Test
    public void testGetCarBySeats() {
        List<Car> carsBySeats = testedObject.getCarBySeats(5);
        assertEquals(1, carsBySeats.size());
    }
    
    @Test
    public void listAllCars() {
        List<Car> cars = testedObject.listAllCars(true);
        assertEquals(1, cars.size());
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
