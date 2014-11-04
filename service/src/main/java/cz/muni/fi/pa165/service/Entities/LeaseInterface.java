package cz.muni.fi.pa165.service.Entities;

import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.Lease;
import cz.muni.fi.pa165.persistence.Entities.Lease.ReturnedStatus;
import cz.muni.fi.pa165.persistence.Entities.Lease.TravelReason;
import cz.muni.fi.pa165.persistence.Entities.Person;
import java.util.Date;
import java.util.List;

/**
 * Interface for Lease on service layer
 * 
 * @author Petr Potucek
 */
public interface LeaseInterface {
    /**
     * Create lease, sets parameters and store it in the database by DAO 
     * 
     * @param car leased car
     * @param person person who wants a lease
     * @param travelReason reason of lease WORK, PERSONAL
     * @param carMileage how many km person suppose to drive on the lease
     * @param dateOfLease date, when the car was leased
     * @param dateOfReturn date, when the lease was returned
     */
    void createLease(Car car, Person person, TravelReason travelReason, int carMileage, Date dateOfLease, Date dateOfReturn);
    
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
    List<Lease> getLeaseByPerson(Person person);
    
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
    Lease getLeaseById(int id);
    
    /**
     * Delete lease from the database by id
     * 
     * @param leaseId id of lease to be deleted 
     */
    void deleteLease(int leaseId);
    
}
