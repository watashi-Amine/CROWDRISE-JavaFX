/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.service;

import com.crowd.Util.MyConnexion;
import com.crowd.entities.Membre;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASR1
 */
public class statistique {
       private Connection cnx;

    public statistique() {
        cnx = MyConnexion.getInstance();
    }
    
    
    public ObservableList<String> listerDateNaissance() {

        ObservableList<String> list;
        String requete = "select date_naissance from user";

        try {
            list = FXCollections.observableArrayList();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(requete);
            while (rs.next()) {
    list.add(rs.getString(1));
               

            }

            return list;
        } catch (Exception e) {
            System.out.println("(MembreDAO:fonction lister )erreur lors de l affichage" + e.getMessage());
            return null;
        }

    }
    
}
