package com.github.mikenussbaumer.coronavirus_tracker.databasemanager.dao;

import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.entity.Country;
import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.interfaces.CountryDAO;
import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.managers.MSSQLManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mike Nußbaumer (dev@mike-nussbaumer.com)
 * @date 22.04.20
 * @time 15:11
 * @project coronavirus-tracker
 */
public class CountryDAOMSSQL implements CountryDAO {

    MSSQLManager mssqlManager;

    public CountryDAOMSSQL ( MSSQLManager mssqlManager ) {
        this.mssqlManager = mssqlManager;
    }

    @Override
    public boolean saveCountry ( Country country ) {
        String query = "INSERT INTO countries (name, latitude, longitude) VALUES ('" + country.getName( ) + "', " + country.getLatitude( ) + ", " + country.getLongitude( ) + ")";
        mssqlManager.update( query );

        return true;
    }

    @Override
    public List < Country > getAllCountries ( ) {

        List < Country > result = new ArrayList <>( );

        String query = "SELECT * FROM countries";
        ResultSet resultSet = mssqlManager.query( query );

        try {
            while ( resultSet.next( ) ) {

                int id = resultSet.getInt( "id" );
                String name = resultSet.getString( "name" );
                double latitude = resultSet.getDouble( "latitude" );
                double longitude = resultSet.getDouble( "longitude" );

                result.add( new Country( id, name, latitude, longitude ) );
            }
        } catch ( SQLException e ) {
            e.printStackTrace( );
        }

        return result;
    }

    @Override
    public Country readCountry ( int id ) {

        Country country = new Country( );
        country.setId( id );

        String query = "SELECT * FROM countries WHERE id=" + id;
        ResultSet resultSet = mssqlManager.query( query );

        try {
            while ( resultSet.next( ) ) {
                String name = resultSet.getString( "name" );
                double latitude = resultSet.getDouble( "latitude" );
                double longitude = resultSet.getDouble( "longitude" );

                country.setName( name );
                country.setLatitude( latitude );
                country.setLongitude( longitude );
            }
        } catch ( SQLException e ) {
            e.printStackTrace( );
        }

        return country;
    }
}
