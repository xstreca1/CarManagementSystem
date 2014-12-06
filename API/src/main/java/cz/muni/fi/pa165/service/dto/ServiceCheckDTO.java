/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service.dto;

import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.Lease;
import cz.muni.fi.pa165.persistence.Entities.ServiceCheck;
import java.util.Date;

/**
 *
 * @author Mato
 */
public class ServiceCheckDTO {
    private Integer scID;
    private ServiceCheck.ServiceCheckName name;

    public Integer getScID() {
        return scID;
    }

    public void setScID(Integer scID) {
        this.scID = scID;
    }
    private int serviceInterval;
    private Date lastCheck;
    private String description;
    private CarDTO car;

    public ServiceCheckDTO(ServiceCheck.ServiceCheckName name, int serviceInterval, Date lastCheck, String description, CarDTO car) {
        this.name = name;
        this.serviceInterval = serviceInterval;
        this.lastCheck = lastCheck;
        this.description = description;
        this.car = car;
    }

    public ServiceCheckDTO() {
        
    }

    public ServiceCheck.ServiceCheckName getName() {
        return name;
    }

    public void setName(ServiceCheck.ServiceCheckName name) {
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

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.scID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ServiceCheckDTO other = (ServiceCheckDTO) obj;
        if (this.scID != other.scID) {
            return false;
        }
        return true;
    }
    
      
    
    
}
