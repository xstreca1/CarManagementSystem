/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.service.Entities;

import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.Lease;
import cz.muni.fi.pa165.persistence.Entities.Person;
import java.util.List;

/**
 *
 * @author jozef.puchly
 */
public interface CarServiceInterface {
    
    public void createCar(Car car);
    
    public Car getCarInfo(Integer carID);
    
    public void returnCar(Integer leaseID);
    
    public List<Car> findAllCars(boolean alsoInactive);
    
    public void retireCar(Car car);
}
