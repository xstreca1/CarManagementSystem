/*
 * Concrete implementation of data access object for entity Person.
 */
package cz.muni.fi.pa165.persistence.DAO;

import cz.muni.fi.pa165.persistence.Entities.Address;
import cz.muni.fi.pa165.persistence.Entities.Person;
import cz.muni.fi.pa165.persistence.Entities.Person.EmploymentStatus;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jakub Rumanovsky
 */
public class PersonDAOImpl implements PersonDAO {

    @PersistenceContext(name="carManagementSystem-unit")
    private EntityManager em;
    
    public PersonDAOImpl(EntityManager entityManager) {
		if (entityManager == null) {
			throw new IllegalArgumentException("argument 'em' must be set");
		}
		em = entityManager;
	}
    
    @Override
    public void insertPerson(Person person) {

        if (person == null) {
            throw new IllegalArgumentException("argument Person nemoze byt null");
        }
        //actual query
        em.persist(person);

    }

    @Override
    public void updatePerson(Person updatedPerson, Integer personID) {

        //get all updatable attributes from the updated person entity instance
        //lease and sex could not be updated
        Address address = updatedPerson.getAddress();
        Date dateOfBirth = updatedPerson.getDateOfBirth();
        EmploymentStatus empStat = updatedPerson.getEmploymentStatus();
        String name = updatedPerson.getName();
        String nationality = updatedPerson.getNationality();
        String position = updatedPerson.getPosition();
        int salary = updatedPerson.getSalary();
        
        Person person1 = (Person)em.find(Person.class ,personID);
        
        //change attributes of persisted entity in DB
        person1.setAddress(address);
        person1.setEmploymentStatus(empStat);
        person1.setName(name);
        person1.setNationality(nationality);
        person1.setPosition(position);
        person1.setSalary(salary);


        //commiting changes and closing entity manager
        em.getTransaction().commit();
    }

    @Override
    public void deletePerson(Integer personID) {
        
        //person is retrieved
        Person person = em.find(Person.class, personID);
        
        //person is removed from Database (TODO - cascading delete?)
        em.remove(person);
        
        //can also be done by em.executeQuery(DELETE_QUERY).executeUpdate();
    }

    @Override
    public Person getPersonByID(Integer personID) {

        //actual query
        String sql = "SELECT p FROM Person p WHERE p.id=:persID";
        Person person = em.createQuery(sql, Person.class)
                .setParameter("persID", personID).getResultList().get(0);

        return person;
    }

    @Override
    public List<Person> getPeopleByName(String name) {

        if (name == null) {
            throw new IllegalArgumentException("car name is null");
        }

        //actual query
        String sql = "SELECT p FROM Person p WHERE p.name= :persName";
        List<Person> people = em.createQuery(sql, Person.class).
                setParameter("persName", name).getResultList();

        return people;
    }

    @Override
    public List<Person> getAllPeople() {

        //actual query
        String sql = "SELECT p FROM Person p";
        List<Person> people = em.createQuery(sql,Person.class).getResultList();

        return people;
    }
}