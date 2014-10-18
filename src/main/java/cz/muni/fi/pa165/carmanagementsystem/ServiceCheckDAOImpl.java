/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carmanagementsystem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Martin Strecansky
 */
public class ServiceCheckDAOImpl implements ServiceCheckDAO {

    public void createServiceCheck(ServiceCheck serviceCheck) {
        // start in memory database
        new AnnotationConfigApplicationContext(DaoContext.class);

        // create new EntityManager and save instance of ServiceCheck to database
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("createSC-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(serviceCheck);
        em.getTransaction().commit();
        em.close();

    }

    public void updateServiceCheck(ServiceCheck serviceCheck, int scID) {
        // start in memory database
        new AnnotationConfigApplicationContext(DaoContext.class);

        // create new EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("updateSC-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // get instance of ServiceCheck according to its ID. Save this instance to variable "update"
        String query = "SELECT * FROM ServiceCheck serviceCheck WHERE scID='" + scID + "'";
        ServiceCheck update = em.createQuery(query, ServiceCheck.class).getSingleResult();

        // get new values of attributes
        ServiceCheckName newName = serviceCheck.getName();
        int newServiceInterval = serviceCheck.getServiceInterval();
        Date newLastCheck = serviceCheck.getLastCheck();
        String newDescription = serviceCheck.getDescription();
        Car newCar = serviceCheck.getCar();

        // replace actual values with new values
        update.setName(newName);
        update.setServiceInterval(newServiceInterval);
        update.setLastCheck(newLastCheck);
        update.setDescription(newDescription);
        update.setCar(newCar);

        // save updated serviceCheck to database
        em.persist(update);
        em.getTransaction().commit();
        em.close();

    }

    public void deleteServiceCheck(int scID) {
        // start in memory database
        new AnnotationConfigApplicationContext(DaoContext.class);

        // create new EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("deleteSC-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // delete serviceCheck from database according to its ID
        String query = "DELETE * FROM ServiceCheck WHERE scID='" + scID + "'";
        em.createQuery(query).executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public int getDaysToNext(int scID) {
        // start in memory database
        new AnnotationConfigApplicationContext(DaoContext.class);

        // create new EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("getDaysToSC-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // get serviceCheck from database according to its ID
        String query = "SELECT * FROM ServiceCheck serviceCheck WHERE scID='" + scID + "'";
        ServiceCheck serviceCheck = em.createQuery(query, ServiceCheck.class).getSingleResult();

        // get date of last performance of this serviceCheck
        Date lastCheck = serviceCheck.getLastCheck();

        // get interval of this serviceCheck
        int interval = serviceCheck.getServiceInterval();

        // end transaction and close EntityManager
        em.getTransaction().commit();
        em.close();

        // create instance of Calenar and set date to date of last performance of this serviceCheck
        Calendar nextControl = Calendar.getInstance();
        nextControl.setTime(lastCheck);

        // add number of months to date of last performance according to inetrval of this check, to find out, when the next check should be performed
        nextControl.add(Calendar.MONTH, interval);

        // get current date
        Calendar now = Calendar.getInstance();
        now.getTime();

        // find out number of days between current date and date of next performance and return result 
        int days = nextControl.get(Calendar.DATE) - now.get(Calendar.DATE);
        return days;

    }

    @Override
    public List getServiceChecksForCar(Car car) {

        // create new EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("getSCforCar-unit");
        EntityManager em = emf.createEntityManager();

        // get information about serviceCheck from database according to car (car is able to have more chcecks assigned). Save them to List.
        String query = "SELECT * FROM ServiceCheck WHERE car='" + car + "'";
        List<ServiceCheck> serviceChecks = em.createQuery(query).getResultList();

        // cloese EntityManager
        em.getTransaction().commit();
        em.close();

        return serviceChecks;

    }

}
