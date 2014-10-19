/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carmanagementsystem;

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
 *
 * @author Petr Potucek
 */
@Entity
public class Lease implements Serializable {

    //--------------------enums-------------------------- 
    private enum ReturnedStatus {

        OK, BROKEN
    };//TODO

    private enum TravelReason {

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
        return leaseId;
    }

    public void setCarMileage(int carMileage) {
        this.carMileage = carMileage;
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

    public void setReturnedStatus(ReturnedStatus returnedStatus) {
        this.returnedStatus = returnedStatus;
    }

    public void setTravelReason(TravelReason travelReason) {
        this.travelReason = travelReason;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
    //----------------constructors-----------------------

    public Lease() {
    }
    
    //-------------mandatory methods---------------------
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) leaseId;
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
        return "cz.muni.fi.pa165.carmanagementsystem.Lease[ id=" + leaseId + " ]";
    }

}
