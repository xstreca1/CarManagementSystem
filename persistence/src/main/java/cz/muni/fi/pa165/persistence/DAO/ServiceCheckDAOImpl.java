/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.persistence.DAO;

import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.Lease;
import cz.muni.fi.pa165.persistence.Entities.ServiceCheck;
import cz.muni.fi.pa165.persistence.Entities.ServiceCheck.ServiceCheckName;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martin Strecansky
 */

@Repository("serviceCheckDAO") //for transformation of exceptions to DataAccessException
public class ServiceCheckDAOImpl implements ServiceCheckDAO {

    @PersistenceContext(name = "carManagementSystem-unit")
    private EntityManager em;

    // constructor with no parameter
    public ServiceCheckDAOImpl() {

    }

    public ServiceCheckDAOImpl(EntityManager entityManager) {
        if (entityManager == null) {
            throw new IllegalArgumentException("argument 'em' must be set");
        }
        em = entityManager;
    }

    @Override
    public ServiceCheck createServiceCheck(ServiceCheck serviceCheck) {
        // testing method parameters
        if (serviceCheck == null) {
            throw new IllegalArgumentException("serviceCheck is null");
        }

        em.persist(serviceCheck);
        return serviceCheck;
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
        // get instance of ServiceCheck according to its ID. Save this instance to variable serviceCheck2
        ServiceCheck serviceCheck2 = (ServiceCheck) em.find(ServiceCheck.class, scID);

        // get new values of attributes
        ServiceCheckName newName = serviceCheck.getName();//netreba
        int newServiceInterval = serviceCheck.getServiceInterval();
        Date newLastCheck = serviceCheck.getLastCheck(); //nope
        String newDescription = serviceCheck.getDescription();
        Car newCar = serviceCheck.getCar();//no

        // replace actual values with new values
        serviceCheck2.setName(newName);
        serviceCheck2.setServiceInterval(newServiceInterval);
        serviceCheck2.setLastCheck(newLastCheck);
        serviceCheck2.setDescription(newDescription);
        serviceCheck2.setCar(newCar);

    }

    @Override
    public void deleteServiceCheck(Integer scID) {
        // testing method parameters
        if (scID == null) {
            throw new IllegalArgumentException("service check ID is null");
        }
        // delete serviceCheck from database according to its ID
        String query = "SELECT s FROM ServiceCheck s WHERE s.scID = :ID";
        ServiceCheck toDelete = em.createQuery(query, ServiceCheck.class).setParameter("ID", scID).getSingleResult();
        em.remove(em.merge(toDelete));
    }

    @Override
    public int getDaysToNext(Integer scID) {
        // testing method parameters
        if (scID == null) {
            throw new IllegalArgumentException("service check ID is null");
        }
        // get serviceCheck from database according to its ID
        String query = "SELECT s FROM ServiceCheck s WHERE s.scID = :ID";
        ServiceCheck serviceCheck = em.createQuery(query, ServiceCheck.class).setParameter("ID", scID).getSingleResult();

        // get date of last performance of this serviceCheck
        Date lastCheck = serviceCheck.getLastCheck();

        // get interval of this serviceCheck
        int interval = serviceCheck.getServiceInterval();

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

        // get information about serviceCheck from database according to car (car is able to have more chcecks assigned). Save them to List.
        String query = "SELECT s FROM ServiceCheck s WHERE s.car= :scCar";
        List<ServiceCheck> serviceChecks = em.createQuery(query).setParameter("scCar", car).getResultList();

        return serviceChecks;

    }

    public List<ServiceCheck> getServiceCheckByName(ServiceCheckName name) {

        if (name == null) {
            throw new IllegalArgumentException("service check name is null");
        }

        //actual query
        String sql = "SELECT s FROM ServiceCheck s WHERE s.name= :scName";
        List<ServiceCheck> checks = em.createQuery(sql, ServiceCheck.class).
                setParameter("scName", name).getResultList();

        return checks;

    }

    public void updateInterval(int interval, Integer scID) {

        // get instance of ServiceCheck according to its ID. Save this instance to variable "serviceCheck2"
        ServiceCheck serviceCheck2 = (ServiceCheck) em.find(ServiceCheck.class, scID);

        // set new value of serviceInterval      
        serviceCheck2.setServiceInterval(interval);

    }
    
    @Override
    public List<ServiceCheck> findAllChecks(){
    
         String sql = "SELECT l FROM Lease l";
        List<ServiceCheck> checks = em.createQuery(sql,ServiceCheck.class).getResultList();

        return checks;
    }

}
