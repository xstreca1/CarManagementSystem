/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carmanagementsystem;

import cz.muni.fi.pa165.carmanagementsystem.Car.bodyStyle;
import java.util.List;

/**
 *
 * @author Jozef Puchly
 */
public interface CarDAO {
    public void createCar(Car car);
    
    public void updateCar(Car car, String vehicleRegPlate);
    
    public void deleteCar(String vehicleRegPlate);
    
    public List listAllAvailableCars();
    
    public List getCarByCategory(int Category);
    
    public List getCarBySeats(int seats);
    
    public List getCarByBodyStyle(bodyStyle bs);
    
    
    
}
