package cz.muni.fi.pa165.carmanagementsystem.Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Claas represents a lease that an employee can make
 * Every lease is identified by an ID
 * Every lease has a car and person assigned, beside that every lease has some
 * mileage employee assumes to drive, returned status, date of expected lease
 * and return, vehicle registration plate, travel reason of lease and isClosed
 * attribute, that specifi if the lease is available
 * 
 * @author      Petr Potucek <scot @ mail.muni.cz>
 * @since       2014-09         
 */
@Entity
public class Lease implements Serializable {

    //--------------------enums-------------------------- 
    public enum ReturnedStatus {

        OK, BROKEN
    };

    public enum TravelReason {

        PERSONAL, WORK
    };

    //-----------------attributes------------------------
    
    private static final long serialVersionUID = 1L;
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
    private Boolean isClosed;
    
    @Column(nullable = false)
    private String vehicleRegPlate;

    @Enumerated(EnumType.STRING)
    private ReturnedStatus returnedStatus;

    @Enumerated(EnumType.STRING)
    private TravelReason travelReason;

    //--------------relationships------------------------
    
    @ManyToOne
    private Car car;

    @ManyToOne
    private Person person;

     //------------getters and setters--------------------
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
     * @return the isClosed
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

    /**
     * @return the id of lease
     */
    public int getId() {
        return leaseId;
    }

    /**
     * Sets a car mileage
     * 
     * @param carMileage expected mileage of car
     */
    public void setCarMileage(int carMileage) {
        this.carMileage = carMileage;
    }

    /**
     * Sets date of expected lease
     * 
     * @param dateOfLease date of lease
     */
    public void setDateOfLease(Date dateOfLease) {
        this.dateOfLease = dateOfLease;
    }
    
    /**
     * Sets date of expecter return of a car
     * 
     * @param dateOfReturn date of return
     */
    public void setDateOfReturn(Date dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    /**
     * Sets whether it is possible to make a lease. False means, it is not.
     * 
     * @param isClosed false or true
     */
    public void setIsClosed(Boolean isClosed) {
        this.isClosed = isClosed;
    }
    
    /**
     * sets vehicle registration plate to identifi a car
     * 
     * @param vehicleRegPlate registration plate of a car
     */
    public void setVehicleRegPlate(String vehicleRegPlate) {
        this.vehicleRegPlate = vehicleRegPlate;
    }

    /**
     * Sets returned status on lease, means status of a car as broken, ok ..
     * 
     * @param returnedStatus status of returned car
     */
    public void setReturnedStatus(ReturnedStatus returnedStatus) {
        this.returnedStatus = returnedStatus;
    }

    /**
     * Sets a reason of lease. It can be personal or work.
     * 
     * @param travelReason reason of lease
     */
    public void setTravelReason(TravelReason travelReason) {
        this.travelReason = travelReason;
    }

    /**
     * Sets a car to a lease
     * 
     * @param car car which is assigned to the lease
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * Sets person to a lease
     * 
     * @param person person to be assigned to lease
     */
    public void setPerson(Person person) {
        this.person = person;
    }
    
    //----------------constructors-----------------------

    public Lease() {
    }
    
    //-------------mandatory methods---------------------
    @Override
    public int hashCode() {
        return (int)this.leaseId *
                carMileage *
                vehicleRegPlate.hashCode() *
                car.hashCode() *
                person.hashCode();
    }
    
    @Override
    public boolean equals(Object object) {
        if(object == null)                  return false;
        if (!(object instanceof Lease))     return false;
        
        Lease other = (Lease) object;
        if(this.leaseId != other.leaseId)                       return false;
        if(! this.vehicleRegPlate.equals(other.vehicleRegPlate))return false;
        if(! this.person.equals(other.person))                  return false;
        if(! this.car.equals(other.car))                        return false;

        return true;
    }
    
    @Override
    public String toString() {
        return "Lease id: " + leaseId + "   Assigned to: " + person.toString() + "   with car: " + car.toString() +
                "\n   date of lease: " + dateOfLease.toString() + "  date of return: " + dateOfReturn.toString() +
                "is approved: " + isClosed.toString();
    }

}
