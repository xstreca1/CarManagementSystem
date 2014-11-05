package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.ServiceCheck;
import cz.muni.fi.pa165.persistence.Entities.ServiceCheck.ServiceCheckName;
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
     * @param name - name of service check
     * @param description - service check description
     * @param car - car to which is this service check assigned
     * @param serviceInterval - interval in months
     * 
     */   

    void createServiceCheck(ServiceCheckName name, String description, Car car, int serviceInterval);

    /**
     * Method, which finds out number of days remaining to next performance of some control
     * 
     * @param check - ServiceCheck for which the number of days to next performance will be calculated
     * @return number of days - int
     */
    int getDaysToNextServiceCheck(ServiceCheck check);
    
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
     * @param car - car for which we want to find out all serviceChecks
     * @return list of ServiceChecks
     */

    List<ServiceCheck> getServiceChecksForCar(Car car); 
    
    
}
