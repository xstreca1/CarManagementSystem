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
        //create emf and em in every method because of transactions
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("carManagementSystem-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updatePerson(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletePerson(String personID) {
         String sql = "DELETE * FROM Person person WHERE person.personID=:personID";//'" + personID + "'";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("carManagementSystem-unit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.createQuery(sql).executeUpdate();
        em.getTransaction().commit();
        em.close();
        //stackoverflow.com/questions/2848164/jpa-entitymanager-remove-operation-is-not-performant
    }

    @Override
    public Person getPersonByID(String personID) {
        String sql = "SELECT * FROM Person person WHERE personID='" + personID + "'";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("carManagementSystem-unit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Person person = em.createQuery(sql, Person.class).getResultList().get(0);
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
