/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.persistence.Entities.Lease;
import cz.muni.fi.pa165.persistence.Entities.Person;
import cz.muni.fi.pa165.persistence.dao.PersonDAO;
import cz.muni.fi.pa165.service.dto.PersonDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
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

    @PersistenceContext
    private EntityManager em;

    public boolean createPerson() {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean editPerson(Person person, Integer personID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean deactivatePerson(PersonDTO personDto) {

        ApplicationContext applicationContext 
                = new ClassPathXmlApplicationContext("/applicationContext.xml");
        
        PersonDAO personDAO = (PersonDAO) applicationContext.getBean("personDAO");
        Person personEntity = null;
        
        List<String> list = new ArrayList<String>();
// Add the mapping configuration
        list.add("dozerMapping.xml");
// Add to DozerMapper
        Mapper mapper = new DozerBeanMapper(list);
        
        mapper.map(personDto, personEntity, "person");
        em.getTransaction().begin();
        personDAO.updatePerson(personEntity, personEntity.getId());
        em.getTransaction().commit();

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Person> findAllPeople(boolean alsoInactive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Person> getPeopleByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
