/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.CV;

import com.crowd.DAO.*;
import com.crowd.entities.*;
import com.crowd.IDAO.*;
import com.crowd.mainform.Main;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import main.java.eu.tjago.speechfxapp.util.VoiceReaderService;
import org.kairos.components.MaterialButton;

/**
 * FXML Controller class
 *
 * @author MohamedAmine
 */
public class ProjetsController implements Initializable {

    
    
    private Main mainApp;
    @FXML
    private TableColumn<Projet, String> TitreProjetList;
    @FXML
    private TableColumn<Projet, Double> BudgetList;
    @FXML
    private Label TitreProjetLabel;
    @FXML
    private Label ResumeLabel;
    @FXML
    private Label BudgetLabel;
    @FXML
    private Label MontantLabel;
    @FXML
    private Label TypeLabel;
    @FXML
    private Label CattegorieLabel;
    @FXML
    private Button AjouterBtn;
    @FXML
    private TableView<Projet> ProjetTableView;
      private Window primaryStage;
    private ObservableList<Projet> ProjetData = FXCollections.observableArrayList();
       private Thread voiceReadingThread;
        iprojet ProjetDao = new ProjetDao();
    @FXML
    private TextField cherchF;
    private Button lireBtn;
    @FXML
    private Button XBtn;
    @FXML
    private MaterialButton OBtn;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      cherchF.setPromptText("chercher projet");
        

// TODO
          TitreProjetList.setCellValueFactory(new PropertyValueFactory<Projet, String>("NOM_PROJET"));
        BudgetList.setCellValueFactory(new PropertyValueFactory<Projet, Double>("BUDJET"));

        try {
            //        ProjetTableView.getSelectionModel().selectedItemProperty().addListener(
// s               (observable, oldValue, newValue) -> showDetails(newValue));

showProjetDetails(null);
        } catch (SQLException ex) {
            Logger.getLogger(ProjetsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ProjetTableView.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
          try {
              showProjetDetails(newValue);
          } catch (SQLException ex) {
              Logger.getLogger(ProjetsController.class.getName()).log(Level.SEVERE, null, ex);
          }
      });
        
      
    }    

    private void ModifierAction(ActionEvent event) {
        
        Projet selectedProjet = ProjetTableView.getSelectionModel().getSelectedItem();
      
    if (selectedProjet != null) {
        boolean okClicked = showProjetSelectionnerDetails(selectedProjet);
        if (okClicked) {
            showProjetSelectionnerDetails(selectedProjet);
                  
        }

    } else {
        // Nothing selected.
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Pas de choix");
        alert.setHeaderText("Aucun projet sélectionné");
        alert.setContentText("S'il vous plaît sélectionner un projet dans le tableau.");

        alert.showAndWait();
    }
     
    }

    @FXML
    private void AjouterAction(ActionEvent event) {
      
        
      
        Projet tempProjet = new Projet();
    boolean okClicked = showProjetAddDialog(tempProjet);
    if (okClicked) {
       
        ProjetData.add(tempProjet);
    }
        
        
        
        
        
    }

    private void SupprimerAction(ActionEvent event) {
        
        int selectedIndex = ProjetTableView.getSelectionModel().getSelectedIndex();
        Projet Projet = new Projet();
         Projet=ProjetTableView.getSelectionModel().getSelectedItem();
        
        
        
    if (selectedIndex >= 0) {
        
           Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Suppression !!!");
            alert.setHeaderText("Etes-vous sur de bien vouloir supprimer '"+Projet.getNOM_PROJET()+"'");
         Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                ProjetTableView.getItems().remove(selectedIndex);
               
                ProjetDao.supprimerProjjet(Projet.getID_PROJET());
                
              
            }
        
        ProjetTableView.getItems().remove(selectedIndex);
        
        
        
        
    } else {
        // Nothing selected.
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Erreur de selection");
        alert.setHeaderText("Selection vide");
        alert.setContentText("Veuillez saisir un projet dans la liste ...");

        alert.showAndWait();
    }
        
        
        
        
    }
      /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
      ProjetData.addAll(ProjetDao.display());
        ProjetTableView.setItems(ProjetData);
     
    }
    
    
    
    
    public void showProjetDetails(Projet Projet) throws SQLException {
         if (Projet != null) {
        TitreProjetLabel.setText(Projet.getNOM_PROJET());
        ResumeLabel.setText(Projet.getRESUME());
        BudgetLabel.setText(Double.toString( Projet.getBUDJET()));
        MontantLabel.setText(Double.toString( Projet.getArgent()));
                itype itype = new typeProjetDao();
 icategorie icat = new categorieProjetDao();
        TypeLabel.setText( itype.findtypeProjetByid(Projet.getID_Type()).toString());
        CattegorieLabel.setText(icat.findcategorieProjetById(Projet.getID_Cat()).toString());
        
        
        
        
            
        
        
        
          
 } else {
             
           TitreProjetLabel.setText("non saisie");
        ResumeLabel.setText("non saisie");
        BudgetLabel.setText("non saisie");
        MontantLabel.setText("non saisie");
        TypeLabel.setText("non saisie");
        CattegorieLabel.setText("non saisie");   
        
        
        
                voiceReadingThread = new Thread () {
            public void run() {
                VoiceReaderService voiceService = new VoiceReaderService();
                voiceService.setVoice( "kevin");
                voiceService.setText("Welcm to Crowd Rise Application");
                voiceService.read();
            }
        };
        voiceReadingThread.start();
      
      
         XBtn.setDisable(false);
            
             
         
        
        
            
         }
         
         
         
         
         
         
    }

    
     @FXML
    private void XlireAction(ActionEvent event) {
         //voiceReadingThread.stop();
         if (voiceReadingThread.isAlive()) {
            
             voiceReadingThread.interrupt();
             voiceReadingThread.destroy();
         }
         
        System.out.println("Stopping thread.");
        
        XBtn.setDisable(true);
        OBtn.setDisable(false);
       
    }
     
     
     
    
     public boolean showProjetAddDialog(Projet Projet) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/com/crowd/CV/AjoutProjetOverview.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ajouter un Projet");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the projets into the controller.
            AjoutProjetController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProjet(Projet);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
     
     
     public boolean  showProjetSelectionnerDetails(Projet Projet){
         
          try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/com/crowd/CV/AjoutProjetOverview.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ajouter un Projet");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the cours into the controller.
            AjoutProjetController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setSelectedProjet(Projet);
            

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
         
         
         
     }

    @FXML
    private void cherchAction(ActionEvent event) {
//        
//        
//       
//        if (cherchF.getText() == null || cherchF.getText().length() == 0)
//        {
//            Alert alert = new Alert(AlertType.WARNING);
//        alert.initOwner(mainApp.getPrimaryStage());
//        alert.setTitle("recherche vide");
//        alert.setHeaderText("aucun saisie detecter");
//        alert.setContentText("S'il vous plaît saisissez un titre pour la recherche.");
//
//        alert.showAndWait();
//            
//        }
//        
//        
//        else
//        {
//               ProjetData.removeAll(ProjetData);
//               ProjetData.addAll(ProjetDao.findByNOM_PROJET(cherchF.getText()));
//            
//               ProjetTableView.setItems(ProjetData);
//           
//            
//            
//               
//        }
        
        
        
        
        
        
     
        
        
        
        
        
        
    }

    @FXML
    private void ActualiserAction(ActionEvent event) {
        
           ProjetData.removeAll(ProjetData);
               ProjetData.addAll(ProjetDao.display());
            
               ProjetTableView.setItems(ProjetData);
           
        
        
        
    }

    @FXML
    private void OlireAction(ActionEvent event) {
     Projet Projet =    ProjetTableView.getSelectionModel().getSelectedItem();
     
     
     if (OBtn.isArmed())
     {
         
         
     
         voiceReadingThread = new Thread () {
            public void run() {
                String tair =". . . . . . . . . . . . . . . . . . . . . . . . . . . ";
                VoiceReaderService voiceService = new VoiceReaderService();
                voiceService.setVoice( "kevin");
                voiceService.setText("The Resume of  "+tair +""+Projet.getNOM_PROJET()+tair +"is "+tair +Projet.getRESUME());
               
                voiceService.read();
            }
        };
        voiceReadingThread.start();
      
      
         XBtn.setDisable(false);
         OBtn.setDisable(true);
           
         
     }
     
     
     
    }

    @FXML
    private void cherchAction(KeyEvent event) {
        
        
        
               ProjetData.removeAll(ProjetData);
               ProjetData.addAll(ProjetDao.findByNOM_PROJET(cherchF.getText()));
            
               ProjetTableView.setItems(ProjetData);
           
        
        
    }

    

   
     
     
     
}
