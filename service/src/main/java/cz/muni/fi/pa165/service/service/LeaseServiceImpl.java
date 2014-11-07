/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.persistence.DAO.LeaseDAO;
import cz.muni.fi.pa165.persistence.Entities.Lease;
import cz.muni.fi.pa165.persistence.Entities.Lease.ReturnedStatus;
import cz.muni.fi.pa165.persistence.Entities.Person;
import cz.muni.fi.pa165.service.dto.LeaseDTO;
import cz.muni.fi.pa165.service.dto.PersonDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Petr Potucek
 */

@Service("leaseService")
@Repository //for transformation of exceptions to DataAccessException
@Transactional //to handle transactions
public class LeaseServiceImpl implements LeaseServiceInterface {

   
    private LeaseDAO leaseDAO;
    private static List<String> list = new ArrayList<String>();
    
    @PersistenceContext
    private EntityManager em;
   
    /**
     * setter for lease
     *
     * @param leaseDao lease to be setted
     */
    public void setLeaseDao(LeaseDAO leaseDAO) {
         if (leaseDAO == null) {
            throw new NullPointerException("null object");
        } 
        
        this.leaseDAO = leaseDAO;
    }
 
    public void createLease(LeaseDTO leaseDTO) {
        if (leaseDTO == null) {
            throw new NullPointerException("null object");
        }
        
        ApplicationContext applicationContext 
                = new ClassPathXmlApplicationContext("/applicationContext.xml");
        
        leaseDAO = (LeaseDAO) applicationContext.getBean("leaseDAO");
        Lease leaseEntity = null;
        
        list.add("dozerMapping.xml");
        
        Mapper mapper = new DozerBeanMapper(list);
        mapper.map(leaseDTO, leaseEntity, "lease");
        
        leaseDAO.createLease(leaseEntity);
        
    }

    public void setReturnedStatus(int id, ReturnedStatus status) {
         if (id < 0) {
            throw new IllegalArgumentException("wrong type of id");
        }
          if (status == null) {
            throw new NullPointerException("null object");
        }  
                
        (leaseDAO.getLeaseByID(id)).setReturnedStatus(status);
    }

    public List<LeaseDTO> getLeaseByPerson(PersonDTO personDTO) {
        if (personDTO == null){
            throw new NullPointerException("personDTO is null");
        }
        Person personEntity = null;
        
        LeaseDTO leaseDTO = null;
        
        List<String> list = new ArrayList<String>();
        
        List<LeaseDTO> leaseListDTO = new ArrayList<LeaseDTO>();

                // map DTO object on Entity
        list.add("dozerMapping.xml");
        Mapper mapper = new DozerBeanMapper(list);

        mapper.map(personDTO, personEntity, "person");

        List<Lease> leaseList = leaseDAO.getLeasesByPerson(personEntity);;
        for (Lease l : leaseList) {
            mapper.map(l, leaseDTO, "lease");
            leaseListDTO.add(leaseDTO);
        }
        
        return leaseListDTO;
    }

    public List getAllLeases(Date from, Date to) {
        if (from == null) {
            throw new NullPointerException("Date from is null");
        }
        if (to == null) {
            throw new NullPointerException("Date to is null");
        } 
        
        ApplicationContext applicationContext 
                = new ClassPathXmlApplicationContext("/applicationContext.xml");
        list.add("dozerMapping.xml");
        List<Lease> allLeases = leaseDAO.getAllLeases(from, to);
	Mapper mapper = new DozerBeanMapper(list);
	List leasesDTO = new ArrayList(allLeases.size());
	for (Lease lease : allLeases) {
            leasesDTO.add(mapper.map(lease, LeaseDTO.class));
        }
	return leasesDTO;
    }

    public Lease getLeaseById(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Wrong type of id");
        } 
       
         ApplicationContext applicationContext 
                = new ClassPathXmlApplicationContext("/applicationContext.xml");
        list.add("dozerMapping.xml");
        Lease lease = leaseDAO.getLeaseByID(id);
	Mapper mapper = DozerBeanMapper.getInstance(list);
	Lease leaseDTO = mapper.map(lease,LeaseDTO.class);
        
	return leaseDTO;
    }

    public void deleteLease(LeaseDTO leaseDto) {
        ApplicationContext applicationContext 
                = new ClassPathXmlApplicationContext("/applicationContext.xml");
        
        LeaseDAO leaseDao = (LeaseDAO) applicationContext.getBean("carDAO");
        Lease leaseEntity = null;
        
        List<String> list = new ArrayList<String>();
// Add the mapping configuration
        list.add("dozerMapping.xml");
// Add to DozerMapper
        Mapper mapper = new DozerBeanMapper(list);
        
        mapper.map(leaseDto, leaseEntity, "lease");
        leaseDao.deleteLease(leaseEntity.getId());
     
    }
    

    public List<Lease> getTravelStatistics(Person person, Date from, Date to) {
        if (person == null){
            throw new NullPointerException("person is null");
        }
        if (from == null) {
            throw new NullPointerException("Date from is null");
        }
        if (to == null) {
            throw new NullPointerException("Date to is null");
        }
        
        ApplicationContext applicationContext 
                = new ClassPathXmlApplicationContext("/applicationContext.xml");
        list.add("dozerMapping.xml");
        List<Lease> allLeases = leaseDAO.getAllLeases(from, to);
        List<Lease> leasesByPersonDTO = new ArrayList();
        Mapper mapper = DozerBeanMapper.getInstance(list);
        for(Lease lease: allLeases){
            if ((lease.getPerson()).equals(person)){
                leasesByPersonDTO.add(mapper.map(lease, LeaseDTO.class));
            }        
        }
        
	return leasesByPersonDTO;
    }
} 
  
