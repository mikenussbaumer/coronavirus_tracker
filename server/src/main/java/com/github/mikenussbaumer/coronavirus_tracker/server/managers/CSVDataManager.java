package com.github.mikenussbaumer.coronavirus_tracker.server.managers;

import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.util.DateUtil;
import com.github.mikenussbaumer.coronavirus_tracker.server.csvparser.CSVParser;
import com.github.mikenussbaumer.coronavirus_tracker.server.entity.CSVData;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author Mike Nußbaumer (dev@mike-nussbaumer.com)
 * @date 23.04.20
 * @time 20:10
 * @project coronavirus-tracker
 */
public class CSVDataManager {

    private CSVParser csvParser;

    public List < CSVRecord > rawCsvData;

    public List < CSVData > csvData;
    public List < CSVData > csvDataWithOutProvinces;

    public CSVDataManager ( ) {
        refreshData( );
    }

    /**
     * Load data from csv file into data structures
     */
    public void refreshData ( ) {
        this.csvParser = new CSVParser( "data/currentCSVData.csv" );
        this.rawCsvData = csvParser.parseCSVFile( );

        this.csvData = getCSVData( );
        this.csvDataWithOutProvinces = getCSVDataWithOutProvinces( );
    }

    /**
     * Get csvdata from the csv file
     *
     * @return list with csvdata objects
     */
    public List < CSVData > getCSVData ( ) {
        List < CSVData > csvDataList = new ArrayList <>( );

        // loop through raw csvdata
        for ( CSVRecord record : rawCsvData ) {
            CSVData csvData = new CSVData( );

            csvData.setCountry( record.get( 1 ) );
            csvData.setLatitude( Double.parseDouble( record.get( 2 ) ) );
            csvData.setLongitude( Double.parseDouble( record.get( 3 ) ) );

            HashMap < Date, Integer > confirmed_cases = new HashMap <>( );
            Object[] header = record.toMap( ).keySet( ).toArray( );

            // loop through confirmed cases
            for ( int i = 4; i < header.length; i++ ) {
                // get date string from csv header
                String dateString = ( String ) header[ i ];

                Date date = DateUtil.createDateFromString( dateString );
                int corona_cases_on_date = Integer.parseInt( record.get( i ) );

                confirmed_cases.put( date, corona_cases_on_date );
            }

            csvData.setConfirmed_cases( confirmed_cases );

            csvDataList.add( csvData );
        }

        return csvDataList;
    }

    /**
     * Get all csvdata without provinces (merged)
     *
     * @return list with csvdata objects without provinces
     */
    public List < CSVData > getCSVDataWithOutProvinces ( ) {
        HashMap < String, CSVData > duplicateHashMap = new HashMap <>( );

        for ( CSVData data : csvData ) {
            String country = data.getCountry( );

            if ( !duplicateHashMap.containsKey( country ) ) {
                duplicateHashMap.put( country, getCSVDataFromCountry( country ) );
            }
        }

        return new ArrayList <>( duplicateHashMap.values( ) );
    }

    /**
     * Get hashmap of countries with confirmed cases by date
     *
     * @param date
     * @return hashmap with countries and confirmed cases
     */
    public HashMap < String, Integer > getConfirmedCasesByDate ( Date date ) {
        HashMap < String, Integer > result = new HashMap <>( );

        for ( CSVData data : csvDataWithOutProvinces ) {
            result.put( data.getCountry( ), data.getConfirmed_cases( ).get( date ) );
        }

        return result;
    }

    /**
     * Merges all cases of the single provinces of a country into to one overall csvdata object
     *
     * @param country - country name
     * @return merged csvdata object of a country
     */
    public CSVData getCSVDataFromCountry ( String country ) {

        CSVData result = new CSVData( );

        for ( CSVData data : csvData ) {
            if ( data.getCountry( ).equals( country ) ) {
                result.setCountry( country );
                result.setLatitude( data.getLatitude( ) );
                result.setLongitude( data.getLongitude( ) );

                if ( result.getConfirmed_cases( ) == null ) {
                    result.setConfirmed_cases( data.getConfirmed_cases( ) );
                } else {
                    result.setConfirmed_cases( mergeHashMaps( data.getConfirmed_cases( ), result.getConfirmed_cases( ) ) );
                }
            }
        }

        return result;
    }

    /**
     * Merges 2 hashmaps into one by summing up the values
     *
     * @param map1
     * @param map2
     * @return merged hashmap of map1 and map2
     */
    private HashMap < Date, Integer > mergeHashMaps ( HashMap < Date, Integer > map1, HashMap < Date, Integer > map2 ) {
        map2.forEach( ( key, value ) -> map1.merge( key, value, Integer::sum ) );
        return map1;
    }
}
