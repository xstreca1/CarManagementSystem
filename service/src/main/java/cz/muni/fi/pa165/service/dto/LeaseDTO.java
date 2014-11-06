package cz.muni.fi.pa165.service.dto;

import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.Lease;
import cz.muni.fi.pa165.persistence.Entities.Person;
import java.util.Date;

/**
 *
 * @author Petr Potucek
 */
public class LeaseDTO {
    
    private int leaseId;

   private int distance;

    private Date dateOfLease;

    private Date dateOfReturn;

    private Boolean isClosed;

    private Lease.ReturnedStatus returnedStatus;

    private Lease.TravelReason travelReason;

    private Car car;
    
    private Person person;
    
     public LeaseDTO(Integer leaseId, Lease.TravelReason travelReason,int distance,
            Date dateOfLease,Date dateOfReturn,Car car, Person person, Boolean isClosed,Lease.ReturnedStatus returnedStatus) {

        this.leaseId = leaseId;
        this.travelReason = travelReason;
        this.distance = distance;
        this.dateOfLease = dateOfLease;
        this.dateOfReturn = dateOfReturn;
        this.car = car;
        this.person = person;
        this.isClosed = isClosed;//here?
        this.returnedStatus = returnedStatus;//here?
    }

    public void setLeaseId(int leaseId) {
        this.leaseId = leaseId;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setDateOfLease(Date dateOfLease) {
        this.dateOfLease = dateOfLease;
    }

    public void setDateOfReturn(Date dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public void setIsClosed(Boolean isClosed) {
        this.isClosed = isClosed;
    }

    public void setReturnedStatus(Lease.ReturnedStatus returnedStatus) {
        this.returnedStatus = returnedStatus;
    }

    public void setTravelReason(Lease.TravelReason travelReason) {
        this.travelReason = travelReason;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getLeaseId() {
        return leaseId;
    }

    public int getDistance() {
        return distance;
    }

    public Date getDateOfLease() {
        return dateOfLease;
    }

    public Date getDateOfReturn() {
        return dateOfReturn;
    }

    public Boolean getIsClosed() {
        return isClosed;
    }

    public Lease.ReturnedStatus getReturnedStatus() {
        return returnedStatus;
    }

    public Lease.TravelReason getTravelReason() {
        return travelReason;
    }

    public Car getCar() {
        return car;
    }

    public Person getPerson() {
        return person;
    }
}
