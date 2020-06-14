package com.github.mikenussbaumer.coronavirus_tracker.databasemanager.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * @author Mike Nußbaumer (dev@mike-nussbaumer.com)
 * @date 22.04.20
 * @time 16:18
 * @project coronavirus-tracker
 */
public class DateUtil {

    public static Date formatJavaDateToSQLDate ( java.util.Date date ) {
        SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
        final String stringDate = dateFormat.format( date );

        return Date.valueOf( stringDate );
    }

    public static java.util.Date createDateFromString ( String dateString ) {
        java.util.Date date = null;
        try {
            date = DateFormat.getDateInstance( FormatStyle.SHORT.ordinal(), Locale.ENGLISH ).parse( dateString );
        } catch ( ParseException e ) {
            e.printStackTrace( );
        }

        return date;
    }
}
