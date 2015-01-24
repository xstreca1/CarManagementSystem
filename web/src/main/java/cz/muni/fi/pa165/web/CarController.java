/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.web;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.Lease;
import cz.muni.fi.pa165.service.dto.CarDTO;
import cz.muni.fi.pa165.service.dto.LeaseDTO;
import cz.muni.fi.pa165.service.dto.PersonDTO;
import cz.muni.fi.pa165.service.dto.ServiceCheckDTO;
import cz.muni.fi.pa165.service.service.CarServiceInterface;
import cz.muni.fi.pa165.service.service.LeaseServiceInterface;
import cz.muni.fi.pa165.service.service.PersonServices;
import cz.muni.fi.pa165.service.service.ServiceCheckInterface;
import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Machine controller implementation
 *
 * @author Jozef Puchly
 *
 */
@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarServiceInterface carService;

    @Autowired
    LeaseServiceInterface leaseService;

    @Autowired
    ServiceCheckInterface serviceCheckService;

    @Autowired
    PersonServices personService;

    @Autowired
    public CarController(CarServiceInterface carService,
            ServiceCheckInterface serviceCheckService,
            LeaseServiceInterface leaseService,
            PersonServices personService) {
        this.carService = carService;
        this.leaseService = leaseService;
        this.serviceCheckService = serviceCheckService;
        this.personService = personService;
    }

    @ModelAttribute("car")
    public CarDTO getCar() {
        CarDTO car = new CarDTO();

        return car;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String carsHome(HttpServletRequest request) {
        List<CarDTO> cars = carService.findAllCars(true);
        request.setAttribute("cars", cars);

        return "carListCars";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCar(@ModelAttribute("car") @Valid CarDTO car,
            BindingResult result, ModelMap model, HttpServletRequest request) {

        if (result.hasErrors()) {

            List<CarDTO> cars = carService.findAllCars(true);
            request.setAttribute("cars", cars);

            return "carListCars";

        } else {

            carService.createCar(car);
            return "redirect:/car/";
        }
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/delete/{id}")
    public String deleteCar(@PathVariable Integer id, ModelMap model) {

        CarDTO car1 = carService.getCarByID(id);
        model.addAttribute("car1", car1);
        car1.setIsActive(false);
        carService.updateCar(car1, id);

        return "redirect:/car/";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update_form(@PathVariable Integer id, HttpServletRequest request) {

        List<CarDTO> cars = new ArrayList();
        cars.add(carService.getCarByID(id));
        request.setAttribute("cars", cars);
        //model.addAttribute("person2", person2);
        return "carEdit";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editCar(@PathVariable Integer id, @ModelAttribute("car") @Valid CarDTO car,
            BindingResult result, ModelMap model, HttpServletRequest request) {

        carService.updateCar(car, id);
        return "redirect:/car/";

    }

    @RequestMapping(value = "/lease/{id}", method = RequestMethod.GET)
    public String leaseCar(@PathVariable Integer id, ModelMap model) {

        List<CarDTO> cars = new ArrayList();
        cars.add(carService.getCarByID(id));
        model.addAttribute("cars", cars);

        // get people from DB a nd save it to model "people" to be able to choose in form from them
        List<PersonDTO> people = personService.findAllPeople(true);
        model.addAttribute("people", people);

        // LeaseDTO lease = new LeaseDTO();
        // leaseService.createLease(lease);
        model.addAttribute("lease", new LeaseDTO());
        //model.addAttribute("person2", person2);

        return "leaseCar";
    }

    @RequestMapping(value = "/confirmLease/{id}", method = RequestMethod.POST)
    public String confirmLeaseCar(@PathVariable Integer id, @Validated @ModelAttribute("lease") LeaseDTO lease,
            ModelMap model, HttpServletRequest request) {

        String url = null;
        model.addAttribute("lease", lease);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getUsername();
        PersonDTO person = personService.getPersonByUsername(name);        
        CarDTO car = carService.getCarByID(id);
        lease.setCar(car);
        lease.setPerson(person);
        car.setAvailibility(false);
        carService.updateCar(car, id);

        leaseService.createLease(lease);

        if (request.isUserInRole("ROLE_ADMIN")) {
            url = "redirect:/lease/";
        } else {
            url = "redirect:/main/";
        }

        return url;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        sdf.setLenient(true);
        sdf.getInstance();
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/check/{id}", method = RequestMethod.GET)
    public String addCheck(@PathVariable Integer id, ModelMap model) {

        List<CarDTO> cars = new ArrayList();
        cars.add(carService.getCarByID(id));
        model.addAttribute("cars", cars);

        model.addAttribute("check", new ServiceCheckDTO());

        return "scAssignCheck";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/confirmCheck/{id}", method = RequestMethod.POST)
    public String confirmCheck(@PathVariable Integer id, @ModelAttribute("check") ServiceCheckDTO check,
            ModelMap model) {

        model.addAttribute("check", check);

        CarDTO car = carService.getCarByID(id);

        check.setCar(car);
        Date lastCheck = check.getLastCheck();
        int interval = check.getServiceInterval();

        Calendar nextControl = Calendar.getInstance();
        nextControl.setTime(lastCheck);
        nextControl.add(Calendar.MONTH, interval);

        Date addedMonths = nextControl.getTime();

        check.setNextCheck(addedMonths);

        serviceCheckService.createServiceCheck(check);

        return "redirect:/serviceCheck/";
    }

    @RequestMapping(value = "/showSC/{id}", method = RequestMethod.GET)
    public String showSC(HttpServletRequest request, @PathVariable Integer id) {

        CarDTO car = carService.getCarByID(id);

        List<ServiceCheckDTO> checks = serviceCheckService.getServiceChecksForCar(car);

        request.setAttribute("checks", checks);

        return "scDisplayChecks";
    }

    @InitBinder
    protected void initBinder2(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(PersonDTO.class, "person", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                PersonDTO person = personService.getPersonByID(Integer.parseInt(text));
                setValue(person);
            }
        });
    }

}
