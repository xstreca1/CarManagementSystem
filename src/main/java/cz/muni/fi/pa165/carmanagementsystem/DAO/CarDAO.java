/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carmanagementsystem.DAO;

import cz.muni.fi.pa165.carmanagementsystem.Entities.Car;
import cz.muni.fi.pa165.carmanagementsystem.Entities.Car.bodyStyle;
import java.util.List;

/**
 *
 * @author Jozef Puchly
 */
public interface CarDAO {
    public void createCar(Car car);
    
    public void updateCar(Car car, Integer carID);
    
    public void deleteCar(Integer carID);
    
    public List listAllAvailableCars();
    
    public List getCarByCategory(int Category);
    
    public List getCarBySeats(int seats);
    
    public List getCarByBodyStyle(bodyStyle bs);
    
    
    
}
