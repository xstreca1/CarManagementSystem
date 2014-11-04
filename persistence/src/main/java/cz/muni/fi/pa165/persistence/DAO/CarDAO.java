/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.persistence.DAO;

import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.Car.bodyStyle;
import java.util.List;

/**
 *
 * @author Jozef Puchly
 */
public interface CarDAO {
    public Car createCar(Car car);
    
    public void updateCar(Car car, Integer carID);
    
    public void deleteCar(Integer carID);
    
    public List listAllAvailableCars();
    
    public List getCarByCategory(int Category);
    
    public List getCarBySeats(int seats);
    
    public List getCarByBodyStyle(bodyStyle bs);
    
    public Car getCarByID (Integer carID);
    
    public List listAllCars(boolean alsoInactive);
    
}
