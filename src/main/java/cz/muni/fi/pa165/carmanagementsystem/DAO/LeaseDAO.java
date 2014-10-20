<<<<<<< HEAD:src/main/java/cz/muni/fi/pa165/carmanagementsystem/LeaseDAO.java
package cz.muni.fi.pa165.carmanagementsystem.DAO;

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
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carmanagementsystem.DAO;

/**
 *
 * @author Petr Potucek
 */
public interface LeaseDAO {
    
}
>>>>>>> origin/master:src/main/java/cz/muni/fi/pa165/carmanagementsystem/DAO/LeaseDAO.java
