package cz.muni.fi.pa165.CMSPersistenceLayer;

import cz.muni.fi.pa165.persistence.DAO.ServiceCheckDAOImpl;
import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.ServiceCheck;
import cz.muni.fi.pa165.persistence.Entities.ServiceCheck.ServiceCheckName;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Petr Potucek
 */
public class ServiceCheckImplTest {

    private static ServiceCheckDAOImpl dao
            = new ServiceCheckDAOImpl(Persistence.createEntityManagerFactory("carManagementSystem-unit"));

    private static ServiceCheck check1;
    private static ServiceCheck check2;
    private static ServiceCheck check3;
    private static ServiceCheck checkInsert;
    private static Car car1;
    private static Car car2;
    private static Car car3;
    private static Date date1;
    private static Date date2;
    private static Date date3;

    @BeforeClass
    public static void setUpClass() {
        EntityManager em = dao.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

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

        car2 = new Car();
        car2.setAvailibility(true);
        car2.setBrand("Skoda");
        car2.setTypeName("Fabia");
        car2.setVIN("AAASSS111222");
        car2.setVehicleRegPlate("PD-112BA");
        car2.setYearOfManufacture(1996);
        car2.setEngineDisplacement((float) 1.4);
        car2.setEnginePower(66);
        car2.setGasConsumption((float) 7.3);
        car2.setMileage(55000);
        car2.setNumberOfSeats(5);
        car2.setTransmission(true);

        car3 = new Car();
        car3.setAvailibility(true);
        car3.setBrand("Volkswagen");
        car3.setTypeName("Golf");
        car3.setVIN("BBBSSS111222");
        car3.setVehicleRegPlate("BA-112BA");
        car3.setYearOfManufacture(1996);
        car3.setEngineDisplacement((float) 1.4);
        car3.setEnginePower(66);
        car3.setGasConsumption((float) 7.3);
        car3.setMileage(55000);
        car3.setNumberOfSeats(5);
        car3.setTransmission(true);

        em.persist(car1);
        em.persist(car2);
        em.persist(car3);

        date1 = new Date(2014, 5, 5);
        date2 = new Date(2014, 6, 5);
        date3 = new Date(2014, 7, 5);

        check1 = new ServiceCheck();
        check1.setServiceInterval(10);
        check1.setLastCheck(date1);
        check1.setDescription("skoda");
        check1.setCar(car1);
        check1.setName(ServiceCheckName.TECHNICAL);
        em.persist(check1);

        check2 = new ServiceCheck();
        check2.setServiceInterval(20);
        check2.setLastCheck(date2);
        check2.setDescription("mercedes");
        check2.setCar(car2);
        check2.setName(ServiceCheckName.OVERALL);
        em.persist(check2);

        check3 = new ServiceCheck();
        check3.setServiceInterval(8);
        check3.setLastCheck(date3);
        check3.setDescription("vw");
        check3.setCar(car3);
        check3.setName(ServiceCheckName.OVERALL);
        em.persist(check3);

        em.getTransaction().commit();
        em.close();

        // to test cretaeServiceCheck()
        checkInsert = new ServiceCheck();
        checkInsert = new ServiceCheck();
        checkInsert.setServiceInterval(8);
        checkInsert.setLastCheck(new Date());
        checkInsert.setDescription("vw");
        checkInsert.setCar(car3);
        checkInsert.setName(ServiceCheckName.OVERALL);
    }

    @After
    public void tearDown() {

    }

    @Test //PASS
    public void isInDB() {
        EntityManager em = dao.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        List<ServiceCheck> s = em.createQuery("SELECT s FROM ServiceCheck s", ServiceCheck.class).getResultList();
        assertEquals(s.size(), 3);
        em.getTransaction().commit();
        em.close();
    }

    @Test // PASS
    public void testAddServiceCheck() {

        try {
            dao.createServiceCheck(null);
            fail("wrong input allowed!");
        } catch (IllegalArgumentException e) {
        }

        dao.createServiceCheck(checkInsert);

        EntityManager em = dao.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        ServiceCheck check = em.find(ServiceCheck.class, checkInsert.getScID());
        Assert.assertTrue(em.contains(check));
        em.getTransaction().commit();
        em.close();

    }

    @Test
    public void testUpdate() {
        // try if bad input results in Exception
        try {
            dao.updateServiceCheck(null, check1.getScID());
            fail("wrong input allowed!");
        } catch (IllegalArgumentException | NullPointerException e) {
        }

        // try if bad input results in Exception
        try {
            dao.updateServiceCheck(check1, null);
            fail("wrong input allowed!");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
        }

        ServiceCheck updatedCheck = new ServiceCheck();

        updatedCheck.setServiceInterval(40);
        updatedCheck.setLastCheck(date3);
        updatedCheck.setDescription("vw");
        updatedCheck.setCar(car3);
        updatedCheck.setName(ServiceCheckName.OVERALL);

        dao.updateServiceCheck(updatedCheck, check3.getScID());

        assertEquals(check3.getServiceInterval(), 40);
        assertEquals(check3.getLastCheck(), date3);
        assertEquals(check3.getDescription(), "vw");
        assertEquals(check3.getCar(), car3);
        assertEquals(check3.getName(), "OVERALL");

    }

    @Test
    public void testServiceCheckDelete() {
        try {
            dao.deleteServiceCheck(null);
            fail("wrong input allowed!");
        } catch (IndexOutOfBoundsException e) {
        }

        Calendar nextControl = Calendar.getInstance();
        nextControl.setTime(date1);
        nextControl.add(Calendar.MONTH, check1.getServiceInterval());
        Calendar now = Calendar.getInstance();
        now.getTime();
        int days = nextControl.get(Calendar.DATE) - now.get(Calendar.DATE);
        int number = dao.getDaysToNext(check1.getScID());
        Assert.assertEquals(number, days);
    }

    @Test // OK, just testing if list is not empty and if it contains exactly one check
    public void testGetCheckForCar() {
        try {
            dao.getServiceChecksForCar(null);
            fail("wrong input allowed!");
        } catch (IllegalArgumentException e) {
        }

        List<ServiceCheck> list = dao.getServiceChecksForCar(car1);
        assertNotNull(list);
        int checks = list.size();
        assertEquals(checks, 1);
    }

}
