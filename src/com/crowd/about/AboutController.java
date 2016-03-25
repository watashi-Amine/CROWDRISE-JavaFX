/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.about;

import application.MaterialText;
import com.crowd.animations.FadeInLeftTransition;
import com.crowd.helper.LoadStage;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author eby
 */
public class AboutController implements Initializable {

    @FXML
    private JFXButton btnFacebook;
    @FXML
    private AnchorPane paneParent;
    @FXML
    private ImageView imageEby;
    @FXML
    private ImageView imageLC;
    @FXML
    private JFXButton btnTwitter;
    @FXML
    private MaterialText ss;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.fadeIn();
        this.startUp();
    }

    private void startUp() {
        MaterialDesignIconView fbIcon = new MaterialDesignIconView(MaterialDesignIcon.FACEBOOK);
        fbIcon.getStyleClass().add("icon");
        fbIcon.setSize("34");
        btnFacebook.setGraphic(fbIcon);

        MaterialDesignIconView twIcon = new MaterialDesignIconView(MaterialDesignIcon.TWITTER);
        twIcon.getStyleClass().add("icon");
        twIcon.setSize("34");
        btnTwitter.setGraphic(twIcon);

        imageEby.setCursor(Cursor.HAND);
        imageLC.setCursor(Cursor.HAND);
    }

    @FXML
    private void facebookAction(ActionEvent event) {
        LoadStage.goToLink("https://www.facebook.com/ebysofyan.web.id/?ref=hl");
    }



    private void fadeIn() {
        new FadeInLeftTransition(paneParent).play();
    }

    @FXML
    private void goToEbyAction(MouseEvent event) {
        LoadStage.goToLink("http://ebysofyan.web.id/");
    }

    @FXML
    private void goToLCAction(MouseEvent event) {
        LoadStage.goToLink("http://lifecode.co.id/");
    }

    @FXML
    private void twitterAction(ActionEvent event) {
        LoadStage.goToLink("https://twitter.com/Eby_sofyan");
    }

}
