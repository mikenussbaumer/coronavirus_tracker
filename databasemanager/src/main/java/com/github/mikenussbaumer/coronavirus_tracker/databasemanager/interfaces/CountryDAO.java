package com.github.mikenussbaumer.coronavirus_tracker.databasemanager.interfaces;

import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.entity.Country;

import java.util.List;

/**
 * @author Mike Nußbaumer (dev@mike-nussbaumer.com)
 * @date 22.04.20
 * @time 15:10
 * @project coronavirus-tracker
 */
public interface CountryDAO {
    /**
     * Saves a country into the database
     *
     * @param country - object that gets written into the database
     * @return - true if operation was successful / false if not
     */
    boolean saveCountry ( Country country );

    /**
     * Get all countries from the database
     *
     * @return list of all countries in the database
     */
    List < Country > getAllCountries ( );

    /**
     * Get a specific country by id from the database
     *
     * @param id - of the country
     * @return country object
     */
    Country readCountry ( int id );
}
