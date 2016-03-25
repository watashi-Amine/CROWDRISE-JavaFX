/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.entities;

/**
 *
 * @author MohamedAmine
 */
public class categorieProjet {
    
    private int ID_CATEGORIE_PROJET;
      private String CATEGORIE ; 

    public categorieProjet() {
    }

    public categorieProjet(String CATEGORIE) {
        this.CATEGORIE = CATEGORIE;
    }

    public categorieProjet(int ID_CATEGORIE_PROJET) {
        this.ID_CATEGORIE_PROJET = ID_CATEGORIE_PROJET;
    }
    
    

    public categorieProjet(int ID_CATEGORIE_PROJET, String CATEGORIE) {
        this.ID_CATEGORIE_PROJET = ID_CATEGORIE_PROJET;
        this.CATEGORIE = CATEGORIE;
    }

    public int getID_CATEGORIE_PROJET() {
        return ID_CATEGORIE_PROJET;
    }

    public void setID_CATEGORIE_PROJET(int ID_CATEGORIE_PROJET) {
        this.ID_CATEGORIE_PROJET = ID_CATEGORIE_PROJET;
    }

    public String getCATEGORIE() {
        return CATEGORIE;
    }

    public void setCATEGORIE(String CATEGORIE) {
        this.CATEGORIE = CATEGORIE;
    }

    @Override
    public String toString() {
        return  CATEGORIE ;
    }
      
      
      
      
    
}
