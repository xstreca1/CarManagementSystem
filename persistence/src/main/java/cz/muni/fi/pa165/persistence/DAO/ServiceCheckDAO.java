/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.persistence.DAO;

import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.ServiceCheck;
import cz.muni.fi.pa165.persistence.Entities.ServiceCheck.ServiceCheckName;
import java.util.List;

/**
 *
 * @author Martin Strecansky
 */
public interface ServiceCheckDAO {

    /**
     * Method used to insert instance of ServiceCheck class to database
     *
     * @param seviceCheck - instance of class ServiceCheck
     */
    public ServiceCheck createServiceCheck(ServiceCheck seviceCheck);

    /**
     * Method used to get the instance of ServiceCheck class from database
     * according to its ID, update attributes of this instance using attributes
     * of new instance of ServiceCheck which is another parameter of this
     * method. Then save this updated serviceCheck to database
     *
     * @param serviceCheck
     * 
     * @param scID - ID of serviceCheck to be updated with new values
     */
    public void updateServiceCheck(ServiceCheck serviceCheck, Integer scID); 

   
    /**
     * Method used to get all service checks assigned to some car
     *
     * @param car
     * @return List of service checks
     */
    public List getServiceChecksForCar(Car car);
    
    /**
     * Get service checks by name
     * 
     * @param name
     * @return list of serviceChecks
     */
    
    public List<ServiceCheck> getServiceCheckByName(ServiceCheckName name);
       
    
    public List<ServiceCheck> findAllChecks();
    
    public ServiceCheck getCheckByID(Integer scID);

}
