/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.rest_messages;

import cz.muni.fi.pa165.service.dto.CarDTO;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author jozefpuchly
 */
public class ReceiveActiveCarListMessage implements Serializable{
    
    
    String message;
    
    List<CarDTO> list;


    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public List<CarDTO> getList()
    {
        return list;
    }

    public void setList(List<CarDTO> list)
    {
        this.list = list;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.list);
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
        final ReceiveActiveCarListMessage other = (ReceiveActiveCarListMessage) obj;
        if (!Objects.equals(this.list, other.list)) {
            return false;
        }
        return true;
    }

    
    
}
