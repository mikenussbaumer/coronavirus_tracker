package com.github.mikenussbaumer.coronavirus_tracker.client;

import com.github.mikenussbaumer.coronavirus_tracker.client.services.DataService;
import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.managers.MySQLManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.Getter;

public class Main extends Application {


    @Getter
    private static MySQLManager mySQLManager;

    @Getter
    private static DataService dataService;

    @Override
    public void start ( Stage primaryStage ) throws Exception {
        mySQLManager = new MySQLManager( );

        if ( !mySQLManager.openConnection( ) )
            System.exit( 0 );

        dataService = new DataService( mySQLManager );
        System.out.println( dataService.getTotalCoronaCasesToday( ) );


        try {
            Thread.sleep( 1000 );
        } catch ( InterruptedException e ) {
            e.printStackTrace( );
        }

        Parent root = FXMLLoader.load( getClass( ).getResource( "/main.fxml" ) );
        primaryStage.setTitle( "Coronavirus Tracker" );
        primaryStage.setScene( new Scene( root ) );
        primaryStage.setResizable( false );
        primaryStage.show( );

    }

    @Override
    public void stop ( ) throws Exception {

    }

    public static void main ( String[] args ) {
        launch( args );
    }
}
