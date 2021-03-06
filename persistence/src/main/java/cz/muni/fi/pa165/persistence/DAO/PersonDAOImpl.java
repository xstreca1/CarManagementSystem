/*
 * Concrete implementation of data access object for entity Person.
 */
package cz.muni.fi.pa165.persistence.DAO;


import cz.muni.fi.pa165.persistence.Entities.Person;
import cz.muni.fi.pa165.persistence.Entities.Person.EmploymentStatus;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jakub Rumanovsky
 */

@Repository("personDAO") //for transformation of exceptions to DataAccessException
public class PersonDAOImpl implements PersonDAO {

    @PersistenceContext(name="carManagementSystem-unit")
    private EntityManager em;
    
    public PersonDAOImpl () {
        
    }
    
    public PersonDAOImpl(EntityManager entityManager) {
		if (entityManager == null) {
			throw new IllegalArgumentException("argument 'em' must be set");
		}
		em = entityManager;
	}
    
    @Override
    public Person insertPerson(Person person) {

        if (person == null) {
            throw new IllegalArgumentException("argument Person nemoze byt null");
        }
        //actual query
        em.persist(person);
        
        return person;

    }

    @Override
    public void updatePerson(Person updatedPerson, Integer personID) {

        //get all updatable attributes from the updated person entity instance
        //lease and sex could not be updated
        //Address address = updatedPerson.getAddress();
        //Date dateOfBirth = updatedPerson.getDateOfBirth();
        EmploymentStatus empStat = updatedPerson.getEmploymentStatus();
        String name = updatedPerson.getName();
        String nationality = updatedPerson.getNationality();
        String position = updatedPerson.getPosition();
        Boolean isActive = updatedPerson.getIsActive();
        int salary = updatedPerson.getSalary();
        
        Person person1 = (Person)em.find(Person.class ,personID);
        
        //change attributes of persisted entity in DB
        //person1.setAddress(address);
        person1.setEmploymentStatus(empStat);
        person1.setName(name);
        person1.setNationality(nationality);
        person1.setPosition(position);
        person1.setSalary(salary);
        person1.setIsActive(isActive);
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
    public Person getPersonByUsername(String username) {

        //actual query
        String sql = "SELECT p FROM Person p WHERE p.username=:username";
        Person person = em.createQuery(sql, Person.class)
                .setParameter("username", username).getResultList().get(0);

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
    public List<Person> findAllPeople(Boolean alsoInactive) {

        String query = "SELECT c FROM Person c WHERE c.isActive = :alsoInactive";
        List<Person> people = em.createQuery(query, Person.class).
                setParameter("alsoInactive", alsoInactive).getResultList();

        return people;
    }
}