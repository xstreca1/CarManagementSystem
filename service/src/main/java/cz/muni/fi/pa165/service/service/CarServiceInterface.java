/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.Lease;
import cz.muni.fi.pa165.persistence.Entities.Lease.ReturnedStatus;
import cz.muni.fi.pa165.persistence.Entities.Person;
import cz.muni.fi.pa165.service.dto.CarDTO;
import java.util.List;

/**
 *
 * @author jozef.puchly
 */
public interface CarServiceInterface {
    
    public CarDTO createCar(CarDTO carDto);
    
    public void updateCar(CarDTO carDto, Integer CarID);
    
    public List<CarDTO> findAllCars(boolean alsoInactive);
    
    public void deleteCar(CarDTO carDto);
}
