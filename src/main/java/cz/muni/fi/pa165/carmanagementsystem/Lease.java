/*
 * This claas represents a lease that can any employee make.
 * Employee is able to lease a car according to his status.
 * Every employee's travel is recorded (where and when, number of kilometers..)
 * Every lease has date from and to which the lease is planned.
 * Every lease need to be approved first.
 */
package cz.muni.fi.pa165.carmanagementsystem;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Masaryk university project
 * 
 * @author Petr Potucek
 */
@Entity
public class Lease implements Serializable {
    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int leaseId;
    
    @Column(nullable = false)
    private int carMileage;
    
    @Temporal(TemporalType.DATE)
    private Date dateOfLease;
    
    @Temporal(TemporalType.DATE)
    private Date dateOfReturn;
    
    @Column(nullable = false)
    private String vehicleRegPlate;
    
    @Column(nullable = false)
    private Boolean isClosed;
    
    @ManyToOne
    private Car car;
    
    @ManyToOne
    private Person person;
    
    @Enumerated(EnumType.STRING)
    private ReturnedStatus returnedStatus;//TODO
    
    public enum ReturnedStatus {
        BROKEN,
        READY,
        EMPTYFUEL        
    };
    
    @Enumerated(EnumType.STRING)
    private TravelReason travelReason;
    
    public enum TravelReason {
        PERSONAL,
        WORK        
    };


    /**
     * @return the returnedStatus
     */
    public ReturnedStatus getReturnedStatus() {
        return returnedStatus;
    }

    /**
     * @return the travelReason
     */
    public TravelReason getTravelReason() {
        return travelReason;
    }

    /**
     * @param carMileage the carMileage to set
     */
    public void setCarMileage(int carMileage) {
        this.carMileage = carMileage;
    }

    /**
     * @param dateOfLease the dateOfLease to set
     */
    public void setDateOfLease(Date dateOfLease) {
        this.dateOfLease = dateOfLease;
    }

    /**
     * @param dateOfReturn the dateOfReturn to set
     */
    public void setDateOfReturn(Date dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    /**
     * @param vehicleRegPlate the vehicleRegPlate to set
     */
    public void setVehicleRegPlate(String vehicleRegPlate) {
        this.vehicleRegPlate = vehicleRegPlate;
    }

    /**
     * @param isClosed the isClosed to set
     */
    public void setIsClosed(Boolean isClosed) {
        this.isClosed = isClosed;
    }

    /**
     * @param car the car to set
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * @param person the person to set
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * @param returnedStatus the returnedStatus to set
     */
    public void setReturnedStatus(ReturnedStatus returnedStatus) {
        this.returnedStatus = returnedStatus;
    }

    /**
     * @param travelReason the travelReason to set
     */
    public void setTravelReason(TravelReason travelReason) {
        this.travelReason = travelReason;
    }

    /**
     * @return the leaseId
     */
    public int getLeaseId() {
        return leaseId;
    }

    /**
     * @return the carMileage
     */
    public int getCarMileage() {
        return carMileage;
    }

    /**
     * @return the dateOfLease
     */
    public Date getDateOfLease() {
        return dateOfLease;
    }

    /**
     * @return the dateOfReturn
     */
    public Date getDateOfReturn() {
        return dateOfReturn;
    }

    /**
     * @return the vehicleRegPlate
     */
    public String getVehicleRegPlate() {
        return vehicleRegPlate;
    }

    /**
     * @return the isClosed
     */
    public Boolean getIsClosed() {
        return isClosed;
    }

    /**
     * @return the car
     */
    public Car getCar() {
        return car;
    }

    /**
     * @return the person
     */
    public Person getPerson() {
        return person;
    }
    
       

    public int getId() {
        return id;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lease)) {
            return false;
        }
        Lease other = (Lease) object;
        if (this.leaseId != other.leaseId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.muni.fi.pa165.carmanagementsystem.Lease[ id=" + id + " ]";
    }
    
}
