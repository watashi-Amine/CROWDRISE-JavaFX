/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.CV;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author MohamedAmine
 */
public class ProjetsAccueilController implements Initializable {

    @FXML
    private Label Resume;
    @FXML
    private ProgressBar EvolutioniD;
    @FXML
    private Label Pudgetid;
    @FXML
    private Label Argentid;
    @FXML
    private VBox VBosId;
    @FXML
    private GridPane aaa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      
        for (int i = 0; i < 10; i++) {
           aaa.getChildren().set(i, aaa);
        }
        
        
    }    

    @FXML
    private void EvolutionAction(MouseEvent event) {
    }

    @FXML
    private void FinancerAction(ActionEvent event) {
    }
    
    
    
    
    
    
}
