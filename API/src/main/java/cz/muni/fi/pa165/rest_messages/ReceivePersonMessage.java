/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.rest_messages;

import cz.muni.fi.pa165.service.dto.PersonDTO;

/**
 *
 * @author jozefpuchly
 */
public class ReceivePersonMessage {
    
    String message;
    
    PersonDTO object;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public PersonDTO getObject()
    {
        return object;
    }
    
    public void setObject(PersonDTO object)
    {
        this.object = object;
    }
    
}
