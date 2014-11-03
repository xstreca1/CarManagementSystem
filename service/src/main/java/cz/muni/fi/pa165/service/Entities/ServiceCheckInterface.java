package cz.muni.fi.pa165.service.Entities;

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
 * @author Petr Potucek
 */

public interface ServiceCheckInterface {
    /**
     * List all service checks for the particular car
     * 
     * @param car car to which the check is assigned 
     */
    List<ServiceCheck> getChecksForCar(Car car);
    
    /**
     * It creates mew service check with listed parameters
     * Sets all parameters for the check and by persistence layer it create it 
     * in the database
     * 
     * @param name name of service check
     * @param description description of the service chcek
     * @param car car, to which service check is assigned
     * @param serviceInterval tima from last to the next check
     * @param lastCheck date of the last check
     */
    void createNewCheck(ServiceCheckName name,String description,Car car, int serviceInterval,Date lastCheck);
    
    /**
     * Updates an existing description
     * 
     * @param scID id of check to be updated
     */
    void setDescription(Integer scID, String description);
    
    /**
     * delete service check by id from the database
     * 
     * @param ScID id of check
     */
    void deleteServiceCheck(Integer ScID);
    
    /**
     * shows number of days to next service check
     * 
     * @param ScID id of check
     */
    void showDaysToNextServiceCheck(Integer ScID);
    
    /**
     * shows number of days to next check
     * 
     * @param ScID id of check
     * @param name new name of the service check
     */
    void changeNameOfCheck(Integer ScID, ServiceCheckName name);
}
