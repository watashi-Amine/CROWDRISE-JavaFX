/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.CV;
import com.crowd.about.AboutController;
import com.crowd.DAO.MembreDAO;
import com.crowd.IDAO.IMembre;
import com.crowd.Util.Singleton;
import com.crowd.entities.Membre;
import com.crowd.helper.LoadStage;
import com.crowd.mainform.Main;
import com.crowd.mainform.MainFormController;
import com.sun.java.swing.plaf.windows.resources.windows;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ASR1
 */
public class LoginController implements Initializable {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label inscription;
    @FXML
    private AnchorPane viewPane;
    private LoadStage loadStage;
    Membre membreInfo;
    
        private Window primaryStage;

    public  MainFormController in;
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
           
        // TODO
    }    

    @FXML
    private void onclick(ActionEvent event) throws IOException {
        
        
          Membre membre=new Membre();
        IMembre im=new MembreDAO();
        System.out.println(im.authentification(username.getText(), password.getText()));
        if(im.authentification(username.getText(), password.getText())==true){
//*****pour l'initialisation du nom de profile                  
                membreInfo=im.informationMembre(username.getText());
                 
                Singleton.getInstance().setMembre(membreInfo);
               
//            MF.setListinfomembre(Listinfomembre);
        ((Node)event.getSource()).getScene().getWindow().hide();
                          FXMLLoader loader=new FXMLLoader();
loader.setLocation(Main.class.getResource("/com/crowd/mainform/MainForm.fxml"));
AnchorPane page=(AnchorPane)loader.load();


Stage dialogStage=new Stage();
       
                  // MainFormController MF=(MainFormController)loader.getControllerFactory();
//MF.startUp();
                  
           //Pane root = FXMLLoader.load(getClass().getResource("/com/crowd/mainform/MainForm.fxml"));

           dialogStage.initModality(Modality.WINDOW_MODAL);
           dialogStage.setTitle("h");
           dialogStage.initOwner(primaryStage);
           Scene scene=new Scene(page);
                   dialogStage.setScene(scene);
                   
                   
                   

           MainFormController MF=loader.getController();
           
           MF.setUsernameText(membre.getPrenom());
           
           
//MF.setUsernameText("kkkkk");
//MF.setFf("jsdsf");

           

        dialogStage.show();

        
    }else{
            System.out.println("mot de passe or username incorrect");
        }
    
}

//    @FXML
//    private void btnInscription(MouseEvent event) {
//                loadStage = new LoadStage();
//
//        System.out.println("About Click Success !!!");
//                loadStage.loadAnchorPane(viewPane, "/about/About.fxml");
//    }

    @FXML
    private void btnInscription(MouseEvent event) {
           loadStage = new LoadStage();

       System.out.println("About Click Success !!!");
               loadStage.loadAnchorPane(viewPane, "/CV/Inscription.fxml");
              

    }

}