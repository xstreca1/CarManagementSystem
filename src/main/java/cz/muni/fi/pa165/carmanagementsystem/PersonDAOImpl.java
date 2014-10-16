/*
 * Concrete implementation of data access object for entity Person.
 */
package cz.muni.fi.pa165.carmanagementsystem;

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
    public void createPerson(Person person) {
        //create emf and em in every method because of transactions
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("carManagementSystem-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updatePerson() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletePerson() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person getPersonByID(String personID) {
        String sql = "SELECT * FROM Person person WHERE personID='" + personID + "'";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("carManagementSystem-unit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Person person = em.createQuery(sql, Person.class).getSingleResult();
        em.getTransaction().commit();
        em.close();

        return person;
    }

    @Override
    public List<Person> getPeopleByName(String name) {
        String sql = "SELECT * FROM person WHERE name='" + name + "'";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("carManagementSystem-unit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        List<Person> people = em.createQuery(sql, Person.class).getResultList();
        em.getTransaction().commit();
        em.close();

        return people;
    }

    @Override
    public List<Person> getAllPeople() {
        String sql = "SELECT * FROM person";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("carManagementSystem-unit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        List<Person> people = em.createQuery(sql, Person.class).getResultList();
        em.getTransaction().commit();
        em.close();

        return people;
    }

}
