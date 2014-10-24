package cz.muni.fi.pa165.carmanagementsystem;

import cz.muni.fi.pa165.carmanagementsystem.DAO.ServiceCheckDAOImpl;
import cz.muni.fi.pa165.carmanagementsystem.Entities.ServiceCheck;
import cz.muni.fi.pa165.carmanagementsystem.Entities.ServiceCheck.ServiceCheckName;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Petr Potucek
 */
public class ServiceCheckImplTest {
    
    @PersistenceUnit
    EntityManagerFactory emf;
    @Before
    public void setUp(){
        ServiceCheck serviceCheck = new ServiceCheck();
        EntityManager em = emf.createEntityManager(); 
        em.getTransaction().begin();
        em.persist(serviceCheck);
        em.getTransaction().commit();
        em.close();     
    }
    
    @After
    public void tearDown() {
        
    }
    
    @Test
    public void testUpdateServiceCheck(){

        ServiceCheck serviceCheck = new ServiceCheck();
        serviceCheck.setName(ServiceCheckName.TECHNICAL);
        ServiceCheckDAOImpl dao = new ServiceCheckDAOImpl();
        dao.createServiceCheck(serviceCheck);
        
        ServiceCheck serviceCheck1 = new ServiceCheck();
        serviceCheck1.setName(ServiceCheckName.EMISSION);
        dao.updateServiceCheck(serviceCheck1, serviceCheck.getScID());

        assertEquals(serviceCheck.getName(), "EMISSION");         
    }
}
