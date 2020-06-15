package com.github.mikenussbaumer.coronavirus_tracker.client;

import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.dao.CoronaDataDAOMySQL;
import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.entity.CoronaData;
import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.interfaces.CoronaDataDAO;
import com.github.mikenussbaumer.coronavirus_tracker.databasemanager.util.DateUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class Controller implements Initializable {

    @FXML
    public JFXButton numberTotalGlobal;
    public JFXButton numberDateGlobal;
    public JFXButton numberTotalLocal;
    public JFXButton numberDateLocal;
    public JFXComboBox < String > countryList;
    public JFXDatePicker globalDate;
    public JFXDatePicker localDate;
    public LineChart < String, Number > globalChart;
    public LineChart < String, Number > localChart;

    private int currentCountryId = 110;

    private int totalCasesGlobal = -1;
    private int totalCasesLocal = -1;

    private int dateCasesGlobal = -1;
    private int dateCasesLocal = -1;

    private Date todayDate = DateUtil.createDateFromString( LocalDateTime.now( ).minusDays( 2 ).format( DateTimeFormatter.ofLocalizedDate( FormatStyle.SHORT ).localizedBy( Locale.ENGLISH ) ) );

    private CoronaDataDAO coronaDataDAO;

    @Override
    public void initialize ( URL location, ResourceBundle resources ) {

        coronaDataDAO = new CoronaDataDAOMySQL( Main.getMySQLManager( ) );

        countryList.getItems( ).addAll( Main.getDataService( ).getCountries( ) );
        countryList.setValue( "110. Austria" );

        globalDate.setValue( todayDate.toInstant( ).atZone( ZoneId.systemDefault( ) ).toLocalDate( ) );
        localDate.setValue( todayDate.toInstant( ).atZone( ZoneId.systemDefault( ) ).toLocalDate( ) );

        totalCasesLocal = Main.getDataService( ).getTotalCoronaCasesCountry( currentCountryId );
        numberTotalLocal.setText( "Total cases: " + String.format( "%,d", totalCasesLocal ) );

        dateCasesGlobal = Main.getDataService( ).getTotalCoronaCasesCountry( currentCountryId );
        numberDateGlobal.setText( "Total cases: " + String.format( "%,d", totalCasesLocal ) );

        refreshDataGlobal( );

        countryList.setOnAction( event -> {
            refreshDataLocal( );
        } );

        localDate.setOnAction( event -> {
            refreshDataLocal( );
        } );

        globalDate.setOnAction( event -> {
            refreshDataGlobal( );
        } );

    }

    private void refreshDataGlobal ( ) {
        totalCasesGlobal = Main.getDataService( ).getTotalCoronaCasesToday( );
        numberTotalGlobal.setText( "Total cases: " + String.format( "%,d", totalCasesGlobal ) );

        dateCasesGlobal = Main.getDataService( ).getTotalCoronaCases( Date.from( globalDate.getValue( ).atStartOfDay( ZoneId.systemDefault( ) ).toInstant( ) ) );
        numberDateGlobal.setText( "Total cases: " + String.format( "%,d", dateCasesGlobal ) );

        initGlobalChart( );
    }

    private void refreshDataLocal ( ) {
        currentCountryId = countryList.getItems( ).indexOf( countryList.getValue( ) ) + 1;
        totalCasesLocal = Main.getDataService( ).getTotalCoronaCasesCountry( currentCountryId );
        numberTotalLocal.setText( "Total cases: " + String.format( "%,d", totalCasesLocal ) );

        dateCasesLocal = Main.getDataService( ).getTotalCoronaCasesCountry( currentCountryId, Date.from( localDate.getValue( ).atStartOfDay( ZoneId.systemDefault( ) ).toInstant( ) ) );
        numberDateLocal.setText( "Total cases: " + String.format( "%,d", dateCasesLocal ) );

        initLocalChart( );
    }

    private void initGlobalChart ( ) {
        globalChart.getData( ).clear( );
        globalChart.setTitle( "Global Corona stats overall" );

        XYChart.Series series1 = new XYChart.Series( );
        series1.setName( "Cases" );

        List < CoronaData > coronaData = Main.getDataService( ).getCoronaDataGlobal( );

        for ( CoronaData data : coronaData ) {
            series1.getData( ).add( new XYChart.Data( data.getDate( ).toString( ), data.getConfirmed_cases( ) ) );
        }

        globalChart.getData( ).addAll( series1 );
    }

    private void initLocalChart ( ) {

        localChart.getData( ).clear( );

        localChart.setTitle( "Local Corona stats overall" );

        XYChart.Series series1 = new XYChart.Series( );
        series1.setName( "Cases" );

        List < CoronaData > coronaData = Main.getDataService( ).getCoronaDataLocal( currentCountryId );

        for ( CoronaData data : coronaData ) {
            series1.getData( ).add( new XYChart.Data( data.getDate( ).toString( ), data.getConfirmed_cases( ) ) );
        }

        localChart.getData( ).addAll( series1 );
    }
}
