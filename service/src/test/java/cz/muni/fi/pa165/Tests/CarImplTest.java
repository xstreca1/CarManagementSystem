package cz.muni.fi.pa165.Tests;

import cz.muni.fi.pa165.persistence.DAO.CarDAO;
import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.service.dto.CarDTO;
import cz.muni.fi.pa165.service.service.CarImpl;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

/**
 *
 * @author jozef.puchly
 */
@RunWith(MockitoJUnitRunner.class)
public class CarImplTest {
    
    @Mock
    CarDAO mockCarDao;
    
    @Mock
    Car car;
    
    @Mock
    CarDTO carDto;
    
    @Mock
    List<Car> cars;
    
    @InjectMocks
    private CarImpl service = new CarImpl();

    @Before
    public void initMock() {
        Mockito.when(mockCarDao.createCar(Matchers.any(Car.class)))
                .thenAnswer(new Answer<Car>() {
					@Override
					public Car answer(InvocationOnMock inv)
							throws Throwable {
						Object[] args = inv.getArguments();
						return (Car) args[0];

					                                  }
                });
      
        List<Car> allCars = new ArrayList<>();
		allCars.add(car);
		allCars.add(car);
		Mockito.when(mockCarDao.listAllCars(true)).thenReturn(allCars);
        
    }

    @Test
    public void testCreateCar() {
             
                CarDTO carDto2 = new CarDTO();  
        
		service.createCar(carDto2);
                
		assertNotNull(carDto2);
                
		assertEquals(true, true);
    }
    
    @Test
    public void findAllCars() {
        
            List<CarDTO> list = service.findAllCars(true);
			assertNotNull(list);
			assertEquals(2, list.size());
        
    }
}