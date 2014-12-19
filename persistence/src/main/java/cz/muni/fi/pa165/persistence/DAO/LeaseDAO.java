package cz.muni.fi.pa165.persistence.DAO;

import cz.muni.fi.pa165.persistence.Entities.Lease;
import cz.muni.fi.pa165.persistence.Entities.Person;
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
    public Lease createLease(Lease lease);
    
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
     * Shows list of leases for person
     * <p>
     * It shows all leases on person has or had in the past. The method
     * uses personId for identification.
     * Person can have more than one lease. Lease can have only one person.
     * 
     * @param personId id of person
     * @return list of leases for person
     */
    public List getLeasesByPerson(Person person);
    
    
    
    public Lease getLeaseByID(Integer ID);
    
    List<Lease> findAllLeases(); 
    
}