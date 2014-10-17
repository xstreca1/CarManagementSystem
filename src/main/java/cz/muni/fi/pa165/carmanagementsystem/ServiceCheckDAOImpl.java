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

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("createSC-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(serviceCheck);
        em.getTransaction().commit();
        em.close();

    }

    public void updateServiceCheck(ServiceCheck serviceCheck, Integer scID) {
        if ((scID == null) || (serviceCheck == null)) {
            throw new NullPointerException();
        }
        // start in memory database
        new AnnotationConfigApplicationContext(DaoContext.class);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("updateSC-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        String query = "SELECT * FROM ServiceCheck serviceCheck WHERE scID='" + scID + "'";
        ServiceCheck update = em.createQuery(query, ServiceCheck.class).getSingleResult();
        ServiceCheckName newName = serviceCheck.getName();
        int newServiceInterval = serviceCheck.getServiceInterval();
        Date newLastCheck = serviceCheck.getLastCheck();
        String newDescription = serviceCheck.getDescription();
        Car newCar = serviceCheck.getCar();

        update.setName(newName);
        update.setServiceInterval(newServiceInterval);
        update.setLastCheck(newLastCheck);
        update.setDescription(newDescription);
        update.setCar(newCar);

        em.persist(update);
        em.getTransaction().commit();
        em.close();

    }

    public void deleteServiceCheck(Integer scID) {
        if (scID == null) {
            throw new NullPointerException();
        }
        // start in memory database
        new AnnotationConfigApplicationContext(DaoContext.class);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("deleteSC-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        String query = "DELETE * FROM ServiceCheck WHERE scID='" + scID + "'";
        em.createQuery(query).executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public int getDaysToNext(Integer scID) {
        if (scID == null) {
            throw new NullPointerException();
        }
        // start in memory database
        new AnnotationConfigApplicationContext(DaoContext.class);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("getDaysToSC-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        String query = "SELECT * FROM ServiceCheck serviceCheck WHERE scID='" + scID + "'";
        ServiceCheck serviceCheck = em.createQuery(query, ServiceCheck.class).getSingleResult();
        Date lastCheck = serviceCheck.getLastCheck();
        int interval = serviceCheck.getServiceInterval();

        em.getTransaction().commit();
        em.close();

        Calendar nextControl = Calendar.getInstance();
        nextControl.setTime(lastCheck);
        nextControl.add(Calendar.MONTH, interval);

        Calendar now = Calendar.getInstance();
        now.getTime();

        int days = nextControl.get(Calendar.DATE) - now.get(Calendar.DATE);
        return days;

    }

    @Override
    public List getServiceChecksForCar(Car car) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("getSCforCar-unit");
        EntityManager em = emf.createEntityManager();
        String query = "SELECT * FROM ServiceCheck WHERE car='" + car + "'";

        List<ServiceCheck> serviceChecks = em.createQuery(query).getResultList();

        em.close();
        
        return serviceChecks;

    }

}
