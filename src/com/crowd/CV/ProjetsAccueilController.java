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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void EvolutionAction(MouseEvent event) {
    }

    @FXML
    private void FinancerAction(ActionEvent event) {
    }
    
}
