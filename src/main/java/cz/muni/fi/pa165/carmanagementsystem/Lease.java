/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carmanagementsystem;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Petr Potucek
 */
@Entity
public class Lease implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int leaseId;
    private int carMileage;
    private Date dateOfLease;
    private Date dateOfReturn;
    private String vehicleRegPlate;
    private Boolean isClosed;
    
    private Car car;
    private Person person;
    
    private enum ReturnedStatus{OK, BROKEN};//TODO
    private enum TravelReason{PERSONAL, WORK};

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

    public void setId(int id) {
        this.id = id;
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
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.muni.fi.pa165.carmanagementsystem.Lease[ id=" + id + " ]";
    }
    
}
