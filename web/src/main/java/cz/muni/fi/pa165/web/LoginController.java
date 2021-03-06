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
import cz.muni.fi.pa165.service.dto.LeaseDTO;
import cz.muni.fi.pa165.service.dto.PersonDTO;
import cz.muni.fi.pa165.service.service.CarServiceInterface;
import cz.muni.fi.pa165.service.service.LeaseServiceInterface;
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
    @Autowired
    LeaseServiceInterface leaseService;

    // Controller for main page
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String printWelcome(ModelMap model, Principal principal, HttpServletRequest request) {

        String name = principal.getName();
        model.addAttribute("username", name);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = user.getUsername();
        System.out.println(userName);

        List<CarDTO> cars = new ArrayList<>();

       // if logged user is not hardcoded admin, find out availible cars for logged employees status
        if (!"admin".equals(userName)) {
          
            List<CarDTO> allCars = carService.findAllCars(true);
            for (CarDTO car : allCars) {
                Category cat = car.getCategory();
                PersonDTO loggedUser = personService.getPersonByUsername(userName);
                EmploymentStatus status = loggedUser.getEmploymentStatus();
                if (status.equals(EmploymentStatus.CEO) || status.equals(EmploymentStatus.MANAGER) || status.equals(EmploymentStatus.SENIOR)) {
                    cars.add(car);
                }
                if (status.equals(EmploymentStatus.JUNIOR) && !Category.A.equals(cat)) {
                    cars.add(car);
                }
                if (status.equals(EmploymentStatus.INTERN) && (!Category.A.equals(cat) && !Category.B.equals(cat))) {
                    cars.add(car);
                }
                if (status.equals(EmploymentStatus.JOZO) && cat == Category.D) {
                    cars.add(car);
                }

            }
        }

        request.setAttribute("cars", cars);

        return "index";

    }

    // Login page controller
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {

        return "loginPage";

    }

    // Login page controller
    @RequestMapping(value = "/loginError", method = RequestMethod.GET)
    public String loginError(ModelMap model) {
        model.addAttribute("error", "true");
        return "loginPage";

    }

    // Logout controlelr
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model) {
        return "logout";
    }

    // Controller fo displaying travel stats for logged user
    @RequestMapping(value = "/mytravels", method = RequestMethod.GET)
    public String travels(ModelMap model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = user.getUsername();
        PersonDTO person = personService.getPersonByUsername(userName);
        List<LeaseDTO> leases = leaseService.getLeaseByPerson(person);
        model.addAttribute("leases", leases);

        return "personStatistics";
    }

}
