package com.github.mikenussbaumer.coronavirus_tracker.server;

import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.managers.MySQLManager;
import com.github.mikenussbaumer.coronavirus_tracker.server.managers.CSVDataManager;
import com.github.mikenussbaumer.coronavirus_tracker.server.managers.CSVDataSaveManager;
import com.github.mikenussbaumer.coronavirus_tracker.server.util.FileDownloader;
import lombok.SneakyThrows;

/**
 * @author Mike Nußbaumer (dev@mike-nussbaumer.com)
 * @date 08.04.20
 * @time 11:49
 * @project coronavirus-tracker
 */
public class Main {

    private static FileDownloader fileDownloader;
    private static MySQLManager mySQLManager;
    private static CSVDataManager csvDataManager;
    private static CSVDataSaveManager csvDataSaveManager;

    public static void main ( String[] args ) {
        init( );

        Runtime.getRuntime( ).addShutdownHook( new Thread( new Runnable( ) {
            @Override
            public void run ( ) {
                shutdown( );
            }
        } ) );
    }

    @SneakyThrows
    private static void init ( ) {
        fileDownloader = new FileDownloader( );
        fileDownloader.startDownloadTask( );

        mySQLManager = new MySQLManager( );
        mySQLManager.openConnection( );

        Thread.sleep( 2000 );

        csvDataManager = new CSVDataManager( );

        csvDataSaveManager = new CSVDataSaveManager( mySQLManager, csvDataManager );
        csvDataSaveManager.saveData( );
    }

    private static void shutdown ( ) {
        mySQLManager.closeConnection( );
    }
}
