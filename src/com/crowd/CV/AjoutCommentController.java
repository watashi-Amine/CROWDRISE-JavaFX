/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.CV;

import com.crowd.DAO.CommentDAO;
import com.crowd.DAO.MembreDAO;
import com.crowd.DAO.ThreadDAO;
import com.crowd.IDAO.ICommentDAO;
import com.crowd.IDAO.IMembre;
import com.crowd.IDAO.IThreadDAO;
import com.crowd.Util.Singleton;
import com.crowd.entities.Comment;
import com.crowd.entities.Membre;
import com.crowd.entities.Thread;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.eu.tjago.speechfxapp.util.VoiceReaderService;

/**
 * FXML Controller class
 *
 * @author Oussama
 */
public class AjoutCommentController implements Initializable {

    @FXML
    private Label TitreLabel;
    @FXML
    private TextField ScoreTextField;
    @FXML
    private TextArea CommentTextArea;
    @FXML
    private ComboBox<Membre> UserComboBox;
    @FXML
    private ComboBox<Thread> ThemeComboBox;
    @FXML
    private DatePicker dateP;

    @FXML
    private Button AjouterBtn;
    @FXML
    private Button AnnulerBtn;

    private Stage dialogStage;
    private Comment comment;
    private boolean ajouterClicked = false;
    private java.lang.Thread voiceReadingThread;

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
    private void AnnulerAction(ActionEvent event) {
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return ajouterClicked;
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (ScoreTextField.getText() == null || ScoreTextField.getText().length() == 0) {
            errorMessage += "Score Invalide!\n";
        }

        if (CommentTextArea.getText() == null || CommentTextArea.getText().length() == 0) {
            errorMessage += "commentaire invalide!\n";
        }

      
        if (ThemeComboBox.getValue() == null) {
            errorMessage += "Theme invalide!\n";
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
            voiceReadingThread = new java.lang.Thread() {
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

    public void setComment(Comment comment) {

        this.comment = comment;

        //preparation du combobox user
        IMembre imembre = new MembreDAO();
        List<Membre> listmenbre = new ArrayList<Membre>(imembre.findMembre());
        ObservableList<Membre> observableListMembre = FXCollections.observableList(listmenbre);
        //preparation du combobox theme
        ThreadDAO Ith = new ThreadDAO();
        List<Thread> listThread = new ArrayList<Thread>(Ith.findAll());
        ObservableList<Thread> observableListThread = FXCollections.observableList(listThread);

        TitreLabel.setText("Ajouter un commentaire");
       
        ThemeComboBox.setItems(observableListThread);
        ScoreTextField.setPromptText("saisir un numero");
        CommentTextArea.setPromptText("saisir votre commentaire");
        dateP.setValue(LocalDate.now());

    }

    @FXML
    private void handleAjouter(ActionEvent event) throws SQLException {
//
//        IMembre imembre = new MembreDAO();
//        comment.setAuthor_id(Integer.parseInt(String.valueOf(imembre.findMembreByusername(UserComboBox.getValue() + ""))));
//        comment.setThread_id(ThemeComboBox.getValue() + "");
//        comment.setBody(CommentTextArea.getText());
//        comment.setCreated_at(dateP.getValue() + " 00:00:00");
//        comment.setScore(Integer.parseInt(ScoreTextField.getText()));
//        comment.setState(2);
//        comment.setDepth(3);
//        comment.setAncestors("mmm");
//        ICommentDAO comDAO = new CommentDAO();
//        comDAO.addComment(comment);
//        ajouterClicked = true;
//        dialogStage.close();

        if (AjouterBtn.getText() == "Modifier") {
            if (isInputValid()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Modifier !!!");
                alert.setContentText("Etes vous sur de bien vouloir Modifier le commentaire '" + CommentTextArea.getText() + "'");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {

                    IMembre imembre = new MembreDAO();
                    comment.setAuthor_id(Singleton.getInstance().getMembre().getId_membre());
                    comment.setThread_id(ThemeComboBox.getValue() + "");
                    comment.setBody(CommentTextArea.getText());
                    comment.setCreated_at(dateP.getValue() + " 00:00:00");
                    comment.setScore(Integer.parseInt(ScoreTextField.getText()));
                    comment.setState(2);
                    comment.setDepth(3);
                    comment.setAncestors("mmm");
                    ICommentDAO comDAO = new CommentDAO();
                    comDAO.updateComment(comment);
                    ajouterClicked = true;
                    dialogStage.close();

                }
            }

        } else if (isInputValid()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ajout !!!");
            alert.setContentText("Etes vous sur de bien vouloir ajouter le commentaire '" + CommentTextArea.getText() + "'");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                IMembre imembre = new MembreDAO();
                comment.setAuthor_id(Singleton.getInstance().getMembre().getId_membre());
                comment.setThread_id(ThemeComboBox.getValue() + "");
                comment.setBody(CommentTextArea.getText());
                comment.setCreated_at(dateP.getValue() + " 00:00:00");
                comment.setScore(Integer.parseInt(ScoreTextField.getText()));
                comment.setState(2);
                comment.setDepth(3);
                comment.setAncestors("mmm");
                ICommentDAO comDAO = new CommentDAO();
                comDAO.addComment(comment);
                ajouterClicked = true;
                dialogStage.close();

                //       Membre m = new Membre(Singleton.getInstance().getMembre().getId_membre());
                //    System.out.println(Singleton.getInstance().getMembre().getId_membre() + "eeeeeeeee");
            }
        }

    }

    public void setSelectedComment(Comment selectedComment) {
        //SetComment contenu
        this.comment = selectedComment;
        //preparation du combobox user
        IMembre imembre = new MembreDAO();
        List<Membre> listmenbre = new ArrayList<Membre>(imembre.findMembre());
        ObservableList<Membre> observableListMembre = FXCollections.observableList(listmenbre);
        //preparation du combobox theme
        ThreadDAO Ith = new ThreadDAO();
        List<Thread> listThread = new ArrayList<Thread>(Ith.findAll());
        ObservableList<Thread> observableListThread = FXCollections.observableList(listThread);

        TitreLabel.setText("Modifier commentaire");
        AjouterBtn.setText("Modifier");
        UserComboBox.setItems(observableListMembre);
        UserComboBox.setPromptText(comment.getUsername());
        ThemeComboBox.setItems(observableListThread);
        ThemeComboBox.setPromptText(comment.getThread_id());
        ScoreTextField.setText(comment.getScore() + "");
        CommentTextArea.setText(comment.getBody());
        dateP.setPromptText(comment.getCreated_at());

    }

}
