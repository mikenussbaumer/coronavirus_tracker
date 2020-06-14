package com.github.mikenussbaumer.coronavirus_tracker.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start ( Stage primaryStage ) throws Exception {
        Parent root = FXMLLoader.load( getClass( ).getResource( "/main.fxml" ) );
        primaryStage.setTitle( "Coronavirus Tracker" );

        primaryStage.getIcons( ).add( new Image( Main.class.getResourceAsStream( "/assets/images/favicon.png" ) ) );
        primaryStage.setScene( new Scene( root ) );
        primaryStage.setResizable( false );
        primaryStage.show( );
    }

    public static void main ( String[] args ) {
        launch( args );
    }
}
