/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service.Entities;

import cz.muni.fi.pa165.persistence.DAO.ServiceCheckDAO;
import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.ServiceCheck;
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
public class ServiceCheckImpl implements ServiceCheckInterface {
    
    // create ServiceCheck DAO
    private ServiceCheckDAO scDAO;
    
    // setter for ServiceCheck DAO  
    public void setDao(ServiceCheckDAO scDAO) {
        this.scDAO = scDAO;
    }

    public void createServiceCheck(ServiceCheck.ServiceCheckName name, String description, Car car, int serviceInterval){
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
    
    public int getDaysToNextServiceCheck(ServiceCheck check){
    
        Integer checkID = check.getScID();
        int daysToNext = scDAO.getDaysToNext(checkID);
        return daysToNext;
        
    }
    
    public void setCheckInterval (List<Car> carList, ServiceCheck.ServiceCheckName scName){
    
        
        
    }
    
    public List<ServiceCheck> getServiceChecksForCar(Car car){
    
        List<ServiceCheck> checks = scDAO.getServiceChecksForCar(car);
        return checks;
        
    }

    
}
