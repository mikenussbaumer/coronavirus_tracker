package com.github.mikenussbaumer.coronavirus_tracker.server.managers;

import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.dao.CoronaDataDAOMySQL;
import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.dao.CountryDAOMySQL;
import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.entity.CoronaData;
import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.entity.Country;
import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.interfaces.CoronaDataDAO;
import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.interfaces.CountryDAO;
import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.managers.MySQLManager;
import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.util.DateUtil;
import com.github.mikenussbaumer.coronavirus_tracker.server.entity.CSVData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author Mike Nußbaumer (dev@mike-nussbaumer.com)
 * @date 03.06.20
 * @time 14:19
 * @project coronavirus-tracker
 */
public class CSVDataSaveManager {

    private MySQLManager mySQLManager;
    private CSVDataManager csvDataManager;

    public CSVDataSaveManager ( MySQLManager mySQLManager, CSVDataManager csvDataManager ) {
        this.mySQLManager = mySQLManager;
        this.csvDataManager = csvDataManager;
    }

    public void initDatabase() throws InterruptedException {

        CountryDAO countryDAO = new CountryDAOMySQL( mySQLManager );

        List < CSVData > csvData = csvDataManager.csvDataWithOutProvinces;

        for ( int i = 0; i < csvData.size( ); i++ ) {
            CSVData element = csvData.get( i );

            countryDAO.saveCountry( new Country( i, element.getCountry( ), element.getLatitude( ), element.getLongitude( ) ) );
        }

        Thread.sleep( 12000 );

        CoronaDataDAO coronaDataDAO = new CoronaDataDAOMySQL( mySQLManager );

        List < CoronaData > coronaDataList = new ArrayList <>( );

        int i = 1;
        for ( CSVData data : csvDataManager.csvDataWithOutProvinces ) {
            int finalI = i;
            data.getConfirmed_cases( ).forEach( ( date, cases ) -> {
                CoronaData coronaData = new CoronaData( 1, date, finalI, cases );
                coronaDataList.add( coronaData );
            } );
            i++;
        }

        coronaDataDAO.saveCoronaData( coronaDataList );
    }

    public void saveData ( ) {
        CoronaDataDAO coronaDataDAO = new CoronaDataDAOMySQL( mySQLManager );

        List < CoronaData > coronaDataList = new ArrayList <>( );
        List < CSVData > csvData = csvDataManager.csvDataWithOutProvinces;

        for ( int i = 0; i < csvData.size( ); i++ ) {
            CSVData data = csvData.get( i );

            Date date = DateUtil.createDateFromString( LocalDateTime.now( ).minusDays( 1 ).format( DateTimeFormatter.ofLocalizedDate( FormatStyle.SHORT ).localizedBy( Locale.ENGLISH ) ) );
            int cases = data.getConfirmed_cases( ).get( date );

            CoronaData coronaData = new CoronaData( 1, date, i, cases );
            coronaDataList.add( coronaData );
        }

        coronaDataDAO.saveCoronaData( coronaDataList );
    }
}
