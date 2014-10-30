package cz.muni.fi.pa165.CMSPersistenceLayer;

import cz.muni.fi.pa165.CMSPersistenceLayer.DAO.ServiceCheckDAOImpl;
import cz.muni.fi.pa165.CMSPersistenceLayer.Entities.Car;
import cz.muni.fi.pa165.CMSPersistenceLayer.Entities.ServiceCheck;
import cz.muni.fi.pa165.CMSPersistenceLayer.Entities.ServiceCheck.ServiceCheckName;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;


import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Petr Potucek
 */
public class ServiceCheckImplTest {
    
    private static ServiceCheckDAOImpl dao = 
            new ServiceCheckDAOImpl(Persistence.createEntityManagerFactory("carManagementSystem-unit"));
    
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
    public void setUpClass() {
        EntityManager em = dao.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        car1 = new Car();
        car2 = new Car(); 
        car3 = new Car(); 
        date1 = new Date(2014,5,5);
        date2 = new Date(2014,6,5);
        date3 = new Date(2014,7,5);
          
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
    }
       
    @After
    public void tearDown() {
        
    }
    
     @Test
     public void isInDB() {
        EntityManager em = dao.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        List<ServiceCheck> s = em.createQuery("SELECT s FROM ServiceCheck s", ServiceCheck.class).getResultList();
        assertEquals(s.size(), 3);
        em.getTransaction().commit();
        em.close();
    }
     

     @Test
     public void testAddServiceCheck() {
        try{
        dao.createServiceCheck(null); 
        fail("wrong input allowed!");
        }
        catch(IllegalArgumentException e){}  
        
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
        try{
        dao.updateServiceCheck(null, check1.getScID()); 
        fail("wrong input allowed!");
        }        
        catch(IllegalArgumentException | NullPointerException e){}
        
        // try if bad input results in Exception
        try{
        dao.updateServiceCheck(check1, null); 
        fail("wrong input allowed!");
        }        
        catch(IndexOutOfBoundsException | IllegalArgumentException e){}
        
                
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
        try{
        dao.deleteServiceCheck(null); 
        fail("wrong input allowed!");
        }        
        catch(IndexOutOfBoundsException e){}
        

        Calendar nextControl = Calendar.getInstance();
        nextControl.setTime(date1);
        nextControl.add(Calendar.MONTH, check1.getServiceInterval());
        Calendar now = Calendar.getInstance();
        now.getTime();
        int days = nextControl.get(Calendar.DATE) - now.get(Calendar.DATE);
        int number = dao.getDaysToNext(check1.getScID());
        Assert.assertEquals(number, days);
    }
    
    @Test
    public void testGetCheckForCar() {
        try{
        dao.getServiceChecksForCar(null); 
        fail("wrong input allowed!");
        }        
        catch(IndexOutOfBoundsException e){}
        
        List<ServiceCheck> list = dao.getServiceChecksForCar(car1);

        Assert.assertEquals(check1,list.get(0));
    }

}
