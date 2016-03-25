/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.CV;

import com.crowd.DAO.MembreDAO;
import com.crowd.IDAO.IMembre;
import com.crowd.animations.FadeInLeftTransition;
import com.crowd.entities.Membre;
import com.crowd.helper.LoadStage;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.kairos.components.MaterialButton;

/**
 * FXML Controller class
 *
 * @author eby
 */
public class InscriptionController implements Initializable {
    
    @FXML
    private AnchorPane paneParent;
 
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private DatePicker date_naissance;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private ImageView imgview;
    
    
     private File mainfile;
    
        private Connection cnx;

    private String imagename;    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        //this.fadeIn();
        //this.startUp();
    }

    private void startUp() {
        
    
        
        /*
        MaterialDesignIconView fbIcon = new MaterialDesignIconView(MaterialDesignIcon.FACEBOOK);
        fbIcon.getStyleClass().add("icon");
        fbIcon.setSize("34");
        btnFacebook.setGraphic(fbIcon);

        MaterialDesignIconView twIcon = new MaterialDesignIconView(MaterialDesignIcon.TWITTER);
        twIcon.getStyleClass().add("icon");
        twIcon.setSize("34");
        btnTwitter.setGraphic(twIcon);
*/
        
        
    }

  

    private void fadeIn() {
        new FadeInLeftTransition(paneParent).play();
    }
     @FXML
    private void btnInscription(ActionEvent event) {
        
            LocalDate ldtString=date_naissance.getValue();
        date_naissance.getValue();
        
Membre m1=new Membre(nom.getText(),prenom.getText(), username.getText(),password.getText(), email.getText(), ldtString.toString(),Boolean.FALSE,mainfile,imagename);
    
IMembre im=new MembreDAO();
      //  upload(event);
        im.add(m1);
        
fadeIn();
        
    }
    
    

  
    @FXML
    private void searchImage(ActionEvent event) {
        
        
        
         FileChooser fileChooser = new FileChooser();
        //Extention filter remove below two comments for extension filter
        FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("Images (.png, .jpg, .bmp)", "*.jpg", "*.png", "*.bmp");
        fileChooser.getExtensionFilters().add(extentionFilter);
        //Set to user directory or go to default if cannot access
        String userDirectoryString = System.getProperty("user.home");
        File userDirectory = new File(userDirectoryString);
        if (!userDirectory.canRead()) {
            userDirectory = new File("c:/");
        }
        fileChooser.setInitialDirectory(userDirectory);

        //Choose the file
        File chosenFile = fileChooser.showOpenDialog(null);
        //Make sure a file was selected, if not return default
        String path;
        if (chosenFile != null) {
            path = chosenFile.getPath();
            File file = new File(path);
            mainfile = chosenFile;
           
           
            
            
            
            //to set image in image view
            Image image = new Image(file.toURI().toString());
            imgview.setImage(image);
            System.out.println(chosenFile.getName().toString());
            
            
            
            
            
            imagename=chosenFile.getName().toString();//utilisable lel outputRead buffer
        } else {
            //default return value
            path = null;
        }
    }

  
  
   

}
