/*
 * Concrete implementation of data access object for entity Person.
 */
package cz.muni.fi.pa165.persistence.DAO;

import cz.muni.fi.pa165.persistence.Entities.Address;
import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.Person;
import cz.muni.fi.pa165.persistence.Entities.Person.EmploymentStatus;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Jakub Rumanovsky
 */
public class PersonDAOImpl implements PersonDAO {

    private static EntityManagerFactory emf;       
    
    public PersonDAOImpl(EntityManagerFactory emf){
        this.emf = emf;
    }
    
    public EntityManagerFactory getEntityManagerFactory(){
        return emf;
    }
    
    @Override
    public void insertPerson(Person person) {

        //create Entity Manager
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("carManagementSystem-unit");
        EntityManager em = emf.createEntityManager();

        //begin of a transaction
        em.getTransaction().begin();

        //actual query
        em.persist(person);

        //commiting changes and closing entity manager
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updatePerson(Person updatedPerson, Integer personID) {

        //create Entity Manager
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("carManagementSystem-unit");
        EntityManager em = emf.createEntityManager();

        //begin of a transaction
        em.getTransaction().begin();

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
        em.close();
    }

    @Override
    public void deletePerson(Integer personID) {

        //create Entity Manager
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("carManagementSystem-unit");
        EntityManager em = emf.createEntityManager();

        //begin of a transaction
        em.getTransaction().begin();

        //person is retrieved
        Person person = getPersonByID(personID);
        
        //person is removed from Database (TODO - cascading delete?)
        em.remove(person);

        //commiting changes and closing entity manager
        em.getTransaction().commit();
        em.close();
        
        //can also be done by em.executeQuery(DELETE_QUERY).executeUpdate();
    }

    @Override
    public Person getPersonByID(Integer personID) {

        //create Entity Manager
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("carManagementSystem-unit");
        EntityManager em = emf.createEntityManager();

        //begin of a transaction
        em.getTransaction().begin();

        //actual query
        String sql = "SELECT p FROM Person p WHERE p.id=:persID";
        Person person = em.createQuery(sql, Person.class)
                .setParameter("persID", personID).getResultList().get(0);

        //commiting changes and closing entity manager
        em.getTransaction().commit();
        em.close();

        return person;
    }

    @Override
    public List<Person> getPeopleByName(String name) {

        //create emf and em in every method because of transactions
        //EntityManagerFactory emf
                //= Persistence.createEntityManagerFactory("carManagementSystem-unit");
        EntityManager em = emf.createEntityManager();

        //begin of a transaction
        em.getTransaction().begin();

        //actual query
        String sql = "SELECT p FROM Person p WHERE p.name= :persName";
        List<Person> people = em.createQuery(sql, Person.class).
                setParameter("persName", name).getResultList();

        //commiting changes and closing entity manager
        em.getTransaction().commit();
        em.close();

        return people;
    }

    @Override
    public List<Person> getAllPeople() {

        //create emf and em in every method because of transactions
        //EntityManagerFactory emf
                //= Persistence.createEntityManagerFactory("carManagementSystem-unit");
        EntityManager em = emf.createEntityManager();

        //begin of a transaction
        em.getTransaction().begin();

        //actual query
        String sql = "SELECT p FROM Person p";
        List<Person> people = em.createQuery(sql,Person.class).getResultList();

        //commiting changes and closing entity manager
        em.getTransaction().commit();
        em.close();

        return people;
    }
}