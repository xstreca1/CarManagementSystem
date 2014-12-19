package cz.muni.fi.pa165.service.service;


import cz.muni.fi.pa165.service.dto.CarDTO;
import cz.muni.fi.pa165.service.dto.ServiceCheckDTO;
import java.util.List;

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
     * Method which finds out all serviceChecks assigned to some car
     * 
     * @param carDTO - car for which we want to find out all serviceChecks
     * @return list of ServiceChecks
     */

    List<ServiceCheckDTO> getServiceChecksForCar(CarDTO carDTO);
    
    public List<ServiceCheckDTO> findAllChecks();
    public ServiceCheckDTO getCheckByID(Integer id);
    public void updateCheck(ServiceCheckDTO scDto, Integer scID);
    
}
