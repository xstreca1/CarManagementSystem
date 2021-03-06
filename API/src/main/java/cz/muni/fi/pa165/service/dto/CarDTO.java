/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service.dto;

import cz.muni.fi.pa165.persistence.Entities.Car;

/**
 *
 * @author jozef.puchly
 */
public class CarDTO {

    private Integer carID;

    private boolean availibility;

    private Boolean isActive;

    private String vehicleRegPlate;

    private Integer yearOfManufacture;

    private Integer mileage;
   
    private String brand;
  
    private String typeName;

    private Car.Color color;

    private Car.bodyStyle bodystyle;

    private Car.Category category;

    private String VIN;

    private Car.emissionStandard emissionstandard;

    private Integer numberOfSeats;

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getVehicleRegPlate() {
        return vehicleRegPlate;
    }

    public void setVehicleRegPlate(String vehicleRegPlate) {
        this.vehicleRegPlate = vehicleRegPlate;
    }

    public Integer getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(Integer yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
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

    public Car.Color getColor() {
        return color;
    }

    public void setColor(Car.Color color) {
        this.color = color;
    }

    public Car.bodyStyle getBodystyle() {
        return bodystyle;
    }

    public void setBodystyle(Car.bodyStyle bodystyle) {
        this.bodystyle = bodystyle;
    }  

    public Car.Category getCategory() {
        return category;
    }

    public void setCategory(Car.Category category) {
        this.category = category;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public Car.emissionStandard getEmissionstandard() {
        return emissionstandard;
    }

    public void setEmissionstandard(Car.emissionStandard emissionstandard) {
        this.emissionstandard = emissionstandard;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Integer getCarID() {
        return carID;
    }

    public void setCarID(Integer carID) {
        this.carID = carID;
    }

    @Override
    public String toString() {
        return carID + ", " + vehicleRegPlate + ", " + brand + ", " + typeName + 
                ", " + mileage + ", " + color + ", " + numberOfSeats;
    }

    public boolean getAvailibility() {
        return availibility;
    }

    public void setAvailibility(boolean availibility) {
        this.availibility = availibility;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.isActive != null ? this.isActive.hashCode() : 0);
        hash = 17 * hash + (this.vehicleRegPlate != null ? this.vehicleRegPlate.hashCode() : 0);
        hash = 17 * hash + (this.yearOfManufacture != null? this.yearOfManufacture.hashCode() : 0);
        hash = 17 * hash + (this.mileage != null ? this.mileage.hashCode() : 0);
        hash = 17 * hash + (this.brand != null ? this.brand.hashCode() : 0);
        hash = 17 * hash + (this.typeName != null ? this.typeName.hashCode() : 0);
        hash = 17 * hash + (this.color != null ? this.color.hashCode() : 0);
        hash = 17 * hash + (this.bodystyle != null ? this.bodystyle.hashCode() : 0);       
        hash = 17 * hash + (this.category != null ? this.category.hashCode() : 0);
        hash = 17 * hash + (this.VIN != null ? this.VIN.hashCode() : 0);
        hash = 17 * hash + (this.emissionstandard != null ? this.emissionstandard.hashCode() : 0);
        hash = 17 * hash + (this.numberOfSeats != null ? this.numberOfSeats.hashCode() : 0);
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
        final CarDTO other = (CarDTO) obj;
        if (this.isActive != other.isActive && (this.isActive == null || !this.isActive.equals(other.isActive))) {
            return false;
        }
        if ((this.vehicleRegPlate == null) ? (other.vehicleRegPlate != null) : !this.vehicleRegPlate.equals(other.vehicleRegPlate)) {
            return false;
        }
        if (this.yearOfManufacture != other.yearOfManufacture) {
            return false;
        }
        if (this.mileage != other.mileage) {
            return false;
        }
        if ((this.brand == null) ? (other.brand != null) : !this.brand.equals(other.brand)) {
            return false;
        }
        if ((this.typeName == null) ? (other.typeName != null) : !this.typeName.equals(other.typeName)) {
            return false;
        }
        if (this.color != other.color) {
            return false;
        }
        if (this.bodystyle != other.bodystyle) {
            return false;
        }      
        if (this.category != other.category) {
            return false;
        }
        if ((this.VIN == null) ? (other.VIN != null) : !this.VIN.equals(other.VIN)) {
            return false;
        }
        if (this.emissionstandard != other.emissionstandard) {
            return false;
        }
        if (this.numberOfSeats != other.numberOfSeats) {
            return false;
        }
        return true;
    }

}
