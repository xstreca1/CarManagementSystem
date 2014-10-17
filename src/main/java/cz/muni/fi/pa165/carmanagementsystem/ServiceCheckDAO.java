/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carmanagementsystem;

import java.util.List;

/**
 *
 * @author Martin Strecansky
 */
public interface ServiceCheckDAO {

    /**
     * Method used to insert instance of ServiceCheck class to database
     *
     * @param seviceCheck
     */
    public void createServiceCheck(ServiceCheck seviceCheck);

    /**
     * Method used to get the instance of ServiceCheck class from database
     * according to its ID, update this instance and save this updated
     * serviceCheck to database
     *
     * @param seviceCheck
     */
    public void updateServiceCheck(ServiceCheck seviceCheck);

    /**
     * Method used to delete instance of ServiceCheck class from database
     * according to its ID
     *
     * @param seviceCheck
     */
    public void deleteServiceCheck(ServiceCheck seviceCheck);

    /**
     * Method used to get number of days remaining to next service check
     * according to its ID
     *
     * @param scID
     * @return number of days
     */
    public int getDaysToNext(int scID);

    /**
     * Method used to get all service checks assigned to some car
     *
     * @param car
     * @return List of service checks
     */
    public List getServiceChecksForCar(Car car);

}
