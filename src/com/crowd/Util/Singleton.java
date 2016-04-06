/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.Util;

import com.crowd.entities.Membre;
import com.crowd.entities.Projet;
import java.lang.reflect.Member;
import javafx.scene.control.TextField;

/**
 *
 * @author ASR1
 */

//http://stackoverflow.com/questions/19883563/how-to-get-data-in-one-java-fxml-controller-from-another-fxml-controller-these
public class Singleton {
        private static Singleton instance = new Singleton();
        public static Singleton getInstance(){
            return instance;
        }

        private TextField txtField1;
        private TextField txtField2;
        private Projet projet;
        private Membre membre;

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

        public TextField getTxtField2() {
            return txtField2;
        }

        public void setTxtField2(TextField txtField2) {
           this.txtField2 = txtField2;
        }

        public TextField getTxtField1() {
           return txtField1;
        }

       public void setTxtField1(TextField txtField1) {
           this.txtField1 = txtField1;
       }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

       
       
       
    }
