/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.persistence.Entities.Lease;
import cz.muni.fi.pa165.persistence.Entities.Person;
import cz.muni.fi.pa165.persistence.DAO.PersonDAO;
import cz.muni.fi.pa165.persistence.DAO.ServiceCheckDAO;
import cz.muni.fi.pa165.service.dto.PersonDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Jakub Rumanovsky
 */
public class PersonServicesImpl implements PersonServices {

    // Person DAO
    private PersonDAO personDAO;

    // EntityManagmentFactory
    EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("carManagementSystem-unit");

    // Entity Manager
    private EntityManager em = emf.createEntityManager();

    // setter for ServiceCheck DAO - to be set in applicationContext.xml  
    public void setDao(PersonDAO personDAO) {
        //check if not null
        this.personDAO = personDAO;
    }

    public boolean createPerson(PersonDTO personDTO) {

        //create empty entity
        Person personEntity = null;

        //create empty list
        List<String> list = new ArrayList<String>();

        // map DTO object on Entity
        list.add("dozerMapping.xml");
        Mapper mapper = new DozerBeanMapper(list);

        mapper.map(personDTO, personEntity, "person");

        // start transaction
        em.getTransaction().begin();

        // save to database using some implementation od DAO
        personDAO.insertPerson(personEntity);

        // commit transaction
        em.getTransaction().commit();

        return true;
    }

    public boolean editPerson(Person person, Integer personID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

            // map DTO object on Entity
            list.add("dozerMapping.xml");
            Mapper mapper = new DozerBeanMapper(list);
            
             if (alsoInactive = true) {

            List<Person> people = personDAO.getAllPeople();

        }

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Person> getPeopleByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Lease> getTravelStatistics(Person person, Date from, Date to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean deactivatePerson(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
