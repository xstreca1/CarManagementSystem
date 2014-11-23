package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.persistence.Entities.Lease;
import cz.muni.fi.pa165.persistence.Entities.Lease.ReturnedStatus;
import cz.muni.fi.pa165.persistence.Entities.Person;
import cz.muni.fi.pa165.service.dto.LeaseDTO;
import cz.muni.fi.pa165.service.dto.PersonDTO;
import java.util.Date;
import java.util.List;

/**
 * Interface for Lease on service layer
 * 
 * @author Petr Potucek
 */
public interface LeaseServiceInterface {
    /**
     * Create lease in database
     * 
     * @param leaseDTO data transfer object mapped to Lease, to be created
     *                 in the database by DAO
     */
    void createLease(LeaseDTO leaseDTO);
    
    /**
     * sets status of car, when it is returned (RETURNED, BROKEN)
     * 
     * @param id id of lease to be updated
     */
    void setReturnedStatus(int id, ReturnedStatus status);
    
    /**
     * Gives a leases for a particular person
     * 
     * @param person person with leases
     * @return return list of leases for person
     */
    List<LeaseDTO> getLeaseByPerson(PersonDTO personDto);
    
    /**
     * Returns all leases in the period
     * 
     * @param from date from I want to display leases
     * @param to date to which I want to displaz leases
     * @return list of leases in that period
     */
    List<Lease> getAllLeases(Date from, Date to);
    
    /**
     * Gives a lease with given id
     * 
     * @param id id of lease
     * @return listlease with given id
     */
    
    
    /**
     * Method displays leases of the person in certain period
     * 
     * @param person person to show
     * @param from date from which it will be showed
     * @param to date to it will be showed
     * @return list of leases
     */
    List<Lease> getTravelStatistics(Person person, Date from, Date to);
    
    LeaseDTO getLeasyByID(Integer id);
    
}
