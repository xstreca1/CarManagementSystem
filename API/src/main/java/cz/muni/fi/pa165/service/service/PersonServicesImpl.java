/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.persistence.DAO.PersonDAO;
import cz.muni.fi.pa165.persistence.Entities.Lease;
import cz.muni.fi.pa165.persistence.Entities.Person;
import cz.muni.fi.pa165.service.dto.PersonDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jakub Rumanovsky
 */
@Service("personService")
@Repository //for transformation of exceptions to DataAccessException
@Transactional //to handle transactions
public class PersonServicesImpl implements PersonServices {

    @Autowired
    // Person DAO
    private PersonDAO personDAO;

    @PersistenceContext
    private EntityManager em;

    // setter for ServiceCheck DAO - to be set in applicationContext.xml  
    public void setDao(PersonDAO personDAO) {
        //check if not null
        this.personDAO = personDAO;
    }
    public boolean createPerson(PersonDTO personDTO) {
        
        
        //create empty entity
        Person personEntity = new Person();

        //create empty list
        List<String> list = new ArrayList<String>();

        // map DTO object on Entity
        list.add("dozerMapping.xml");
        Mapper mapper = new DozerBeanMapper(list);

        mapper.map(personDTO, personEntity, "person");

        // save to database using some implementation od DAO
        personDAO.insertPerson(personEntity);

        return true;
    }

    public boolean editPerson(PersonDTO personDTO, Integer personID) {
   
    //create empty entity
        Person personEntity = null;

        //create empty list
        List<String> list = new ArrayList<String>();

        // map DTO object on Entity
        list.add("dozerMapping.xml");
        Mapper mapper = new DozerBeanMapper(list);

        mapper.map(personDTO, personEntity, "person");

        personDAO.updatePerson(personEntity, personID);
    

        return true;

    }

    /**
     * public boolean deactivatePerson(PersonDTO personDto) {
     *
     * ApplicationContext applicationContext = new
     * ClassPathXmlApplicationContext("/applicationContext.xml");
     *
     * PersonDAO personDAO = (PersonDAO)
     * applicationContext.getBean("personDAO"); Person personEntity = null;
     *
     * List<String> list = new ArrayList<String>(); // Add the mapping
     * configuration list.add("dozerMapping.xml"); // Add to DozerMapper Mapper
     * mapper = new DozerBeanMapper(list);
     *
     * mapper.map(personDto, personEntity, "person");
     * em.getTransaction().begin(); personDAO.updatePerson(personEntity,
     * personEntity.getId()); em.getTransaction().commit();
     *
     * throw new UnsupportedOperationException("Not supported yet."); //To
     * change body of generated methods, choose Tools | Templates. }
     *
     */
    @Override
    public List<PersonDTO> findAllPeople(boolean alsoInactive) {
        //create empty list
        List<String> list = new ArrayList<String>();

        List<PersonDTO> peopleDTO = new ArrayList<PersonDTO>();

        PersonDTO personDTO = new PersonDTO();

        // map DTO object on Entity
        list.add("dozerMapping.xml");
        Mapper mapper = new DozerBeanMapper(list);
        
        List<Person> people = personDAO.findAllPeople(alsoInactive);
        
        for (Person p : people) {
                mapper.map(p, personDTO, "person");
                peopleDTO.add(personDTO);
        }

       
        return peopleDTO;
    }

    @Override
    public List<PersonDTO> getPeopleByName(String name) {

        List<String> list = new ArrayList<String>();

        List<PersonDTO> peopleDTO = new ArrayList<PersonDTO>();

        PersonDTO personDTO = new PersonDTO();

        list.add("dozerMapping.xml");
        Mapper mapper = new DozerBeanMapper(list);

        List<Person> people = personDAO.getPeopleByName(name);
        for (Person p : people) {
            mapper.map(p, personDTO, "person");
            peopleDTO.add(personDTO);

        }
        return peopleDTO;

    }
    
    @Override
    public PersonDTO getPersonByID(Integer id) {
        Person personEntity = new Person();
        PersonDTO personDto = new PersonDTO();
        
        List<String> list = new ArrayList<String>();
        
        list.add("dozerMapping.xml");
        
        personEntity = personDAO.getPersonByID(id);
        
        Mapper mapper = new DozerBeanMapper(list);
        
        mapper.map(personEntity, personDto, "person");
        
        return personDto;
    }
}
