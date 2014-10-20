package cz.muni.fi.pa165.carmanagementsystem.DAO;

import cz.muni.fi.pa165.carmanagementsystem.Entities.Lease;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Petr Potucek
 */
public interface LeaseDAO {
    
    public void createLease(Lease lease);
    public void updateLease(Lease lease, int leaseId);
    public void deleteLease(int leaseId);
    public List getLeasesByPerson(String personId);
    public List getAllLeases(Date from, Date until);    
    
}