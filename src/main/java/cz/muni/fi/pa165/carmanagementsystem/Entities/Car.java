/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carmanagementsystem.Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Jozef Puchly
 */
@Entity
public class Car implements Serializable {

    //--------------------enums--------------------------
    public enum Color {

        BLACK,
        WHITE,
        BLUE,
        GREEN
    };

    public enum bodyStyle {

        SEDAN,
        HATCHBACK,
        CABRIOLET,
        SUV

    };

    public enum Category {

        A,
        B,
        C,
        D
    };

    public enum emissionStandard {

        EU3,
        EU4,
        EU5,
        EU6

    };
    
    //-----------------attributes------------------------
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer CarID;

    @Column(nullable = false)
    private String vehicleRegPlate;

    //should be year not int
    @Column(nullable = false)
    private int yearOfManufacture;

    @Column(nullable = false)
    private int mileage;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String typeName;

    @Enumerated(EnumType.STRING)
    private Color color;

    @Enumerated(EnumType.STRING)
    private bodyStyle bodystyle;

    @Column(nullable = false)
    private int enginePower;

    @Column(nullable = false)
    private float gasConsumption;

    @Column(nullable = false)
    private boolean transmission;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    private String VIN;

    @Enumerated(EnumType.STRING)
    private emissionStandard emissionstandard;

    @Column(nullable = false)
    private int numberOfSeats;

    //--------------relationships------------------------
    
    @OneToMany(mappedBy = "car")
    private List<Lease> lease;
    
    @OneToMany(mappedBy = "car")
    private List<ServiceCheck> serviceCheck;
    
    //------------getters and setters--------------------
    
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

    public Integer getId() {
        return CarID;
    }

    
    //----------------------constructors---------------------
    
    public Car() {

    }

    //-------------mandatory methods---------------------


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (CarID != null ? CarID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        if ((this.CarID == null && other.CarID != null) || (this.CarID != null && !this.CarID.equals(other.CarID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.muni.fi.pa165.carmanagementsystem.Car[ id=" + CarID + " ]";
    }

}
