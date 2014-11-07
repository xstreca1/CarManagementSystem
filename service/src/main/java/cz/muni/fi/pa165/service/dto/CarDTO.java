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

    private Boolean isActive;

    private String vehicleRegPlate;

    private int yearOfManufacture;

    private int mileage;

    private String brand;

    private String typeName;

    private Car.Color color;

    private Car.bodyStyle bodystyle;

    private int enginePower;

    private float gasConsumption;

    private boolean transmission;

    private Car.Category category;

    private String VIN;

    private Car.emissionStandard emissionstandard;

    private int numberOfSeats;

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

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Integer getCarID() {
        return carID;
    }

    public void setCarID(Integer carID) {
        this.carID = carID;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.isActive != null ? this.isActive.hashCode() : 0);
        hash = 17 * hash + (this.vehicleRegPlate != null ? this.vehicleRegPlate.hashCode() : 0);
        hash = 17 * hash + this.yearOfManufacture;
        hash = 17 * hash + this.mileage;
        hash = 17 * hash + (this.brand != null ? this.brand.hashCode() : 0);
        hash = 17 * hash + (this.typeName != null ? this.typeName.hashCode() : 0);
        hash = 17 * hash + (this.color != null ? this.color.hashCode() : 0);
        hash = 17 * hash + (this.bodystyle != null ? this.bodystyle.hashCode() : 0);
        hash = 17 * hash + this.enginePower;
        hash = 17 * hash + Float.floatToIntBits(this.gasConsumption);
        hash = 17 * hash + (this.transmission ? 1 : 0);
        hash = 17 * hash + (this.category != null ? this.category.hashCode() : 0);
        hash = 17 * hash + (this.VIN != null ? this.VIN.hashCode() : 0);
        hash = 17 * hash + (this.emissionstandard != null ? this.emissionstandard.hashCode() : 0);
        hash = 17 * hash + this.numberOfSeats;
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
        if (this.enginePower != other.enginePower) {
            return false;
        }
        if (Float.floatToIntBits(this.gasConsumption) != Float.floatToIntBits(other.gasConsumption)) {
            return false;
        }
        if (this.transmission != other.transmission) {
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
