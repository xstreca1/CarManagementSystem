package cz.muni.fi.pa165.Tests;

import cz.muni.fi.pa165.persistence.DAO.ServiceCheckDAOImpl;
import cz.muni.fi.pa165.persistence.Entities.ServiceCheck;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.runner.RunWith;


/**
 * Tests using Mock DAO objects
 * 
 * @author Petr Potucek
 */
@RunWith(MockitoJUnitRunner.class)
public class ServiceCheckTest {
    @Mock
    private ServiceCheckDAOImpl dao;
    
    @Test 
    public void testGetChecksForCar() {
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
    
    @Test 
    public void testCreateNewCheck() {}
    
    @Test 
    public void testDescriptionSet() {}
    
    @Test 
    public void deleteTest() {}
    
    @Test 
    public void testNameChange() {}
    
    @Test 
    public void testGetDaysToNext() {}
    

}

    

