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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martin Strecansky
 */

@Service("serviceCheckService")
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
        
        // save to database using some implementation od DAO
        scDAO.createServiceCheck(checkEntity);

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

        int daysToNext = scDAO.getDaysToNext(checkEntity.getScID());
        
        return daysToNext;

    }

    public void setCheckInterval(List<Car> carList, ServiceCheck.ServiceCheckName scName, int serviceInterval) {

        // create new list to store service checks with same name
        // start transaction

        List<ServiceCheck> checkList = scDAO.getServiceCheckByName(scName);

        // Set new serviceInterval for every serviceCheck, which is assigned to some car from list
        for (ServiceCheck sc : checkList) {

            Car car = sc.getCar();
            if (carList.contains(car)) {
                scDAO.updateInterval(serviceInterval, sc.getScID());

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

        List<ServiceCheck> checks = scDAO.getServiceChecksForCar(carEntity);

        return checks;

    }

}
