/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.web;

/**
 *
 * @author jrumanov
 */
import cz.muni.fi.pa165.persistence.Entities.Car.Category;
import cz.muni.fi.pa165.persistence.Entities.Person.EmploymentStatus;
import cz.muni.fi.pa165.service.dto.CarDTO;
import cz.muni.fi.pa165.service.dto.PersonDTO;
import cz.muni.fi.pa165.service.service.CarServiceInterface;
import cz.muni.fi.pa165.service.service.PersonServices;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Autowired
    CarServiceInterface carService;
    @Autowired
    PersonServices personService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String printWelcome(ModelMap model, Principal principal, HttpServletRequest request) {

        String name = principal.getName();
        model.addAttribute("username", name);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = user.getUsername();
        System.out.println(userName);
        
        List<CarDTO> cars = new ArrayList<>();       
        
        if(!"admin".equals(userName)){
        PersonDTO loggedUser = personService.getPersonByUsername(userName);
        EmploymentStatus status = loggedUser.getEmploymentStatus();
        String category = null;
        switch(status){
            
            case CEO:  category="A";            
            case MANAGER: category="A";
            case SENIOR: category="A";
            case JUNIOR: category="B";
            case INTERN: category="C";
            case JOZO: category="D";
        }
        
        
        List<CarDTO> allCars = carService.findAllCars(true);
        for (CarDTO car: allCars){
           Category cat = car.getCategory();
        if (category == "A"){cars.add(car);}
        if (category == "B" && cat != Category.A){cars.add(car);}
        if (category == "C" && (cat != Category.A || cat != Category.B)){cars.add(car);}
        if (category == "D" && cat == Category.D){cars.add(car);}
        
        }
        }
        

        request.setAttribute("cars", cars);

        return "index";

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {

        return "loginPage";

    }

    @RequestMapping(value = "/loginError", method = RequestMethod.GET)
    public String loginError(ModelMap model) {
        model.addAttribute("error", "true");
        return "loginPage";

    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model) {
        return "logout";
    }

}
