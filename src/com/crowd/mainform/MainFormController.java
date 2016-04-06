/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.mainform;

import application.MaterialText;

import com.crowd.entities.Membre;
import com.crowd.helper.LoadStage;
import com.crowd.helper.VBoxContentHelper;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author eby
 */
public class  MainFormController implements Initializable {
    
    
    

   

    @FXML
    private VBox hBoxMenu;
    @FXML
    private AnchorPane viewPane;
    @FXML
    private Pane sidePane;
    @FXML
    private JFXButton btnMenu;

    private LoadStage loadStage;
    @FXML
    public Text usernameText;
    @FXML
    private Label ff;
    @FXML
    private AnchorPane anchorparent;
    
 
   

    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   this.startUp();
//        this.prepareSlideMenuAnimation();
        
        
    }

   
        

   
   
    
    
    

    public void startUp() {
        
        
        
        loadStage = new LoadStage();
        VBoxContentHelper.propHelper("Acceuil", new MaterialDesignIconView(MaterialDesignIcon.HOME_VARIANT), hBoxMenu, new EventHandler() {

            @Override
            public void handle(Event event) {
                System.out.println("Acceuil click !!!");
                
                         
                                loadStage.loadAnchorPane(viewPane, "/CV/Accueil.fxml");

            }
        });

        VBoxContentHelper.propHelper("Parametre ", new MaterialDesignIconView(MaterialDesignIcon.SETTINGS), hBoxMenu, new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                System.out.println("parametre de compte click !!!");
            }
        });
         VBoxContentHelper.propHelper("Projets", new MaterialDesignIconView(MaterialDesignIcon.PANDA), hBoxMenu, new EventHandler() {

            @Override
            public void handle(Event event) {
                System.out.println("About Click Success !!!");
                loadStage.loadAnchorPane(viewPane, "/CV/Projets.fxml");
            }
        });

           VBoxContentHelper.propHelper("Mes Projets", new MaterialDesignIconView(MaterialDesignIcon.PANDORA), hBoxMenu, new EventHandler() {

            @Override
            public void handle(Event event) {
                System.out.println("About Click Success !!!");
                loadStage.loadAnchorPane(viewPane, "/CV/MesProjets.fxml");
            }
        });
         
         
        VBoxContentHelper.propHelper("About", new MaterialDesignIconView(MaterialDesignIcon.INFORMATION), hBoxMenu, new EventHandler() {

            @Override
            public void handle(Event event) {
                System.out.println("About Click Success !!!");
                loadStage.loadAnchorPane(viewPane, "/about/About.fxml");
            }
        });
               VBoxContentHelper.propHelper("Les Comantaires", new MaterialDesignIconView(MaterialDesignIcon.HEART), hBoxMenu, new EventHandler() {

            @Override
            public void handle(Event event) {
                System.out.println("comment click !!!");
                loadStage.loadAnchorPane(viewPane, "/CV/Comantaire.fxml");
            }
        });

        MaterialIconView iconMenu = new MaterialIconView(MaterialIcon.ARROW_BACK);
        iconMenu.getStyleClass().add("icon");
        iconMenu.setSize("34");
        btnMenu.setGraphic(iconMenu);
        
        

         
    }

    public Text getUsernameText() {
        return usernameText;
    }

    public void setUsernameText(String usr) {
usernameText.setText(usr);
    }

    public Label getFf() {
        return ff;
    }

    public void setFf(String fff) {
        ff.setText(fff);
    }

   
         
    
    
    
    
    @FXML
    private void menuAction(ActionEvent event) {
        
        TranslateTransition openNav = new TranslateTransition(new Duration(350), sidePane);
        openNav.setToX(0);
        TranslateTransition closeNav = new TranslateTransition(new Duration(350), sidePane);
        if (sidePane.getTranslateX() != 0) {
            MaterialIconView iconMenu = new MaterialIconView(MaterialIcon.ARROW_BACK);
            iconMenu.getStyleClass().add("icon");
            iconMenu.setSize("34");
            btnMenu.setGraphic(iconMenu);
            openNav.play();

            viewPane.setLayoutX(250);
            viewPane.setLayoutY(50);
            viewPane.setPrefSize(750, 550);
        } else {
            closeNav.setToX(-(sidePane.getWidth()));
            MaterialIconView iconMenu = new MaterialIconView(MaterialIcon.MENU);
            iconMenu.getStyleClass().add("icon");
            iconMenu.setSize("34");
            btnMenu.setGraphic(iconMenu);
            closeNav.play();
            
            viewPane.setLayoutX(0);
            viewPane.setLayoutY(50);
            viewPane.setPrefSize(1000, 550);
        }
    }

    



}


