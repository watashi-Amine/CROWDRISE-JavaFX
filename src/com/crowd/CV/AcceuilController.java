/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.CV;

import com.crowd.animations.FadeInLeftTransition;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ASR1
 */
public class AcceuilController implements Initializable {
    @FXML
    private AnchorPane paneParent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
    
     this.fadeIn();
        this.startUp();
    }

    private void startUp() {
     
    }
    
    
     private void fadeIn() {
        new FadeInLeftTransition(paneParent).play();
    }
     
     private void f(){
         System.out.println("sswdsfsss");   
     }
     
      private void f22(){
         System.out.println("yo");   
     }
      
       private void Azerty(){
         System.out.println("yo");   
     }
}
