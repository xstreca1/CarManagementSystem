/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.persistence.DAO;

import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.ServiceCheck;
import cz.muni.fi.pa165.persistence.Entities.ServiceCheck.ServiceCheckName;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
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
        Date newNextCheck=serviceCheck.getNextCheck();

        // replace actual values with new values
        serviceCheck2.setName(newName);
        serviceCheck2.setServiceInterval(newServiceInterval);
        serviceCheck2.setLastCheck(newLastCheck);
        serviceCheck2.setDescription(newDescription);
        serviceCheck2.setCar(newCar);
        serviceCheck2.setNextCheck(newNextCheck);

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
    
    @Override
    public List<ServiceCheck> findAllChecks(){
    
         String sql = "SELECT s FROM ServiceCheck s";
        List<ServiceCheck> checks = em.createQuery(sql,ServiceCheck.class).getResultList();

        return checks;
    }
    
     @Override
    public ServiceCheck getCheckByID(Integer scID) {

        //actual query
        String sql = "SELECT s FROM ServiceCheck s WHERE s.scID=:id";
        ServiceCheck check = em.createQuery(sql, ServiceCheck.class)
                .setParameter("id", scID).getResultList().get(0);

        return check;
    }

}
