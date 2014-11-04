/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.service.Entities;

import cz.muni.fi.pa165.persistence.DAO.CarDAO;
import cz.muni.fi.pa165.persistence.DAO.LeaseDAO;
import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.Lease;
import cz.muni.fi.pa165.persistence.Entities.Person;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service("carService")
@Transactional
/**
 *
 * @author jozef.puchly
 */
public class CarImpl implements CarServiceInterface {
    
    private CarDAO carDAO;
    private LeaseDAO leaseDAO;
    
    public void createCar(Car car) {
		carDAO.createCar(car);
    }

    public Car getCarInfo(Integer carID) {
        Car car = new Car();
        car = carDAO.getCarByID(carID);
        return car;
    }

    public void returnCar(Integer leaseID) {
        Lease lease = new Lease();
        lease = leaseDAO.getLeaseByID(leaseID);
        
   //     leaseDAO.
    }

    public List<Car> findAllCars(boolean alsoInactive) {
        
        List<Car> cars = carDAO.listAllCars(alsoInactive);
        
        return cars;
       
    }

    public void retireCar(Car car) {
        
    }
    
}