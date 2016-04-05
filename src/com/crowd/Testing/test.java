package com.crowd.Testing;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;









public class test extends Application { 
  
    @Override 
    public void start(Stage primaryStage) { 
        // Création du graphique. 
        final PieChart chart = new PieChart(); 
        chart.setTitle("type projet"); 
        chart.getData().setAll(new PieChart.Data("Pommes", 50), new PieChart.Data("Oranges", 30),  
                new PieChart.Data("Poires", 25), new PieChart.Data("Pêches", 42), 
                new PieChart.Data("Citrons", 5), new PieChart.Data("Kiwis", 19) 
        ); 
        // Montage de l'IU. 
        final StackPane root = new StackPane(); 
        root.getChildren().add(chart); 
        final Scene scene = new Scene(root, 300, 250); 
        primaryStage.setTitle("Test de PieChart"); 
        primaryStage.setScene(scene); 
        primaryStage.show(); 
    } 
  
    public static void main(String[] args) { 
        launch(args); 
    } 
}