package cz.muni.fi.pa165.persistence.DAO;

import cz.muni.fi.pa165.persistence.Entities.Lease;
import java.util.Date;
import java.util.List;

/**
 * An interface specifiing functionality of lease
 * 
 * @author Petr Potucek
 * @since       2014-09   
 */
public interface LeaseDAO {
    /**
     * Create lease in database
     * 
     * @param lease lease to be created
     */
    public void createLease(Lease lease);
    
    /**
     * Update an existing lease
     * <p>
     * It pick an exsisting lease by id and update it with the new lease data
     * 
     * @param lease lease with new updated info
     * @param leaseId id of existing lease
     */
    public void updateLease(Lease lease, int leaseId);
    
    /**
     * Delete lease
     * <p>
     * It delete the lease by the given id
     * 
     * @param leaseId id of lease to be deleted
     */
    public void deleteLease(int leaseId);
    
    /**
     * Shows list of leases for person
     * <p>
     * It shows all leases on person has or had in the past. The method
     * uses personId for identification.
     * Person can have more than one lease. Lease can have only one person.
     * 
     * @param personId id of person
     * @return list of leases for person
     */
    public List getLeasesByPerson(Integer personId);
    
    /**
     * Shows all leases of a certain period
     * <p>
     * It shows all leases for all employees from the certain date to the 
     * certain date.
     * 
     * @param from date from the leases should be showed
     * @param until date upon the leases should be showed
     * @return list of leases
     */
    public List getAllLeases(Date from, Date until);    
    
    public Lease getLeaseByID(Integer ID);
    
}