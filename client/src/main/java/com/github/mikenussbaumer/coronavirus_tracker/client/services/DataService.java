package com.github.mikenussbaumer.coronavirus_tracker.client.services;

import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.entity.CoronaData;
import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.managers.MySQLManager;
import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.util.DateUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

/**
 * @author Mike Nußbaumer (dev@mike-nussbaumer.com)
 * @date 14.06.20
 * @time 15:35
 * @project corona-tracker
 */
public class DataService {

    private MySQLManager mySQLManager;

    public DataService ( MySQLManager mySQLManager ) {
        this.mySQLManager = mySQLManager;
    }

    public int getTotalCoronaCasesToday ( ) {

        int result = -1;
        Date date = DateUtil.createDateFromString( LocalDateTime.now( ).minusDays( 2 ).format( DateTimeFormatter.ofLocalizedDate( FormatStyle.SHORT ).localizedBy( Locale.ENGLISH ) ) );

        String query = "SELECT SUM(confirmed_cases) AS cases FROM corona_data WHERE date='" + DateUtil.formatJavaDateToSQLDate( date ).toString( ) + "'";
        ResultSet resultSet = mySQLManager.query( query );

        try {
            while ( resultSet.next( ) ) {
                result = resultSet.getInt( "cases" );
            }
        } catch ( SQLException e ) {
            e.printStackTrace( );
        }

        try {
            resultSet.close( );
        } catch ( SQLException e ) {
            e.printStackTrace( );
        }

        return result;
    }

    public int getTotalCoronaCases ( Date date ) {
        int result = -1;

        String query = "SELECT SUM(confirmed_cases) AS cases FROM corona_data WHERE date='" + DateUtil.formatJavaDateToSQLDate( date ).toString( ) + "'";
        ResultSet resultSet = mySQLManager.query( query );

        try {
            while ( resultSet.next( ) ) {
                result = resultSet.getInt( "cases" );
            }
        } catch ( SQLException e ) {
            e.printStackTrace( );
        }

        try {
            resultSet.close( );
        } catch ( SQLException e ) {
            e.printStackTrace( );
        }

        return result;
    }

    public int getTotalCoronaCasesCountry ( int countryId ) {
        int result = -1;

        String query = "SELECT * FROM corona_data WHERE country_id=" + countryId + " ORDER BY date DESC LIMIT 1";
        ResultSet resultSet = mySQLManager.query( query );

        try {
            while ( resultSet.next( ) ) {
                result = resultSet.getInt( "confirmed_cases" );
            }
        } catch ( SQLException e ) {
            e.printStackTrace( );
        }

        try {
            resultSet.close( );
        } catch ( SQLException e ) {
            e.printStackTrace( );
        }

        return result;
    }

    public int getTotalCoronaCasesCountry ( int countryId, Date date ) {
        int result = -1;

        String query = "SELECT * FROM corona_data WHERE country_id=" + countryId + " AND date='" + DateUtil.formatJavaDateToSQLDate( date ).toString( ) + "'";
        ResultSet resultSet = mySQLManager.query( query );

        try {
            while ( resultSet.next( ) ) {
                result = resultSet.getInt( "confirmed_cases" );
            }
        } catch ( SQLException e ) {
            e.printStackTrace( );
        }

        try {
            resultSet.close( );
        } catch ( SQLException e ) {
            e.printStackTrace( );
        }

        return result;
    }

    public List < String > getCountries ( ) {

        List < String > result = new ArrayList <>( );

        String query = "SELECT id, name FROM countries";
        ResultSet resultSet = mySQLManager.query( query );

        try {
            while ( resultSet.next( ) ) {
                result.add( resultSet.getInt( "id" ) + ". " + resultSet.getString( "name" ) );
            }
        } catch ( SQLException e ) {
            e.printStackTrace( );
        }

        try {
            resultSet.close( );
        } catch ( SQLException e ) {
            e.printStackTrace( );
        }

        return result;
    }

    public List < CoronaData > getCoronaDataGlobal ( ) {
        List < CoronaData > result = new ArrayList <>( );

        String query = "SELECT id, date, SUM(confirmed_cases) AS cases FROM corona_data GROUP BY date ORDER BY date ASC";
        ResultSet resultSet = mySQLManager.query( query );

        try {
            while ( resultSet.next( ) ) {
                Date date = resultSet.getDate( "date" );
                int confirmed_cases = resultSet.getInt( "cases" );

                result.add( new CoronaData( -1, date, -1, confirmed_cases ) );
            }
        } catch ( SQLException e ) {
            e.printStackTrace( );
        }

        return result;
    }

    public List < CoronaData > getCoronaDataLocal ( int countryId ) {
        List < CoronaData > result = new ArrayList <>( );

        String query = "SELECT date, SUM(confirmed_cases) AS cases FROM corona_data WHERE country_id=" + countryId + " GROUP BY date ORDER BY date ASC";
        ResultSet resultSet = mySQLManager.query( query );

        try {
            while ( resultSet.next( ) ) {
                Date date = resultSet.getDate( "date" );
                int confirmed_cases = resultSet.getInt( "cases" );

                result.add( new CoronaData( -1, date, countryId, confirmed_cases ) );
            }
        } catch ( SQLException e ) {
            e.printStackTrace( );
        }

        return result;
    }
}
