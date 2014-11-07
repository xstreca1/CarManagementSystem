package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.ServiceCheck;
import cz.muni.fi.pa165.persistence.Entities.ServiceCheck.ServiceCheckName;
import cz.muni.fi.pa165.service.dto.CarDTO;
import cz.muni.fi.pa165.service.dto.ServiceCheckDTO;
import java.util.Date;
import java.util.List;
import javax.activation.DataSource;

/**
 * Interface for the service layer of Service Check
 *
 *
 * @author Martin Strecansky
 */
public interface ServiceCheckInterface {

    /**
     * Method which creates instance of ServiceCheck to be saved to database.
     * 
     * @param checkDTO - DTO object
     * 
     */   

    void createServiceCheck(ServiceCheckDTO checkDTO);

    /**
     * Method, which finds out number of days remaining to next performance of some control
     * 
     * @param checkDTO - ServiceCheck for which the number of days to next performance will be calculated
     * @return number of days - int
     */
    int getDaysToNextServiceCheck(ServiceCheckDTO checkDTO);
    
    /**
     * Method which can edit interval of serviceCheck
     * 
     * @param carList - list of cars for which the service check will be edited
     * @param scName - type of serviceCheck to be edited
     * @param serviceInterval - new value of service interval
     */

    void setCheckInterval (List<Car> carList, ServiceCheckName scName, int serviceInterval);
    
    /**
     * Method which finds out all serviceChecks assigned to some car
     * 
     * @param carDTO - car for which we want to find out all serviceChecks
     * @return list of ServiceChecks
     */

    List<ServiceCheckDTO> getServiceChecksForCar(CarDTO carDTO); 
    
    
}
