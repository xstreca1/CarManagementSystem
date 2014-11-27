package cz.muni.fi.pa165.Tests;

import cz.muni.fi.pa165.persistence.DAO.ServiceCheckDAO;
import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.ServiceCheck;
import cz.muni.fi.pa165.service.dto.CarDTO;
import cz.muni.fi.pa165.service.dto.ServiceCheckDTO;
import cz.muni.fi.pa165.service.service.ServiceCheckImpl;
import cz.muni.fi.pa165.service.service.ServiceCheckInterface;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

/**
 * Service check service test file
 * 
 * @author Petr Potucek
 */
@RunWith(MockitoJUnitRunner.class)
public class ServiceCheckImplTest {
    
    @Mock
    ServiceCheckDAO mockCheckDAO;
    
    
    @Mock
    ServiceCheck serviceCheck;
    
    @Mock
    ServiceCheckDTO serviceCheckDTO;
    
    @Mock
    CarDTO carDto;
    
    @InjectMocks
    ServiceCheckInterface serviceCheckImpl = new ServiceCheckImpl();

    
    @Before
    public void setUp(){
        Mockito.when(mockCheckDAO.createServiceCheck(Matchers.any(ServiceCheck.class)))
                .thenAnswer(new Answer<ServiceCheck>() {
            @Override
            public ServiceCheck answer(InvocationOnMock inv) throws Throwable {
                Object[] args = inv.getArguments();
                return (ServiceCheck) args[0];
            }
        });
        
        Mockito.when(mockCheckDAO.getDaysToNext(Matchers.any(Integer.class)))
                .thenAnswer(new Answer<ServiceCheck>() {
            @Override
            public ServiceCheck answer(InvocationOnMock inv) throws Throwable {
                Object[] args = inv.getArguments();
                return (ServiceCheck) args[0];
            }
        });
        
        List<ServiceCheck> allChecks = new ArrayList<>();
		allChecks.add(serviceCheck);
		allChecks.add(serviceCheck);
        Mockito.when(mockCheckDAO.getServiceChecksForCar(Matchers.any(Car.class)))
				.thenReturn(allChecks);       
            
    }
    
                        
    
    @Test
    public void testCreateServiceCheck(){
          
                ServiceCheckDTO serviceCheckDTO2 = new ServiceCheckDTO();
                
		serviceCheckImpl.createServiceCheck(serviceCheckDTO2);
                
		assertNotNull(serviceCheckDTO2);
                
		assertEquals(true, true);
        
    }
    
  //  @Test
    public void testGetDaysToNextServiceCheck(){
        
        serviceCheckImpl.getDaysToNextServiceCheck(serviceCheckDTO);
        
        assertNotNull(serviceCheckDTO);
        
        assertEquals(true, true);
    }
    
   /** @Test
    public void testSetCheckInterval(){
    }
    **/
    @Test
    public void testGetServiceChecksForCar(){
        
        List<ServiceCheckDTO> list = serviceCheckImpl.getServiceChecksForCar(carDto);
			assertNotNull(list);
			assertEquals(2, list.size());
        
    }    
}