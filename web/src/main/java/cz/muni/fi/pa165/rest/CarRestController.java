/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.rest;

import cz.muni.fi.pa165.service.service.CarServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
    
}
