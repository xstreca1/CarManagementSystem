package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.persistence.DAO.PersonDAO;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martin Strecansky
 */
@Service("serviceCheckService")
@Transactional //to handle transactions
public class ServiceCheckImpl implements ServiceCheckInterface {

    @Autowired
    // ServiceCheck DAO
    private ServiceCheckDAO scDAO;

    @PersistenceContext
    private EntityManager em;

    // setter for ServiceCheck DAO - to be set in applicationContext.xml  
    //public void setDao(ServiceCheckDAO scDAO) {
//check if not null
    //this.scDAO = scDAO;
    //}
    public void createServiceCheck(ServiceCheckDTO checkDTO) {
   
        //create empty entity
        ServiceCheck checkEntity = new ServiceCheck();

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
        ServiceCheck checkEntity = new ServiceCheck();

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

    public List<ServiceCheckDTO> getServiceChecksForCar(CarDTO carDTO) {

        //create empty entity
        Car carEntity = new Car();
        
        ServiceCheckDTO checkDTO = new ServiceCheckDTO();

        //create empty list
        List<String> list = new ArrayList<String>();

        List<ServiceCheckDTO> checkListDTO = new ArrayList<ServiceCheckDTO>();

        // map DTO object on Entity
        list.add("dozerMapping.xml");
        Mapper mapper = new DozerBeanMapper(list);

        mapper.map(carDTO, carEntity, "car");

        List<ServiceCheck> checks = scDAO.getServiceChecksForCar(carEntity);
        for (ServiceCheck s : checks) {
            mapper.map(s, checkDTO, "servicecheck");
            checkListDTO.add(checkDTO);

        }

        return checkListDTO;

    }
    
    @Override
    public List<ServiceCheckDTO> findAllChecks() {
        //create empty list
        List<String> list = new ArrayList<String>();

        List<ServiceCheckDTO> checksDTO = new ArrayList<ServiceCheckDTO>();

        ServiceCheckDTO checkDTO = new ServiceCheckDTO();

        // map DTO object on Entity
        list.add("dozerMapping.xml");
        Mapper mapper = new DozerBeanMapper(list);

        List<ServiceCheck> checks = scDAO.findAllChecks();

        for (ServiceCheck c : checks) {
            checkDTO = mapper.map(c, ServiceCheckDTO.class);
            checksDTO.add(checkDTO);
        }

        return checksDTO;
    }

   

    

}