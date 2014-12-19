/*
 * Unit test for Data access object of entity class Car
 */
package cz.muni.fi.pa165.persistence.tests;

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
                .createEntityManagerFactory("Test-unit");
        em = emf.createEntityManager();
        testedObject = new CarDAOImpl(em);

        car1 = new Car();
        car1.setAvailibility(true);
        car1.setBrand("Citroen");
        car1.setTypeName("Berlingo");
        car1.setVIN("AJGSKA1234FF");
        car1.setVehicleRegPlate("TN-112BA");
        car1.setYearOfManufacture(1996);
        
        car1.setMileage(55000);
        car1.setNumberOfSeats(5);
        
        car1.setIsActive(true);

        car2 = new Car();
        car2.setAvailibility(false);
        car2.setBrand("Citroen");
        car2.setTypeName("Berlingo");
        car2.setVIN("AJGSKA1234GF");
        car2.setVehicleRegPlate("TN-112CA");
        car2.setYearOfManufacture(1996);
    
        car2.setMileage(56000);
        car2.setNumberOfSeats(4);
       
        car2.setIsActive(false);

        carForInsert = new Car();
        carForInsert.setAvailibility(false);
        carForInsert.setBrand("Porsche");
        carForInsert.setTypeName("918");
        carForInsert.setVIN("SAKSD2232DNF");
        carForInsert.setVehicleRegPlate("TN-918EE");
        carForInsert.setYearOfManufacture(2014);
      
        carForInsert.setMileage(559);
        carForInsert.setNumberOfSeats(2);
       
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
      
        carForUpdate.setMileage(101200);
        carForUpdate.setNumberOfSeats(5);
        
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

       
     em.getTransaction().begin();
        
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
        Assert.assertEquals(carForUpdate.getAvailibility(), updatedCar.getAvailibility());
        Assert.assertEquals(carForUpdate.getIsActive(), updatedCar.getIsActive());
        em.getTransaction().commit();

    }

    @Test
    public void testListAllAvailableCars() {
        List<Car> cars = testedObject.listAllAvailableCars();
        assertEquals(1, cars.size());
    }

    @Test
    public void listAllCars() {
        List<Car> cars = testedObject.listAllCars(true);
        assertEquals(1, cars.size());
    }

    @Test
    // test if it is possible to get car from DB using getPersonById()
    public void testGetById() {
        // try if bad input results in Exception
        try {
            testedObject.getCarByID(null);
            fail("wrong input allowed!");
        } catch (IndexOutOfBoundsException e) {
        }

        // get person using getPersonById method
        Car car = testedObject.getCarByID(car1.getId());
        // pers should be not null
        assertNotNull(car);
        // person and person1 should be the same
        assertEquals(car1, car);

    }
}
