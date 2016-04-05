/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.CV;

import com.crowd.DAO.ProjetDao;
import com.crowd.IDAO.iprojet;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author araga
 */
public class ProjetStatiqtiqueFXMLController implements Initializable {

     private Stage dialogStage;
    private boolean ajouterClicked = false;
    
    private Stage Stage;
    @FXML
    private AnchorPane Anchorid;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    
    public void  setStat()
    {
        
              
        
    }
    
     public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return ajouterClicked;
    }

    @FXML
    private void A(ActionEvent event) {
        
         Anchorid.getChildren().clear();
        
          // Création du graphique. 
        final PieChart chart = new PieChart();
        chart.setPrefSize(800, 800);
            chart.setStyle("-fx-padding:50px;");
        iprojet iproj = new ProjetDao();

        chart.setTitle("Projet parCategorie ");
        chart.getData().setAll(new PieChart.Data("Web Design", iproj.findByCat("Web Design").size()), new PieChart.Data("Web Developpement", iproj.findByCat("Web Developpement").size()),
                new PieChart.Data("Sport", iproj.findByCat("Sport").size()), new PieChart.Data("Nature et envirenement", iproj.findByCat("Nature et envirenement").size()),
                new PieChart.Data("reparation  informatique", iproj.findByCat("reparation  informatique").size()), new PieChart.Data("Mobile Developement", iproj.findByCat("Mobile Developement").size()),
                new PieChart.Data("Bricolage", iproj.findByCat("Bricolage").size()), new PieChart.Data("nouvelle technologie", iproj.findByCat("nouvelle technologie").size()),
                new PieChart.Data("Divertissement", iproj.findByCat("Divertissement").size()), new PieChart.Data("humanitaire", iproj.findByCat("humanitaire").size())
        );
        // Montage de l'IU. 
        
        Anchorid.getChildren().add(chart);
        
       

        final Label caption = new Label("");
        caption.setTextFill(Color.BLACK);
        caption.setStyle("-fx-font: 24 arial;");

        for (PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());
                    caption.setText(String.valueOf(data.getPieValue()) + "%");
                }
            });

        }

    }

    @FXML
    private void B(ActionEvent event) {
       
       Anchorid.getChildren().clear();
       
          iprojet iproj = new ProjetDao();
         final List<BarChart.Series> seriesList = new LinkedList<>(); 
        final String[] categoriesNames = {""}; 
        final String[] seriesNames = {"Web Design", "Web Developpement", "Sport","Nature et envirenement","reparation  informatique", "Mobile Developement","Bricolage", "nouvelle technologie", "Divertissement","humanitaire"}; 
        final int[][] allValues = { 
            {iproj.findByCat("Web Design").size()},
            { iproj.findByCat("Web Developpement").size()}, 
            { iproj.findByCat("Sport").size()},
            { iproj.findByCat("Nature et envirenement").size()},
            { iproj.findByCat("reparation  informatique").size()},
            { iproj.findByCat("Mobile Developement").size()},
            { iproj.findByCat("Bricolage").size()}, 
            { iproj.findByCat("nouvelle technologie").size()},
            {  iproj.findByCat("Divertissement").size()},
            {  iproj.findByCat("humanitaire").size()}}; 
        final double minY = 0; 
        double maxY = -Double.MAX_VALUE; 
        for (int seriesIndex = 0; seriesIndex < seriesNames.length; seriesIndex++) { 
            final BarChart.Series series = new BarChart.Series<>(); 
            series.setName(seriesNames[seriesIndex]); 
            final int[] values = allValues[seriesIndex]; 
            for (int categoryIndex = 0; categoryIndex < categoriesNames.length; categoryIndex++) { 
                final int value = values[categoryIndex]; 
                final String category = categoriesNames[categoryIndex]; 
                maxY = Math.max(maxY, value); 
                final BarChart.Data data = new BarChart.Data(category, value); 
                series.getData().add(data); 
            } 
            seriesList.add(series); 
        } 
        // Création du graphique. 
        final CategoryAxis xAxis = new CategoryAxis(); 
        xAxis.getCategories().setAll(categoriesNames); 
        xAxis.setLabel(" "); 
        final NumberAxis yAxis = new NumberAxis(minY, maxY, 50); 
        yAxis.setLabel("Nombre de Projets"); 
        final BarChart chart = new BarChart(xAxis, yAxis); 
         chart.setPrefSize(800, 800);
          chart.setStyle("-fx-padding:50px;");
        chart.setTitle("Projet Par Categorie"); 
        chart.getData().setAll(seriesList); 
        // Montage de l'IU. 
       
       Anchorid.getChildren().add(chart);
      
    }

    
    
    
}
