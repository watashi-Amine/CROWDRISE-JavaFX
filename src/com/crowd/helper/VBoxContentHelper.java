package com.crowd.helper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import application.ActionBar;
import application.MaterialText;
import application.TabTitle;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author eby
 */
public class VBoxContentHelper {

    public static void propHelper(String value, MaterialDesignIconView icon, VBox vBox, EventHandler event) {
        MaterialText text = new MaterialText(value);
        text.setTextAlignment(TextAlignment.RIGHT);
        text.getStyleClass().add("material-text");
        icon.setSize("30");
        icon.setStyleClass("icon");
        HBox hBox = new HBox();
        hBox.setSpacing(30);
        hBox.getChildren().addAll(icon, text);
        hBox.setOnMouseClicked(event);
        TabTitle tabTitle = new TabTitle(hBox);
        tabTitle.setPrefSize(300, 50);
        tabTitle.setPadding(new Insets(20));
        tabTitle.setTextFill(Color.WHITE);
        tabTitle.setAlignment(Pos.CENTER_LEFT);

        vBox.getChildren().add(tabTitle);
    }
}
