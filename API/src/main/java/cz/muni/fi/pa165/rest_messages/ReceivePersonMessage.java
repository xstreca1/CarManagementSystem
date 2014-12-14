/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.rest_messages;

import cz.muni.fi.pa165.service.dto.PersonDTO;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.object);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReceivePersonMessage other = (ReceivePersonMessage) obj;
        if (!Objects.equals(this.object, other.object)) {
            return false;
        }
        return true;
    }
    
    
    
}
