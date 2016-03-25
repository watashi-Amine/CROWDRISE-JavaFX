/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.IDAO;

import com.crowd.entities.Membre;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author ASR1
 */
public interface IMembre {
    
    
        public void add(Membre d);
        public void  updateMembre(Membre membre);
        public void removeMembreyId(Membre membre);
        public Membre findMembreById(int id);
        ObservableList<Membre> findMembreByAnyCritere(String recherche);

        ObservableList<Membre> listerMembre();
       public Membre informationMembre(String username);

        public boolean authentification(String username,String password);
        public String selectrole(int id);
        
        

}