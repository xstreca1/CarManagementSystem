/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.web;

import cz.muni.fi.pa165.persistence.Entities.ServiceCheck;
import cz.muni.fi.pa165.service.dto.ServiceCheckDTO;
import java.util.Comparator;

/**
 *
 * @author Mato
 */
public class DateComparator implements Comparator<ServiceCheckDTO> {
    @Override
    public int compare(ServiceCheckDTO s1, ServiceCheckDTO s2) {
        return s1.getNextCheck().compareTo(s2.getNextCheck());
    }
}
    

