/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.persistence.dao.ServiceCheckDAO;
import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.ServiceCheck;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martin Strecansky
 */
@Service
@Transactional
public class ServiceCheckServiceImpl implements ServiceCheckServiceInterface {

    // create ServiceCheck DAO
    private ServiceCheckDAO scDAO;

    // setter for ServiceCheck DAO  
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

        // save to database using some implementation od DAO
        scDAO.createServiceCheck(check);
        
    }
    
    public int getDaysToNextServiceCheck(ServiceCheck check) {
        
        Integer checkID = check.getScID();
        int daysToNext = scDAO.getDaysToNext(checkID);
        return daysToNext;
        
    }
    
    public void setCheckInterval(List<Car> carList, ServiceCheck.ServiceCheckName scName, int serviceInterval) {

        // create new list to store service checks with same name
        List<ServiceCheck> checkList = scDAO.getServiceCheckByName(scName);
        
        // Set new serviceInterval for every serviceCheck, which is assigned to some car from list
        for (ServiceCheck sc : checkList) {
            
            Car car = sc.getCar();
            if (carList.contains(car)) {
                sc.setServiceInterval(serviceInterval);
                
            }
            
        }
        
    }
    
    public List<ServiceCheck> getServiceChecksForCar(Car car) {
        
        List<ServiceCheck> checks = scDAO.getServiceChecksForCar(car);
        return checks;
        
    }
    
}
