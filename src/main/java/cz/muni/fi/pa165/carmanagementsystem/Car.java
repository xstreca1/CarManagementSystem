/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carmanagementsystem;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author jrumanov
 */
@Entity
public class Car implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String vehicleRegPlate;
    private int yearOfManufacture;
    private int mileage;
    private String brand;
    private String typeName;
    private enum color {
        BLACK,
        WHITE,
        BLUE,
        GREEN
    };
    private enum bodyStyle{
        SEDAN,
        HATCHBACK,
        CABRIOLET,
        SUV
        
    };
    private int enginePower;
    private float gasConsumption;
    private boolean transmission;
    private enum category {
        A,
        B,
        C,
        D
    };
    private String VIN;
    private enum emissionStandard {
        EU3,
        EU4,
        EU5,
        EU6
        
    };
    private int numberOfSeats;

    public String getVehicleRegPlate() {
        return vehicleRegPlate;
    }

    public void setVehicleRegPlate(String vehicleRegPlate) {
        this.vehicleRegPlate = vehicleRegPlate;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(int enginePower) {
        this.enginePower = enginePower;
    }

    public float getGasConsumption() {
        return gasConsumption;
    }

    public void setGasConsumption(float gasConsumption) {
        this.gasConsumption = gasConsumption;
    }

    public boolean isTransmission() {
        return transmission;
    }

    public void setTransmission(boolean transmission) {
        this.transmission = transmission;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public float getEngineDisplacement() {
        return engineDisplacement;
    }

    public void setEngineDisplacement(float engineDisplacement) {
        this.engineDisplacement = engineDisplacement;
    }

    public boolean isAvailibility() {
        return availibility;
    }

    public void setAvailibility(boolean availibility) {
        this.availibility = availibility;
    }
    private float engineDisplacement;
    private boolean availibility;

    public String getId() {
        return id;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.muni.fi.pa165.carmanagementsystem.Car[ id=" + id + " ]";
    }
    
}
