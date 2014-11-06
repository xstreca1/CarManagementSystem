/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.Tests;

import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.DAO.CarDAO;
import cz.muni.fi.pa165.service.service.CarImpl;
import java.util.ArrayList;
import java.util.Date;
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
    List<Car> cars;
    
    @InjectMocks
    private CarImpl service = new CarImpl();

    @Before
    public void setUp() {
        
    }

		
}
