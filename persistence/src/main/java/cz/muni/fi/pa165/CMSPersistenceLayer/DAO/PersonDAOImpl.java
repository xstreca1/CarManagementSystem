/*
 * Concrete implementation of data access object for entity Person.
 */
package cz.muni.fi.pa165.CMSPersistenceLayer.DAO;

import cz.muni.fi.pa165.CMSPersistenceLayer.Entities.Address;
import cz.muni.fi.pa165.CMSPersistenceLayer.Entities.Person;
import cz.muni.fi.pa165.CMSPersistenceLayer.Entities.Person.EmploymentStatus;
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
        
        //actual query
        String sql = "UPDATE Person p SET p.address = :addr, "
                + "p.dateOfBirth = :birth, p.employmentStatus = :empStat,"
                + "p.name = :name, p.nationality = :nationality,"
                + "p.position = :position, p.salary = :salary"
                + "WHERE p.id= :persID";
        
        em.createQuery(sql).setParameter("addr", address)
                .setParameter("birth", dateOfBirth)
                .setParameter("empStat", empStat)
                .setParameter("name", name)
                .setParameter("nationality", nationality)
                .setParameter("position", position)
                .setParameter("salary", salary)
                .setParameter("persID", personID)
                .executeUpdate();

        //commiting changes and closing entity manager
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deletePerson(Integer personID) {

        //create Entity Manager
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("carManagementSystem-unit");
        EntityManager em = emf.createEntityManager();

       

        //person is retrieved
        Person person = getPersonByID(personID);
        
        //begin of a transaction
        em.getTransaction().begin();
        
        //person is removed from Database (TODO - cascading delete?)
        em.remove(em.merge(person));

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
        // check wrong input
        if (name == null){
            throw new IllegalArgumentException("Name can not be null!");
        }
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