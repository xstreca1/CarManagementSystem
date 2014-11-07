/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.persistence.DAO.CarDAO;
import cz.muni.fi.pa165.persistence.DAO.LeaseDAO;
import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.Lease;
import cz.muni.fi.pa165.persistence.Entities.Lease.ReturnedStatus;
import cz.muni.fi.pa165.persistence.Entities.Person;
import cz.muni.fi.pa165.service.dto.CarDTO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
    @PersistenceContext
    private EntityManager em;
    
    public void createCar(CarDTO carDto) {
		ApplicationContext applicationContext 
                = new ClassPathXmlApplicationContext("/applicationContext.xml");
        
        CarDAO CarDAO = (CarDAO) applicationContext.getBean("carDAO");
        Car carEntity = null;
        
        List<String> list = new ArrayList<String>();
// Add the mapping configuration
        list.add("dozerMapping.xml");
// Add to DozerMapper
        Mapper mapper = new DozerBeanMapper(list);
        
        mapper.map(carDto, carEntity, "car");
        em.getTransaction().begin();
        carDAO.createCar(carEntity);
        em.getTransaction().commit();
    }


    public void updateCar(CarDTO carDto, Integer carID) {
         ApplicationContext applicationContext 
                = new ClassPathXmlApplicationContext("/applicationContext.xml");
        
        CarDAO CarDAO = (CarDAO) applicationContext.getBean("carDAO");
        Car carEntity = null;
        
        List<String> list = new ArrayList<String>();
// Add the mapping configuration
        list.add("dozerMapping.xml");
// Add to DozerMapper
        Mapper mapper = new DozerBeanMapper(list);
        
        mapper.map(carDto, carEntity, "car");
        em.getTransaction().begin();
        carDAO.updateCar(carEntity, carID);
        em.getTransaction().commit();
        
   //     leaseDAO.
    }

    public List<CarDTO> findAllCars(boolean alsoInactive) {
        
        	ApplicationContext applicationContext 
                = new ClassPathXmlApplicationContext("/applicationContext.xml");
        
        CarDAO CarDAO = (CarDAO) applicationContext.getBean("carDAO");
        Car carEntity = null;
        CarDTO carDto = null;
        
        List<String> list = new ArrayList<String>();
        List<Car> ori = new ArrayList<Car>();
        List<CarDTO> n = new ArrayList<CarDTO>();
        

// Add the mapping configuration
        list.add("dozerMapping.xml");
// Add to DozerMapper
        Mapper mapper = new DozerBeanMapper(list);
        for (Car co : ori) {
             n.add(mapper.map(co, CarDTO.class));
        mapper.map(carDto, carEntity, "car");
        em.getTransaction().begin();
        carDAO.listAllCars(alsoInactive);
        em.getTransaction().commit();
       
        }
        return n;
    }

    public void deleteCar(CarDTO carDto) {
        
        ApplicationContext applicationContext 
                = new ClassPathXmlApplicationContext("/applicationContext.xml");
        
        CarDAO CarDAO = (CarDAO) applicationContext.getBean("carDAO");
        Car carEntity = null;
        
        List<String> list = new ArrayList<String>();
// Add the mapping configuration
        list.add("dozerMapping.xml");
// Add to DozerMapper
        Mapper mapper = new DozerBeanMapper(list);
        
        mapper.map(carDto, carEntity, "car");
        em.getTransaction().begin();
        carDAO.deleteCar(carEntity.getCarID());
        em.getTransaction().commit();
        
    }
    
}
