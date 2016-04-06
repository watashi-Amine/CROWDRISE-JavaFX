/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.CV;

import com.crowd.DAO.ProjetDao;
import com.crowd.IDAO.iprojet;
import com.crowd.Util.Singleton;
import com.crowd.entities.Projet;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author MohamedAmine
 */
public class AccueilController implements Initializable {

    @FXML
    private Label Resume;
    @FXML
    private ProgressBar EvolutioniD;
    @FXML
    private Label Pudgetid;
    @FXML
    private Label Argentid;
    @FXML
    private VBox VBosId;
    @FXML
    private GridPane Grid;
    @FXML
    private AnchorPane AnchorId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void EvolutionAction(MouseEvent event) {
    }

    @FXML
    private void FinancerAction(ActionEvent event) {
    }

    @FXML
    private void nosProjetsAction(ActionEvent event) {

        AnchorId.getChildren().clear();
        
        iprojet ProjetDao = new ProjetDao();
        List<Projet> ListeProjet = new ArrayList<Projet>();
        ListeProjet = ProjetDao.display();
        for (int i = 0; i < 3; i++) {
            for (int j = 1; j < 10; j++) {
                Label l = new Label("Titre");
                l.setFont(new Font("Arial", 30));
                Label l1 = new Label("Resumer");
                l1.setFont(new Font("Arial", 30));
    Image im = new Image("file:///C:\\wamp\\www\\1\\uploadsProjets\\"+ListeProjet.get(j).getImage());
                System.out.println("file:///C:\\wamp\\www\\1\\uploadsProjets\\"+ListeProjet.get(j).getImage());
  ImageView Iv=  new ImageView();
  Iv.setImage(im);
  Iv.setFitWidth(100);
         Iv.setStyle("-fx-padding: 1 1 1 1;");
         Iv.setFitHeight(250);
         Iv.setFitWidth(250);
                System.out.println(ListeProjet.get(j).getImage());
                ProgressBar Pb = new ProgressBar(Math.round(ListeProjet.get(j).getArgent() / ListeProjet.get(j).getBUDJET()));
                Pb.setStyle("  -fx-background-color:  white \n"
                        + "        -fx-box-border,\n"
                        + "        linear-gradient(to bottom, derive(-fx-accent,95%), derive(-fx-accent,10%)),\n"
                        + "        linear-gradient(to bottom, derive(-fx-accent,38%), -fx-accent);\n");

                VBox v = new VBox(l, new Label(ListeProjet.get(j).getNOM_PROJET()),Iv, l1, new Label(ListeProjet.get(j).getRESUME()), new Label("Evolution du financement"), new HBox(new Label(" \n   ")), Pb, new HBox(new Label(" \n   ")), new Button("financer"));
                v.setStyle("-fx-background-color: rgb(255.0, 255.0, 255.0);"
                        + "-fx-background-radius: 4.0;"
                        + "-fx-effect: dropshadow(gaussian, rgb(0.0, 0.0, 0.0, 0.15), 6.0, 0.7, 0.0,1.5);"
                        + "-fx-padding: 16 16 16 16;");
                Grid.add(v, i, j);
                Grid.setHgap(10); //horizontal gap in pixels => that's what you are asking for
                Grid.setVgap(10); //vertical gap in pixels
            }
        }

        AnchorId.getChildren().add(Grid);

    }

    @FXML
    private void nosProblemesAction(ActionEvent event) {
          AnchorId.getChildren().clear();
    }

    @FXML
    private void nosEvenementAction(ActionEvent event) {
          AnchorId.getChildren().clear();
    }

}
