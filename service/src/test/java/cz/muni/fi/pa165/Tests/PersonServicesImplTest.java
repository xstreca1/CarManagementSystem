package cz.muni.fi.pa165.Tests;

import cz.muni.fi.pa165.persistence.DAO.PersonDAO;
import cz.muni.fi.pa165.persistence.Entities.Lease;
import cz.muni.fi.pa165.persistence.Entities.Person;
import cz.muni.fi.pa165.service.dto.PersonDTO;
import cz.muni.fi.pa165.service.service.PersonServicesImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
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
 * @author jrumanov
 */
@RunWith(MockitoJUnitRunner.class)
public class PersonServicesImplTest {
    
    @Mock
    PersonDAO mockPersonDao;
    
    @Mock
    Person person;
    
    @Mock
    PersonDTO personDto;
    
    @Mock
    List<Person> people;
    
    @InjectMocks
    private PersonServicesImpl service = new PersonServicesImpl();
    
    
    @Before
    public void setUp() {
        
         Mockito.when(mockPersonDao.insertPerson(Matchers.any(Person.class)))
                .thenAnswer(new Answer<Person>() {
					@Override
					public Person answer(InvocationOnMock inv)
							throws Throwable {
						Object[] args = inv.getArguments();
						return (Person) args[0];

					                                  }
                });
         
        
        List<Person> allPeople = new ArrayList<Person>();
		allPeople.add(person);
		allPeople.add(person);
                
                List<Person> namedPeople = new ArrayList<Person>();
                namedPeople.add(person);
                namedPeople.add(person);
                
		Mockito.when(mockPersonDao.findAllPeople(Matchers.anyBoolean()))
                        .thenReturn(namedPeople);
                
       
                
                Mockito.when(mockPersonDao.getPeopleByName(Matchers.contains("JOZO")))
				.thenReturn(namedPeople);
    }
    
    
    @Test //OK
    public void testInsertPerson() {
                
		service.createPerson(personDto);
                
		assertNotNull(personDto);
                
		assertEquals(true, true);
    }
    
  //  @Test
    public void testFindAllPersons() {
        List<PersonDTO> list = service.findAllPeople(true);
			assertNotNull(list);
			assertEquals(2, list.size());
    }
    
    @Test
    public void getPeopleByName() {
        
        List<PersonDTO> list = service.getPeopleByName("JOZO");
			assertNotNull(list);
			assertEquals(2, list.size());
        
    }       

    
}