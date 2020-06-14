package com.github.mikenussbaumer.coronavirus_tracker.server.csvparser;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mike Nußbaumer (dev@mike-nussbaumer.com)
 * @date 22.04.20
 * @time 15:53
 * @project coronavirus-tracker
 */

public class CSVParser {

    private String filePath;

    public CSVParser ( String filePath ) {
        this.filePath = filePath;
    }

    public List < CSVRecord > parseCSVFile ( ) {

        List < CSVRecord > records = new ArrayList <>( );
        Reader in = null;

        try {
            in = new FileReader( filePath );
            Iterable < CSVRecord > iterable = CSVFormat.RFC4180.withFirstRecordAsHeader( ).parse( in );

            for ( CSVRecord record : iterable ) {
                records.add( record );
            }
        } catch ( IOException e ) {
            e.printStackTrace( );
        }

        return records;
    }
}
