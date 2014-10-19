/*
 * Concrete implementation of data access object for entity Person.
 */
package cz.muni.fi.pa165.carmanagementsystem.DAO;

import cz.muni.fi.pa165.carmanagementsystem.Entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jakub Rumanovsky
 */
public class PersonDAOImpl implements PersonDAO {

    @Override
    public void insertPerson(Person person) {

        //create Entity Manager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("carManagementSystem-unit");
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
    public void updatePerson(Person updatedPerson, String personID) {

        //create Entity Manager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("carManagementSystem-unit");
        EntityManager em = emf.createEntityManager();

        //begin of a transaction
        em.getTransaction().begin();

        
        //actual query
        String sql = "UPDATE Person p SET a WHERE p.personID= :persID";
        em.createQuery(sql).setParameter("persID", personID).executeUpdate();

        //commiting changes and closing entity manager
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deletePerson(String personID) {

        //create Entity Manager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("carManagementSystem-unit");
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
    }

    @Override
    public Person getPersonByID(String personID) {

        //create Entity Manager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("carManagementSystem-unit");
        EntityManager em = emf.createEntityManager();

        //begin of a transaction
        em.getTransaction().begin();

        //actual query
        String sql = "SELECT p FROM Person p WHERE p.personID= :persID";
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
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("carManagementSystem-unit");
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
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("carManagementSystem-unit");
        EntityManager em = emf.createEntityManager();

        //begin of a transaction
        em.getTransaction().begin();

        //actual query
        String sql = "SELECT p FROM Person p";
        List<Person> people = em.createQuery(sql, Person.class).getResultList();

        //commiting changes and closing entity manager
        em.getTransaction().commit();
        em.close();

        return people;
    }
}