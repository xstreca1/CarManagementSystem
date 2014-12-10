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
    
    private Integer leaseId;

   private int distance;

    private Date dateOfLease;

    private Date dateOfReturn;

    private Boolean isClosed;

    private Lease.ReturnedStatus returnedStatus;

    private Lease.TravelReason travelReason;
    
     public LeaseDTO() {

    }

    public void setLeaseId(Integer leaseId) {
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

    public Integer getLeaseId() {
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
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.leaseId;
        hash = 17 * hash + (this.dateOfLease != null ? this.dateOfLease.hashCode() : 0);
        hash = 17 * hash + (this.dateOfReturn != null ? this.dateOfReturn.hashCode() : 0);
        hash = 17 * hash + this.distance;
        hash = 17 * hash + (this.returnedStatus != null ? this.returnedStatus.hashCode() : 0);
        hash = 17 * hash + (this.travelReason != null ? this.travelReason.hashCode() : 0);
        hash = 17 * hash + (this.isClosed != null ? this.isClosed.hashCode() : 0);
        
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (getClass() != object.getClass()) {
            return false;
        }

        LeaseDTO other = (LeaseDTO) object;
        if (this.leaseId != other.leaseId) {
            return false;
        }
        if (!this.dateOfLease.equals(other.dateOfLease)) {
            return false;
        }
        if (!this.dateOfReturn.equals(other.dateOfReturn)) {
            return false;
        }
        if (this.distance != other.distance) {
            return false;
        }
        if (!this.returnedStatus.equals(other.returnedStatus)) {
            return false;
        }
        if (!this.travelReason.equals(other.travelReason)) {
            return false;
        }
        if (this.isClosed != other.isClosed) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        return "Lease id: " + leaseId + "   Assigned to: " 
                + "\n   date of lease: " + dateOfLease.toString() 
                + "  date of return: " + dateOfReturn.toString()
                + "is approved: " + isClosed.toString();
    }

}
