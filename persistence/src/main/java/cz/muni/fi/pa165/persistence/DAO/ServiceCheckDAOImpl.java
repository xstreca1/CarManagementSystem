/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.persistence.DAO;

import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.ServiceCheck;
import cz.muni.fi.pa165.persistence.Entities.ServiceCheck.ServiceCheckName;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Martin Strecansky
 */
public class ServiceCheckDAOImpl implements ServiceCheckDAO {

    private EntityManagerFactory emf;

    public ServiceCheckDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    @Override
    public void createServiceCheck(ServiceCheck serviceCheck) {
        // testing method parameters
        if (serviceCheck == null) {
            throw new IllegalArgumentException("serviceCheck is null");
        }
        // create new EntityManager and save instance of ServiceCheck to database
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(serviceCheck);
        em.getTransaction().commit();
        em.close();

    }

    @Override
    public void updateServiceCheck(ServiceCheck serviceCheck, Integer scID) {
        // testing method parameters
        if (serviceCheck == null) {
            throw new IllegalArgumentException("serviceCheck is null");
        }
        if (scID == null) {
            throw new IllegalArgumentException("service check ID is null");
        }
        // create new EntityManager
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // get instance of ServiceCheck according to its ID. Save this instance to variable "update"
        String query = "SELECT s FROM ServiceCheck s WHERE s.scID = :ID";;
        ServiceCheck update = em.createQuery(query, ServiceCheck.class).setParameter("ID", scID).getSingleResult();

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

    @Override
    public void deleteServiceCheck(Integer scID) {
        // testing method parameters
        if (scID == null) {
            throw new IllegalArgumentException("service check ID is null");
        }
        // create new EntityManager
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // delete serviceCheck from database according to its ID
        String query = "SELECT s FROM ServiceCheck s WHERE s.scID = :ID";
        ServiceCheck toDelete = em.createQuery(query, ServiceCheck.class).setParameter("ID", scID).getSingleResult();
        em.remove(em.merge(toDelete));
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public int getDaysToNext(Integer scID) {
        // testing method parameters
        if (scID == null) {
            throw new IllegalArgumentException("service check ID is null");
        }
        // create new EntityManager
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // get serviceCheck from database according to its ID
        String query = "SELECT s FROM ServiceCheck s WHERE s.scID = :ID";
        ServiceCheck serviceCheck = em.createQuery(query, ServiceCheck.class).setParameter("ID", scID).getSingleResult();

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
        // testing method parameters
        if (car == null) {
            throw new IllegalArgumentException("car is null");
        }
        // create new EntityManager
        EntityManager em = emf.createEntityManager();

        // get information about serviceCheck from database according to car (car is able to have more chcecks assigned). Save them to List.
        String query = "SELECT s FROM ServiceCheck s WHERE s.car= :scCar";
        List<ServiceCheck> serviceChecks = em.createQuery(query).setParameter("scCar", car).getResultList();

        // cloese EntityManager
        em.getTransaction().commit();
        em.close();

        return serviceChecks;

    }

}
