/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.persistence.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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
 * @author Martin Strecansky
 */
@Entity
public class ServiceCheck implements Serializable {

    public void createServiceCheck(ServiceCheck serviceCheck) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //--------------------enums--------------------------
    public enum ServiceCheckName {

        EMISSION, 
        TECHNICAL, 
        OVERALL
    }

    //-----------------attributes------------------------
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer scID;

    @Enumerated(EnumType.STRING)
    private ServiceCheckName name;

    @Column(nullable = false)
    private int serviceInterval;

    @Temporal(TemporalType.DATE)
    private Date lastCheck;
    
    @Temporal(TemporalType.DATE)
    private Date nextCheck;

    @Column(nullable = false)
    private String description;

    //--------------relationships------------------------
    @ManyToOne
    private Car car;

    //------------getters and setters--------------------
    public Integer getScID() {
        return scID;
    }

    public ServiceCheckName getName() {
        return name;
    }

    public void setName(ServiceCheckName name) {
        this.name = name;
    }

    public int getServiceInterval() {
        return serviceInterval;
    }

    public void setServiceInterval(int serviceInterval) {
        this.serviceInterval = serviceInterval;
    }

    public Date getLastCheck() {
        return lastCheck;
    }

    public void setLastCheck(Date lastCheck) {
        this.lastCheck = lastCheck;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setScID(Integer scID) {
        this.scID = scID;
    }

    public Date getNextCheck() {
        return nextCheck;
    }

    public void setNextCheck(Date nextCheck) {
        this.nextCheck = nextCheck;
    }
    

    //----------------constructors-----------------------
    public ServiceCheck() {
    }

    //-------------mandatory methods---------------------  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (Integer) scID;
        return hash;
    }

    @Override
     public boolean equals(Object object) {
        if (!(object instanceof ServiceCheck)) {
            return false;
        }
        ServiceCheck other = (ServiceCheck) object;
        if ((this.scID == null && other.scID != null)
                || (this.scID != null
                && !this.scID.equals(other.scID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.muni.fi.pa165.carmanagementsystem.ServiceCheck[ id=" + scID + " ]";
    }

}
