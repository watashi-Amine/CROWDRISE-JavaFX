/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.mainform;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author eby
 */
public class Main extends Application {
    
    private Stage primaryStage;
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/crowd/CV/login1.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
     public Stage getPrimaryStage() {
        return primaryStage;
    }
    
}
