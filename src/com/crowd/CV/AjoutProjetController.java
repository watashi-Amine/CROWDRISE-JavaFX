/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.CV;


import com.crowd.DAO.*;
import com.crowd.entities.*;
import com.crowd.IDAO.*;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import main.java.eu.tjago.speechfxapp.util.VoiceReaderService;

/**
 * FXML Controller class
 *
 * @author MohamedAmine
 */
public class AjoutProjetController implements Initializable {

    @FXML
    private Label Titre;
    @FXML
    private Label Resume;
    @FXML
    private Label Budget;
    @FXML
    private TextField TitreF;
    @FXML
    private TextArea ResumeF;
    @FXML
    private TextField BudgetF;
    @FXML
    private ComboBox<typeProjet> TypeF;
    @FXML
    private ComboBox<categorieProjet> CategorieF;
    @FXML
    private Button AjouterBtn;
    @FXML
    private Button AnnulerBtn;
    private Projet Projet;
    private categorieProjet categorieProjet;
    private typeProjet typeProjet;
    @FXML
    private Label AjoutProjetlabel;
    private Stage dialogStage;
    private boolean ajouterClicked = false;
    private Thread voiceReadingThread;
    private String AbsolutePath;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    @FXML
    private void AjoutAction(ActionEvent event) throws SQLException {

        if (AjouterBtn.getText() == "Modifier") {
            if (isInputValid()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Modifier !!!");
                alert.setContentText("Etes vous sur de bien vouloir Modifier le Projet '" + TitreF.getText() + "'");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    icategorie icat = new categorieProjetDao();
                    categorieProjet C = new categorieProjet();
                    C = icat.findcategorieProjetByName(CategorieF.getValue().toString());

                    itype itype = new typeProjetDao();
                    typeProjet T = new typeProjet();
                    T = itype.findtypeProjetByName(TypeF.getValue().toString());

                    Projet.setNOM_PROJET(TitreF.getText());
                    Projet.setRESUME(ResumeF.getText());
                    Projet.setBUDJET(Double.parseDouble(BudgetF.getText()));
                    Projet.setType(T);
                    Projet.setCATEGORIE(C);

                    iprojet iproj = new ProjetDao();
                    System.out.println(iproj.update(Projet, Projet.getID_PROJET()));
                    System.out.println(Projet.getID_PROJET());

                    ajouterClicked = true;
                    dialogStage.close();
                }
            }

        } else if (isInputValid()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ajout !!!");
            alert.setContentText("Etes vous sur de bien vouloir ajouter le Projet '" + TitreF.getText() + "'");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                icategorie icat = new categorieProjetDao();
                categorieProjet C = new categorieProjet();
                C = icat.findcategorieProjetByName(CategorieF.getValue().toString());

                itype itype = new typeProjetDao();
                typeProjet T = new typeProjet();
                T = itype.findtypeProjetByName(TypeF.getValue().toString());

                Projet.setNOM_PROJET(TitreF.getText());
                Projet.setRESUME(ResumeF.getText());
                Projet.setBUDJET(Double.parseDouble(BudgetF.getText()));
                Projet.setType(T);
                Projet.setCATEGORIE(C);
               Projet.setFICHIER(AbsolutePath);
                iprojet iproj = new ProjetDao();
                iproj.add(Projet);
                ajouterClicked = true;
                dialogStage.close();
            }
        }

    }

    @FXML
    private void AnnulerAction(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    private void TypeFAction(ActionEvent event) {
    }

    @FXML
    private void CategorieFAction(ActionEvent event) {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return ajouterClicked;
    }

    public void setProjet(Projet Projet) {

        //categorieProjet preparation CB
        icategorie icat = new categorieProjetDao();
        List<categorieProjet> list = new ArrayList<categorieProjet>(icat.findcategorieProjet());
        ObservableList<categorieProjet> observableList = FXCollections.observableList(list);

        //typeProjet preparation CB
        itype itype = new typeProjetDao();

        List<typeProjet> list1 = new ArrayList<typeProjet>(itype.findtypeProjet());
        ObservableList<typeProjet> observableList1 = FXCollections.observableList(list1);

        //Setprojet contenu
        this.Projet = Projet;
        AjoutProjetlabel.setText("Ajout nouveau Projet");

        CategorieF.setItems(observableList);
        TypeF.setItems(observableList1);
        TitreF.setPromptText("saisir titre");
        ResumeF.setPromptText("decrire le contenu du projet ...");
        BudgetF.setPromptText("montant en DTN ");

    }

    public void setSelectedProjet(Projet selectedProjet) {

        //categorieProjet preparation CB
        icategorie icat = new categorieProjetDao();
        List<categorieProjet> list = new ArrayList<categorieProjet>(icat.findcategorieProjet());
        ObservableList<categorieProjet> observableList = FXCollections.observableList(list);

        //typeProjet preparation CB
        itype itype = new typeProjetDao();

        List<typeProjet> list1 = new ArrayList<typeProjet>(itype.findtypeProjet());
        ObservableList<typeProjet> observableList1 = FXCollections.observableList(list1);

        //Setprojet contenu
        this.Projet = selectedProjet;
        AjoutProjetlabel.setText("Modifier Projet");

        CategorieF.setItems(observableList);
        TypeF.setItems(observableList1);
        TitreF.setText(Projet.getNOM_PROJET());
        ResumeF.setText(Projet.getRESUME());
        CategorieF.setValue(Projet.getCATEGORIE());
        TypeF.setValue(Projet.getType());

        BudgetF.setText(Double.toString(Projet.getBUDJET()));
        AjouterBtn.setText("Modifier");

    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (TitreF.getText() == null || TitreF.getText().length() == 0) {
            errorMessage += "Titre Projet Invalide!\n";
        }

        if (ResumeF.getText() == null || ResumeF.getText().length() == 0) {
            errorMessage += "Description invalide!\n";
        }

        if (BudgetF.getText() == null || !isNumeric(BudgetF.getText())) {
            errorMessage += "Budget invalide!\n";
        }
        if (TypeF.getValue() == null) {
            errorMessage += "Type invalide!\n";
        }
        if (CategorieF.getValue() == null) {
            errorMessage += "Categrie invalide!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Champs Invalide");
            alert.setHeaderText("Veuillez verifier les champs ci dessous");
            alert.setContentText(errorMessage);
            /*
                **
                **   Message d'acueil
                **
             */
            voiceReadingThread = new Thread() {
                public void run() {
                    VoiceReaderService voiceService = new VoiceReaderService();
                    voiceService.setVoice("kevin");
                    voiceService.setText("please look at the following area");
                    voiceService.read();
                }
            };
            voiceReadingThread.start();

            /*
                **
                **   end Message d'acueil
                **
             */
            alert.showAndWait();

            return false;
        }

    }

    @FXML
    private void UploafAction(ActionEvent event) {

        /*
                **
                **   Upload Code
                **
         */
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selection = chooser.getSelectedFile();
            System.out.println(selection.getAbsolutePath());
            this.AbsolutePath = selection.getAbsolutePath();
            System.out.println(AbsolutePath);
            /*
                **
                **  end  Upload Code
                **
             */

        }

    }

}
