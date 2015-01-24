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
import cz.muni.fi.pa165.service.dto.CarDTO;
import cz.muni.fi.pa165.service.service.CarServiceInterface;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    
    @Autowired
    CarServiceInterface carService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String printWelcome(ModelMap model, Principal principal, HttpServletRequest request) {

        String name = principal.getName();
        model.addAttribute("username", name);   
        
        List<CarDTO> cars = carService.findAllCars(true);
        
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

}
