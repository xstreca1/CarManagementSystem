/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service.Entities;

import cz.muni.fi.pa165.persistence.DAO.ServiceCheckDAO;
import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.ServiceCheck;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martin Strecansky
 */
@Service
@Transactional
public class ServiceCheckImpl implements ServiceCheckInterface {

    // ServiceCheck DAO
    private ServiceCheckDAO scDAO;
    
    // EntityManagmentFactory
    EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("carManagementSystem-unit");
        
    // Entity Manager
    private EntityManager em = emf.createEntityManager();
    

    // setter for ServiceCheck DAO - to be set in applicationContext.xml  
    public void setDao(ServiceCheckDAO scDAO) {

        this.scDAO = scDAO;
    }    

    public void createServiceCheck(ServiceCheck.ServiceCheckName name, String description, Car car, int serviceInterval) {
        // create instance of calendar to find out current time which will be used as paramter fo lastCheck
        Calendar now = Calendar.getInstance();

        // create new serviceCheck, set lastCheck as current date
        ServiceCheck check = new ServiceCheck();
        check.setName(name);
        check.setDescription(description);
        check.setCar(car);
        check.setServiceInterval(serviceInterval);
        check.setLastCheck(now.getTime());

        // start transaction
        em.getTransaction().begin();

        // save to database using some implementation od DAO
        scDAO.createServiceCheck(check);

        // commit transaction
        em.getTransaction().commit();

    }

    public int getDaysToNextServiceCheck(ServiceCheck check) {

        Integer checkID = check.getScID();

        // start transaction
        em.getTransaction().begin();

        int daysToNext = scDAO.getDaysToNext(checkID);

        // commit transaction
        em.getTransaction().commit();

        return daysToNext;

    }

    public void setCheckInterval(List<Car> carList, ServiceCheck.ServiceCheckName scName, int serviceInterval) {

        // create new list to store service checks with same name
        // start transaction
        em.getTransaction().begin();

        List<ServiceCheck> checkList = scDAO.getServiceCheckByName(scName);

        // commit transaction
        em.getTransaction().commit();

        // Set new serviceInterval for every serviceCheck, which is assigned to some car from list
        for (ServiceCheck sc : checkList) {

            Car car = sc.getCar();
            if (carList.contains(car)) {
                em.getTransaction().begin();
                scDAO.updateInterval(serviceInterval, sc.getScID());
                em.getTransaction().commit();

            }

        }

    }

    public List<ServiceCheck> getServiceChecksForCar(Car car) {

        // start transaction
        em.getTransaction().begin();
        List<ServiceCheck> checks = scDAO.getServiceChecksForCar(car);
         // commit transaction
        em.getTransaction().commit();
        return checks;

    }

}
