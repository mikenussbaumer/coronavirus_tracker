package com.github.mikenussbaumer.coronavirus_tracker.databasemanager.managers;

import com.github.mikenussbaumer.coronavirus_tracker.configmanager.ConfigManager;
import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.configs.MySQLConfig;
import lombok.Getter;

import java.io.File;
import java.sql.*;
import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Mike Nußbaumer (dev@mike-nussbaumer.com)
 * @date 14.06.20
 * @time 11:25
 * @project coronavirus-tracker
 */
public class MySQLManager {

    private static ConfigManager < MySQLConfig > databaseConfig;
    private String hostname, username, password, database;
    private int port;

    @Getter
    private Connection connection;

    private static ExecutorService executorService = Executors.newCachedThreadPool( );

    public MySQLManager ( ) {
        createConfig( );
        this.hostname = getDatabaseConfig( ).getHostname( );
        this.username = getDatabaseConfig( ).getUsername( );
        this.password = getDatabaseConfig( ).getPassword( );
        this.database = getDatabaseConfig( ).getDatabase( );
        this.port = getDatabaseConfig( ).getPort( );
    }

    public MySQLManager ( String hostname, String username, String password, String database, int port ) {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
        this.database = database;
        this.port = port;
    }

    //<editor-fold desc="openConnection">
    public boolean openConnection ( ) {
        try {
            String uri = MessageFormat.format( "{0}://{1}:{2}/{3}?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "jdbc:mysql", hostname, String.valueOf( port ), database );

            this.connection = DriverManager.getConnection( uri, username, password );

            if ( !this.connection.isClosed( ) && connection != null ) {
                System.out.println( "[MySQLManager] Successfully connected to Database: " + uri );
                return true;
            }
        } catch ( SQLException error ) {
            System.err.println( "[MySQLManager] Can't connect to Database: " + error.getMessage( ) );
        }

        return false;
    }
    //</editor-fold>

    //<editor-fold desc="closeConnection">
    public boolean closeConnection ( ) {
        try {
            if ( !connection.isClosed( ) && connection != null ) {
                connection.close( );
                executorService.shutdown( );

                if ( connection.isClosed( ) ) {
                    System.out.println( "[MySQLManager] Connection successfully closed." );
                    return true;
                } else {
                    System.err.println( "[MySQLManager] Can't close the sql connection" );
                }
            }
        } catch ( SQLException error ) {
            System.err.println( "[MySQLManager] Can't close the sql connection: " + error.getMessage( ) );
        }

        return false;
    }
    //</editor-fold>

    //<editor-fold desc="update">
    public void update ( String query ) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement( query );
            preparedStatement.executeUpdate( );
            closePreparedStatement( preparedStatement );
        } catch ( SQLException error ) {
            error.printStackTrace( );
            System.err.println( "[MySQLManager] Update error: " + error.getMessage( ) );
        }
    }
    //</editor-fold>@@ﬂﬂﬂ@

    //<editor-fold desc="multiUpdate">
    public void multiUpdate ( String... multiquery ) {
        for ( String query : multiquery ) {
            this.update( query );
        }
    }
    //</editor-fold>

    //<editor-fold desc="query">
    public ResultSet query ( String query ) {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement( query );
            resultSet = preparedStatement.executeQuery( );
            closePreparedStatement( preparedStatement );
        } catch ( SQLException error ) {
            System.err.println( "[MySQLManager] Query error: " + error.getMessage( ) );
        }
        return resultSet;
    }
    //</editor-fold>

    //<editor-fold desc="closePreparedStatement">
    private void closePreparedStatement ( PreparedStatement preparedStatement ) {
        executorService.execute( new Runnable( ) {
            @Override
            public void run ( ) {
                try {
                    Thread.sleep( 20000 );
                    preparedStatement.close( );
                } catch ( Exception e ) {
                    e.printStackTrace( );
                }
            }
        } );
    }
    //</editor-fold>

    //<editor-fold desc="createConfig">
    private void createConfig ( ) {
        databaseConfig = new ConfigManager <>( new File( "MySQLConfig.json" ), MySQLConfig.class );
    }
    //</editor-fold>

    //<editor-fold desc="getDatabaseConfig">
    public static MySQLConfig getDatabaseConfig ( ) {
        return databaseConfig.getSettings( );
    }
    //</editor-fold>
}
