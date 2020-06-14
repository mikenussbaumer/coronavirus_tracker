package com.github.mikenussbaumer.coronavirus_tracker.databasemanager.interfaces;

import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.entity.CoronaData;

import java.util.Date;
import java.util.List;

/**
 * @author Mike Nußbaumer (dev@mike-nussbaumer.com)
 * @date 22.04.20
 * @time 15:28
 * @project coronavirus-tracker
 */
public interface CoronaDataDAO {
    /**
     * Saves corona data into the database
     *
     * @param coronaData - object that gets written into the database
     * @return - true if operation was successful / false if not
     */
    boolean saveCoronaData ( CoronaData coronaData );

    /**
     * Saves corona data into the database
     *
     * @param coronaData - list of coronadata objects that will be written into the database
     * @return - true if operation was successful / false if not
     */
    boolean saveCoronaData ( List < CoronaData > coronaData );

    /**
     * Get all corona data from the database
     *
     * @return list of all corona data in the database
     */
    List < CoronaData > getAllCoronaData ( );

    /**
     * Get all corona data by date
     *
     * @param date - of the data
     * @return list of corona data by date
     */
    List < CoronaData > getAllCoronaDataByDate ( Date date );

    /**
     * Get all corona data by country id
     *
     * @param country_id - id of the country
     * @return list of corona data by country id
     */
    List < CoronaData > getAllCoronaDataByCountry ( int country_id );

    /**
     * Get all corona data from by date and country id
     *
     * @param date
     * @param country_id
     * @return corona data object
     */
    CoronaData getAllCoronaData ( Date date, int country_id );
}
