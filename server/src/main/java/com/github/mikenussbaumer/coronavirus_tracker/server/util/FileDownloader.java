package com.github.mikenussbaumer.coronavirus_tracker.server.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Mike Nußbaumer (dev@mike-nussbaumer.com)
 * @date 08.04.20
 * @time 11:51
 * @project coronavirus-tracker
 */
public class FileDownloader {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool( 1 );

    public void downloadFile ( String url, String fileName ) {
        try ( InputStream in = URI.create( url ).toURL( ).openStream( ) ) {
            Files.copy( in, Paths.get( fileName ) );
        } catch ( IOException e ) {
            e.printStackTrace( );
        }
    }

    public void startDownloadTask ( ) {
        scheduler.scheduleAtFixedRate( new Runnable( ) {
            @Override
            public void run ( ) {
                String url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
                String fileName = "data/currentCSVData.csv";

                try {
                    if ( Files.exists( Paths.get( fileName ) ) ) {
                        Files.delete( Paths.get( fileName ) );
                    }
                } catch ( IOException e ) {
                    e.printStackTrace( );
                }

                // download file from github
                downloadFile( url, fileName );
            }
        }, 0, 1, TimeUnit.DAYS );
    }
}
