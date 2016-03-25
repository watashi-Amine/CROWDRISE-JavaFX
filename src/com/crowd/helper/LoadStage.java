package com.crowd.helper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;

public class LoadStage {

    public LoadStage() {
    }

    public static void dialog(Alert.AlertType alertType, String s) {
        Alert alert = new Alert(alertType, s);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Info");
        alert.showAndWait();
    }

    public void loadAnchorPane(AnchorPane ap, String resource) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/com/crowd" + resource));
            ap.getChildren().setAll(p);
        } catch (IOException e) {
        }
    }

    public static void setModelColumn(TableColumn tb, String a) {
        tb.setCellValueFactory(new PropertyValueFactory(a));
    }
    
    public static void goToLink(String url) {
        String os = System.getProperty("os.name").toLowerCase();
        Runtime rt = Runtime.getRuntime();
 
        try {
 
            if (os.indexOf("win") >= 0) {
                rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if (os.indexOf("mac") >= 0) {
                rt.exec("open " + url);
            } else if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0) {
                String[] browsers = {"epiphany", "chromium-browser", "chrome", 
                "google chrome", "firefox", "mozilla", "konqueror",
                    "netscape", "opera", "links", "lynx"};
                StringBuffer cmd = new StringBuffer();
                for (int i = 0; i < browsers.length; i++) {
                    cmd.append((i == 0 ? "" : " || ") + browsers[i] 
                              + " \"" + url + "\" ");
                }
                rt.exec(new String[]{"sh", "-c", cmd.toString()});
            } else {
                return;
            }
        } catch (Exception e) {
            return;
        }
        return;
    }
}
