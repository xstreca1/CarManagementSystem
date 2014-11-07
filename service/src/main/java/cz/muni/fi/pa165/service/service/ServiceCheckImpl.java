/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.persistence.DAO.ServiceCheckDAO;
import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.ServiceCheck;
import cz.muni.fi.pa165.service.dto.CarDTO;
import cz.muni.fi.pa165.service.dto.ServiceCheckDTO;
import java.util.ArrayList;
import java.util.Calendar;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martin Strecansky
 */
@Repository //for transformation of exceptions to DataAccessException
@Transactional //to handle transactions
public class ServiceCheckImpl implements ServiceCheckInterface {

    // ServiceCheck DAO
    private ServiceCheckDAO scDAO;

    @PersistenceContext
    private EntityManager em;

    // setter for ServiceCheck DAO - to be set in applicationContext.xml  
    public void setDao(ServiceCheckDAO scDAO) {
//check if not null
        this.scDAO = scDAO;
    }

    public void createServiceCheck(ServiceCheckDTO checkDTO) {
        //create empty entity
        ServiceCheck checkEntity = null;

        //create empty list
        List<String> list = new ArrayList<String>();

        // map DTO object on Entity
        list.add("dozerMapping.xml");
        Mapper mapper = new DozerBeanMapper(list);

        mapper.map(checkDTO, checkEntity, "servicecheck");

        // start transaction
        em.getTransaction().begin();

        // save to database using some implementation od DAO
        scDAO.createServiceCheck(checkEntity);

        // commit transaction
        em.getTransaction().commit();

    }

    public int getDaysToNextServiceCheck(ServiceCheckDTO checkDTO) {

        //create empty entity
        ServiceCheck checkEntity = null;

        //create empty list
        List<String> list = new ArrayList<String>();

        // map DTO object on Entity
        list.add("dozerMapping.xml");
        Mapper mapper = new DozerBeanMapper(list);

        mapper.map(checkDTO, checkEntity, "servicecheck");

        // start transaction
        em.getTransaction().begin();

        int daysToNext = scDAO.getDaysToNext(checkEntity.getScID());

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

    public List<ServiceCheck> getServiceChecksForCar(CarDTO carDTO) {
        //create empty entity
        Car carEntity = null;

        //create empty list
        List<String> list = new ArrayList<String>();

        // map DTO object on Entity
        list.add("dozerMapping.xml");
        Mapper mapper = new DozerBeanMapper(list);

        mapper.map(carDTO, carEntity, "car");

        // start transaction
        em.getTransaction().begin();
        List<ServiceCheck> checks = scDAO.getServiceChecksForCar(carEntity);
        // commit transaction
        em.getTransaction().commit();
        return checks;

    }

}
