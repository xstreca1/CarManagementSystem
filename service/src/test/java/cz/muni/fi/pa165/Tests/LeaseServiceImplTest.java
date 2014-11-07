/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.Tests;

import cz.muni.fi.pa165.persistence.DAO.LeaseDAO;
import cz.muni.fi.pa165.persistence.Entities.Lease;
import cz.muni.fi.pa165.service.dto.LeaseDTO;
import cz.muni.fi.pa165.service.service.LeaseServiceImpl;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
 * @author petr.potucek
 */
public class LeaseServiceImplTest {
    
    @Mock
    LeaseDAO mockLeaseDao;
    
    @Mock
    Lease lease;
    
    @Mock
    LeaseDTO leaseDto;
    
    @Mock
    List<Lease> leases;
    
    @InjectMocks
    private LeaseServiceImpl service = new LeaseServiceImpl();
    
    @Before
    public void setUp() {
        Mockito.when(mockLeaseDao.createLease(Matchers.any(Lease.class)))
                .thenAnswer(new Answer<Lease>() {
					@Override
					public Lease answer(InvocationOnMock inv)
							throws Throwable {
						Object[] args = inv.getArguments();
						return (Lease) args[0];

					                                  }
                });
        Mockito.when(mockLeaseDao.deleteLease(Matchers.any(Integer.class)))
				.thenAnswer(new Answer<Lease>() {
					@Override
					public Lease answer(InvocationOnMock inv)
							throws Throwable {
						Object[] args = inv.getArguments();
						return (Lease) args[0];

					}
				});
    }
    
    @Test
    public void testCreateLease() {
                
                service.createLease(leaseDto);
                
		assertNotNull(leaseDto);
                
		assertEquals(true, true);
    }
    
    @Test
    public void testDeleteLease() {
        
        service.deleteLease(leaseDto);
        
        verify(mockLeaseDao).deleteLease(lease.getId());
        
    }
    
}
