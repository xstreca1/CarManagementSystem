package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.persistence.DAO.CarDAO;
import cz.muni.fi.pa165.persistence.DAO.LeaseDAO;
import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.service.dto.CarDTO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jozef.puchly
 */

@Service("carService")
@Transactional //to handle transactions
public class CarImpl implements CarServiceInterface {
   
    @Autowired
    private CarDAO carDAO;
    
    @Autowired
    private LeaseDAO leaseDAO;
    
    @PersistenceContext
    private EntityManager em;
    
    public void setDAO (CarDAO carDao) {
        this.carDAO = carDao;
    }
    
    public CarDTO createCar(CarDTO carDto) {
        
        
        Car carEntity = new Car();
        
        List<String> list = new ArrayList<String>();
// Add the mapping configuration
        list.add("dozerMapping.xml");
// Add to DozerMapper
        Mapper mapper = new DozerBeanMapper(list);
        
        mapper.map(carDto, carEntity, "car");
        carDAO.createCar(carEntity);
        
        return carDto;
    }


    public void updateCar(CarDTO carDto, Integer carID) {
        
        Car carEntity = new Car();
        
        List<String> list = new ArrayList<String>();
// Add the mapping configuration
        list.add("dozerMapping.xml");
// Add to DozerMapper
        Mapper mapper = new DozerBeanMapper(list);
        
        mapper.map(carDto, carEntity, "car");
        carDAO.updateCar(carEntity, carID);

    }

    public List<CarDTO> findAllCars(boolean alsoInactive) {
        
        Car carEntity = new Car();
        CarDTO carDto = new CarDTO();
        
        List<String> list = new ArrayList<String>();
        List<CarDTO> n = new ArrayList<CarDTO>();
        

// Add the mapping configuration
        list.add("dozerMapping.xml");
// Add to DozerMapper
        Mapper mapper = new DozerBeanMapper(list);
        
        List<Car> ori = carDAO.listAllCars(alsoInactive);
        
        for (Car co : ori) {
            carDto = mapper.map(co, CarDTO.class);
            n.add(carDto);
       
        }
        return n;
    }

    /*public void deleteCar(CarDTO carDto) {
        
        Car carEntity = new Car();
        
        List<String> list = new ArrayList<String>();
// Add the mapping configuration
        list.add("dozerMapping.xml");
// Add to DozerMapper
        Mapper mapper = new DozerBeanMapper(list);
        
        mapper.map(carDto, carEntity, "car");
        carDAO.deleteCar(carDto.getCarID());
     
    }*/
    
    public CarDTO getCarByID(Integer id) {
        
        Car carEntity = new Car();
        CarDTO carDto = new CarDTO();
        
        List<String> list = new ArrayList<String>();
        
        list.add("dozerMapping.xml");
        
        carEntity = carDAO.getCarByID(id);
        
        Mapper mapper = new DozerBeanMapper(list);
        
        mapper.map(carEntity, carDto, "car");
        
        return carDto;
    }
    
}