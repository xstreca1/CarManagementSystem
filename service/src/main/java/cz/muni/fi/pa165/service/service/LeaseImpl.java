/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.persistence.DAO.LeaseDAO;
import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.Lease;
import cz.muni.fi.pa165.persistence.Entities.Lease.ReturnedStatus;
import cz.muni.fi.pa165.persistence.Entities.Person;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Petr Potucek
 */
@Service
@Transactional
public class LeaseImpl implements LeaseInterface {
    
    private LeaseDAO leaseDao;
    
    /**
     * setter for lease 
     * 
     * @param leaseDao lease to be setted
     */
    public void setLeaseDao(LeaseDAO leaseDao){
        this.leaseDao = leaseDao;
    }

    public void createLease(Car car, Person person, Lease.TravelReason travelReason, int carMileage, Date dateOfLease, Date dateOfReturn) {
        //creation of lease to persist
        Lease lease = new Lease();
        
        //set parameters to lease before persist
        lease.setCar(car);
        lease.setPerson(person);
        lease.setTravelReason(travelReason);
        lease.setCarMileage(carMileage);
        lease.setDateOfLease(dateOfLease);
        lease.setDateOfReturn(dateOfReturn);
        
        //set if lease is closed
        lease.setIsClosed(Boolean.FALSE);
        
        //persist
        leaseDao.createLease(lease);
    }

    public void setReturnedStatus(int id, ReturnedStatus status) {
        (leaseDao.getLeaseByID(id)).setReturnedStatus(status);
    }

    public List getLeaseByPerson(Person person) {
        List<Lease> leasesForPerson;
        
        return leasesForPerson = leaseDao.getLeasesByPerson(person);      
    }

    public List getAllLeases(Date from, Date to) {
        List<Lease> allLeases;
        
        return allLeases = leaseDao.getAllLeases(from,to);
        
    }

    public Lease getLeaseById(int id) {
        return leaseDao.getLeaseByID(id);
    }

    public void deleteLease(int leaseId) {    
        leaseDao.deleteLease(leaseId);
    }
    
}
