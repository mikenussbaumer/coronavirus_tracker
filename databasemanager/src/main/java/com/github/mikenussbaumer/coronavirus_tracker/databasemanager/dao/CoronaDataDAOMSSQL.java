package com.github.mikenussbaumer.coronavirus_tracker.databasemanager.dao;

import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.entity.CoronaData;
import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.interfaces.CoronaDataDAO;
import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.managers.MSSQLManager;
import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.util.DateUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Mike Nußbaumer (dev@mike-nussbaumer.com)
 * @date 22.04.20
 * @time 15:34
 * @project coronavirus-tracker
 */
public class CoronaDataDAOMSSQL implements CoronaDataDAO {

    MSSQLManager mssqlManager;

    public CoronaDataDAOMSSQL ( MSSQLManager mssqlManager ) {
        this.mssqlManager = mssqlManager;
    }

    @Override
    public boolean saveCoronaData ( CoronaData coronaData ) {
        String query = "INSERT INTO corona_data (date, country_id, confirmed_cases) VALUES ('" + DateUtil.formatJavaDateToSQLDate( coronaData.getDate( ) ) + "', " + coronaData.getCountry_id( ) + "," + coronaData.getConfirmed_cases( ) + ")";
        mssqlManager.update( query );

        return true;
    }

    @Override
    public boolean saveCoronaData ( List < CoronaData > coronaData ) {

        String query = "INSERT INTO corona_data (date, country_id, confirmed_cases) VALUES (?,?,?)";

        try {
            PreparedStatement preparedStatement = mssqlManager.getConnection( ).prepareStatement( query );
            for ( CoronaData element : coronaData ) {
                preparedStatement.setDate( 1, DateUtil.formatJavaDateToSQLDate( element.getDate( ) ) );
                preparedStatement.setInt( 2, element.getCountry_id( ) );
                preparedStatement.setInt( 3, element.getConfirmed_cases( ) );
                preparedStatement.addBatch( );
            }

            preparedStatement.executeBatch( );
        } catch ( SQLException e ) {
            e.printStackTrace( );
        }

        return true;
    }

    @Override
    public List < CoronaData > getAllCoronaData ( ) {

        List < CoronaData > result = new ArrayList <>( );

        String query = "SELECT * FROM corona_data";
        ResultSet resultSet = mssqlManager.query( query );

        try {
            while ( resultSet.next( ) ) {
                int id = resultSet.getInt( "id" );
                Date date = resultSet.getDate( "date" );
                int country_id = resultSet.getInt( "country_id" );
                int confirmed_cases = resultSet.getInt( "confirmed_cases" );

                result.add( new CoronaData( id, date, country_id, confirmed_cases ) );
            }
        } catch ( SQLException e ) {
            e.printStackTrace( );
        }

        return result;
    }

    @Override
    public List < CoronaData > getAllCoronaDataByDate ( Date date ) {
        List < CoronaData > result = new ArrayList <>( );

        String query = "SELECT * FROM corona_data WHERE date='" + DateUtil.formatJavaDateToSQLDate( date ).toString( ) + "'";
        ResultSet resultSet = mssqlManager.query( query );

        try {
            while ( resultSet.next( ) ) {
                int id = resultSet.getInt( "id" );
                int country_id = resultSet.getInt( "country_id" );
                int confirmed_cases = resultSet.getInt( "confirmed_cases" );

                result.add( new CoronaData( id, date, country_id, confirmed_cases ) );
            }
        } catch ( SQLException e ) {
            e.printStackTrace( );
        }

        return result;
    }

    @Override
    public List < CoronaData > getAllCoronaDataByCountry ( int country_id ) {
        List < CoronaData > result = new ArrayList <>( );

        String query = "SELECT * FROM corona_data WHERE country_id=" + country_id;
        ResultSet resultSet = mssqlManager.query( query );

        try {
            while ( resultSet.next( ) ) {
                int id = resultSet.getInt( "id" );
                Date date = resultSet.getDate( "date" );
                int confirmed_cases = resultSet.getInt( "confirmed_cases" );

                result.add( new CoronaData( id, date, country_id, confirmed_cases ) );
            }
        } catch ( SQLException e ) {
            e.printStackTrace( );
        }

        return result;
    }

    @Override
    public CoronaData getAllCoronaData ( Date date, int country_id ) {
        CoronaData result = new CoronaData( );
        result.setDate( date );
        result.setCountry_id( country_id );

        String query = "SELECT * FROM corona_data WHERE country_id=" + country_id + " AND date='" + DateUtil.formatJavaDateToSQLDate( date ).toString( ) + "'";
        ResultSet resultSet = mssqlManager.query( query );

        try {
            while ( resultSet.next( ) ) {
                int id = resultSet.getInt( "id" );
                int confirmed_cases = resultSet.getInt( "confirmed_cases" );

                result.setId( id );
                result.setConfirmed_cases( confirmed_cases );
            }
        } catch ( SQLException e ) {
            e.printStackTrace( );
        }

        return result;
    }
}
