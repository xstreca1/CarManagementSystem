/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.persistence.Entities;

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
    private Integer carID;

    @Column(nullable = false)
    private Boolean isActive;

    @Column
    private boolean availibility;

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

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column //(nullable = false)
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
    public Integer getCarID() {
        return carID;
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
    
    public boolean getAvailibility() {
        return availibility;
    }

    public void setAvailibility(boolean availibility) {
        this.availibility = availibility;
    }

    public Integer getId() {
        return carID;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public bodyStyle getBodystyle() {
        return bodystyle;
    }

    public void setBodystyle(bodyStyle bodystyle) {
        this.bodystyle = bodystyle;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public emissionStandard getEmissionstandard() {
        return emissionstandard;
    }

    public void setEmissionstandard(emissionStandard emissionstandard) {
        this.emissionstandard = emissionstandard;
    }

    public List<Lease> getLease() {
        return lease;
    }

    public void setLease(List<Lease> lease) {
        this.lease = lease;
    }

    public List<ServiceCheck> getServiceCheck() {
        return serviceCheck;
    }

    public void setServiceCheck(List<ServiceCheck> serviceCheck) {
        this.serviceCheck = serviceCheck;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public void setCarID(Integer carID) {
        this.carID = carID;
    }

    //----------------------constructors---------------------
    public Car() {

    }

    //-------------mandatory methods---------------------
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vehicleRegPlate != null ? vehicleRegPlate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        if ((this.vehicleRegPlate == null && other.vehicleRegPlate != null)
                || (this.vehicleRegPlate != null
                && !this.vehicleRegPlate.equals(other.vehicleRegPlate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.muni.fi.pa165.carmanagementsystem.Car[ id=" + carID + " ]";
    }

}
