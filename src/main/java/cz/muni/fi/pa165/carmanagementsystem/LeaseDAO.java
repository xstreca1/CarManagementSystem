package cz.muni.fi.pa165.carmanagementsystem;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Petr Potucek
 */
public interface LeaseDAO {
    
    public void createLease();
    public void updateLease(int leaseId);
    public void deleteLease(int leaseId);
    public List getLeasesByPerson(String personId);
    public List getAllLeases(Date from, Date until);    
    
}
