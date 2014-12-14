/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.rest;

import cz.muni.fi.pa165.rest_messages.ReceiveActiveCarListMessage;
import cz.muni.fi.pa165.rest_messages.ReceiveCarMessage;
import cz.muni.fi.pa165.service.dto.CarDTO;
import cz.muni.fi.pa165.service.service.CarServiceInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jozefpuchly
 */
@RestController
@RequestMapping("/rest/car")
public class CarRestController {
   
   @Autowired
   CarServiceInterface service; 
   
    @RequestMapping("/get")
    public ReceiveCarMessage getCarById(@RequestParam(required = true) int id)
    {
        CarDTO car;
        ReceiveCarMessage result = new ReceiveCarMessage();
        
        try
        {
            car = service.getCarByID(id);
            result.setObject(car);
        }
        catch(Exception ex)
        {
            result.setMessage(ex.getMessage());
            result.setObject(null);
        }
        
        return result;
    }
    
    @RequestMapping("/getAll")
    public ReceiveActiveCarListMessage getAllActiveCars()
    {
        ReceiveActiveCarListMessage result = new ReceiveActiveCarListMessage();
        
        try
        {
            List<CarDTO> list = service.findAllCars(true);
            result.setList(list);
        }
        catch(Exception ex)
        {
            result.setMessage(ex.getMessage());
            result.setList(null);
        }
        
        return result;
    }
    
    @RequestMapping("/update")
    public ReceiveCarMessage updateCar(@RequestBody CarDTO car)
    {
      
        ReceiveCarMessage result = new ReceiveCarMessage();
        
        try
        {
            Integer id = car.getCarID();
            service.updateCar(car, id);
            result.setObject(car);
            
        }
        catch(Exception ex)
        {
            result.setMessage(ex.getMessage());
            result.setObject(null);
        }
        
        return result;
    }
    
    @RequestMapping("/delete")
    public ReceiveCarMessage deleteCar(@RequestBody CarDTO car)
    {
        ReceiveCarMessage result = new ReceiveCarMessage();
        
        try
        {
            service.deleteCar(car);
            result.setObject(car);
        }
        catch(Exception ex)
        {
            result.setMessage(ex.getMessage());
            result.setObject(null);
        }
        
        return result;
    }
    
    @RequestMapping("/add")
    public ReceiveCarMessage addCar(@RequestBody CarDTO car)
    {
        ReceiveCarMessage result = new ReceiveCarMessage();
        
        try
        {
            CarDTO newCar = service.createCar(car);
            result.setObject(newCar);
        }
        catch(Exception ex)
        {
            result.setMessage(ex.getMessage());
            result.setObject(null);
        }
        
        return result;
    }
    
}
