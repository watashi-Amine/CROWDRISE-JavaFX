/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.CV;

import com.crowd.DAO.CommentDAO;
import com.crowd.DAO.MembreDAO;
import com.crowd.IDAO.ICommentDAO;
import com.crowd.IDAO.IMembre;
import com.crowd.Util.Singleton;
import com.crowd.entities.Comment;
import com.crowd.mainform.Main;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.java.eu.tjago.speechfxapp.util.VoiceReaderService;
import org.kairos.components.MaterialButton;

/**
 * FXML Controller class
 *
 * @author Oussama
 */
public class CommantaireController implements Initializable {

    private Main mainApp;
    @FXML
    private TableColumn<Comment, String> utilisateurList;
    @FXML
    private TableColumn<Comment, String> CommantairetList;
    @FXML
    private Label utilisateurLabel;
    @FXML
    private Label CommantaireLabel;
    @FXML
    private Label ThemeLabel;
    @FXML
    private Label DateLabel;
    @FXML
    private Label VoteLabel;

    @FXML
    private Button ModifierBnt;
    @FXML
    private Button AjouterBtn;
    @FXML
    private Button SupprimerBtn;
    @FXML
    private TableView<Comment> commentTableView;
    private Window primaryStage;
    ICommentDAO commentDao = new CommentDAO();

    private final ObservableList<Comment> commentData = commentDao.findAlluser();
    private Thread voiceReadingThread;
    @FXML
    private TextField cherchF;
    @FXML
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
        cherchF.setPromptText("chercher commentaire");
        System.out.println(commentDao.findAll());
// TODO
        utilisateurList.setCellValueFactory(new PropertyValueFactory<Comment, String>("username"));
        CommantairetList.setCellValueFactory(new PropertyValueFactory<Comment, String>("body"));
        commentTableView.setItems(commentData);

        try {
            //        ProjetTableView.getSelectionModel().selectedItemProperty().addListener(
// s               (observable, oldValue, newValue) -> showDetails(newValue));

            showCommentDetails(null);
        } catch (SQLException ex) {
            Logger.getLogger(CommantaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
        commentTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    try {
                        showCommentDetails(newValue);
                    } catch (SQLException ex) {
                        Logger.getLogger(CommantaireController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

    }

    @FXML
    private void ModifierAction(ActionEvent event) {

        Comment selectedComment = commentTableView.getSelectionModel().getSelectedItem();

        if (selectedComment != null) {
            boolean okClicked = showCommentSelectionnerDetails(selectedComment);
            if (okClicked) {
                showCommentSelectionnerDetails(selectedComment);

            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Pas de choix");
            alert.setHeaderText("Aucun projet sélectionné");
            alert.setContentText("S'il vous plaît sélectionner un Commantaire dans le tableau.");
            alert.showAndWait();
        }

    }

    @FXML
    private void AjouterAction(ActionEvent event) {

        Comment tempcomm = new Comment();
        boolean okClicked = showCommAddDialog(tempcomm);
        if (okClicked) {

            commentData.add(tempcomm);
        }

    }

    public boolean showCommAddDialog(Comment comm) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/com/crowd/CV/AjoutComment.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ajouter un commantaire");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the projets into the controller.
            AjoutCommentController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setComment(comm);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void SupprimerAction(ActionEvent event) {

        int selectedIndex = commentTableView.getSelectionModel().getSelectedIndex();
        Comment comm = new Comment();
        comm = commentTableView.getSelectionModel().getSelectedItem();

        if (selectedIndex >= 0) {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Suppression !!!");
            alert.setHeaderText("Etes-vous sur de bien vouloir supprimer '" + comm.getBody() + "'");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                commentTableView.getItems().remove(selectedIndex);

                commentDao.removeCommentById(comm.getId());

            }

            commentTableView.getItems().remove(selectedIndex);

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
        commentData.addAll(commentDao.findAll());

        commentTableView.setItems(commentData);

    }

    public void showCommentDetails(Comment comm) throws SQLException {
        if (comm != null) {
            IMembre m = new MembreDAO();
            utilisateurLabel.setText(comm.getUsername());
            CommantaireLabel.setText(comm.getBody());
            ThemeLabel.setText(comm.getThread_id());
            DateLabel.setText(comm.getCreated_at());
            VoteLabel.setText(comm.getScore() + "");
        } else {

            utilisateurLabel.setText("non saisie");
            CommantaireLabel.setText("non saisie");
            ThemeLabel.setText("non saisie");
            DateLabel.setText("non saisie");
            VoteLabel.setText("non saisie");

            voiceReadingThread = new Thread() {
                @Override
                public void run() {
                    VoiceReaderService voiceService = new VoiceReaderService();
                    voiceService.setVoice("kevin");
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

    public boolean showCommentSelectionnerDetails(Comment comm) {

        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/com/crowd/CV/AjoutComment.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Modifier un Commantaire");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the cours into the controller.
            AjoutCommentController controller = loader.getController();
            controller.setDialogStage(dialogStage);
       //     controller.setComment(comm);
            controller.setSelectedComment(comm);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            return false;
        }

    }

    @FXML
    private void cherchAction(ActionEvent event) {

        if (cherchF.getText() == null || cherchF.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("recherche vide");
            alert.setHeaderText("aucun saisie detecter");
            alert.setContentText("S'il vous plaît saisissez un commentaire pour la recherche.");

            alert.showAndWait();

        } else {
            commentData.removeAll(commentData);
            commentData.addAll(commentDao.findCommentByBody(cherchF.getText()));

            commentTableView.setItems(commentData);

        }

    }

    @FXML
    private void ActualiserAction(ActionEvent event) {

        commentData.removeAll(commentData);
        commentData.addAll(commentDao.findCommentByAuthorId(Singleton.getInstance().getMembre().getId_membre()));

        commentTableView.setItems(commentData);

    }

    @FXML
    private void OlireAction(ActionEvent event) {
        Comment Comment = commentTableView.getSelectionModel().getSelectedItem();

        if (OBtn.isArmed()) {

            voiceReadingThread = new Thread() {
                @Override
                public void run() {
                    String tair = ". . . . . . . . . . . . . . . . . . . . . . . . . . . ";
                    VoiceReaderService voiceService = new VoiceReaderService();
                    voiceService.setVoice("kevin");
                    voiceService.setText("The User " + tair + "" + Comment.getUsername()+ tair + " Say in his comment" + tair + Comment.getBody());

                    voiceService.read();
                }
            };
            voiceReadingThread.start();

            XBtn.setDisable(false);
            OBtn.setDisable(true);

        }

    }

}
