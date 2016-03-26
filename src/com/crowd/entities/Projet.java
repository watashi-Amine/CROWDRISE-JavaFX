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
public class Projet {
     private int 	ID_PROJET;
     private int 	ID_Cat;
     private int 	ID_Type;
      private String 	NOM_PROJET ; 
       private String 	RESUME ; 
   private double BUDJET;
private double	argent;
      private String FICHIER ; 
      
   private String image	;     

 private int id ; 

   private typeProjet type;
      private categorieProjet CATEGORIE ; 
      
        public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }
 private Membre membre;
  public Projet(String NOM_PROJET, String RESUME, double BUDJET, double argent,typeProjet type, categorieProjet CATEGORIE) {
        this.NOM_PROJET = NOM_PROJET;
        this.RESUME = RESUME;
        this.BUDJET = BUDJET;
        this.argent = argent;
            this.type = type;
        this.CATEGORIE = CATEGORIE;
    }
    public Projet(String NOM_PROJET, String RESUME, double BUDJET, double argent, String FICHIER, String image, int id, typeProjet type, categorieProjet CATEGORIE) {
        this.NOM_PROJET = NOM_PROJET;
        this.RESUME = RESUME;
        this.BUDJET = BUDJET;
        this.argent = argent;
        this.FICHIER = FICHIER;
        this.image = image;
        this.id = id;
        this.type = type;
        this.CATEGORIE = CATEGORIE;
    }
      
      
      
      

    public Projet() {
    }

    public Projet(String NOM_PROJET, String RESUME, double BUDJET, typeProjet type, categorieProjet CATEGORIE) {
        this.NOM_PROJET = NOM_PROJET;
        this.RESUME = RESUME;
        this.BUDJET = BUDJET;
        this.type = type;
        this.CATEGORIE = CATEGORIE;
    }

    public Projet(int ID_PROJET, String NOM_PROJET, String RESUME, double BUDJET, double argent, String FICHIER, String image, int id, typeProjet type, categorieProjet CATEGORIE) {
        this.ID_PROJET = ID_PROJET;
        this.NOM_PROJET = NOM_PROJET;
        this.RESUME = RESUME;
        this.BUDJET = BUDJET;
        this.argent = argent;
        this.FICHIER = FICHIER;
        this.image = image;
        this.id = id;
        this.type = type;
        this.CATEGORIE = CATEGORIE;
    }

    public int getID_Cat() {
        return ID_Cat;
    }

    public void setID_Cat(int ID_Cat) {
        this.ID_Cat = ID_Cat;
    }

    public int getID_Type() {
        return ID_Type;
    }

    public void setID_Type(int ID_Type) {
        this.ID_Type = ID_Type;
    }

    public int getID_PROJET() {
        return ID_PROJET;
    }

    public void setID_PROJET(int ID_PROJET) {
        this.ID_PROJET = ID_PROJET;
    }

    public String getNOM_PROJET() {
        return NOM_PROJET;
    }

    public void setNOM_PROJET(String NOM_PROJET) {
        this.NOM_PROJET = NOM_PROJET;
    }

    public String getRESUME() {
        return RESUME;
    }

    public void setRESUME(String RESUME) {
        this.RESUME = RESUME;
    }

    public double getBUDJET() {
        return BUDJET;
    }

    public void setBUDJET(double BUDJET) {
        this.BUDJET = BUDJET;
    }

    public double getArgent() {
        return argent;
    }

    public void setArgent(double argent) {
        this.argent = argent;
    }

    public String getFICHIER() {
        return FICHIER;
    }

    public void setFICHIER(String FICHIER) {
        this.FICHIER = FICHIER;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public typeProjet getType() {
        return type;
    }

    public void setType(typeProjet type) {
        this.type = type;
    }

    public categorieProjet getCATEGORIE() {
        return CATEGORIE;
    }

    public void setCATEGORIE(categorieProjet CATEGORIE) {
        this.CATEGORIE = CATEGORIE;
    }

    @Override
    public String toString() {
        return "Projet{" + "ID_PROJET=" + ID_PROJET + ", ID_Cat=" + ID_Cat + ", ID_Type=" + ID_Type + ", NOM_PROJET=" + NOM_PROJET + ", RESUME=" + RESUME + ", BUDJET=" + BUDJET + ", argent=" + argent + ", FICHIER=" + FICHIER + ", image=" + image + ", id=" + id + ", type=" + type + ", CATEGORIE=" + CATEGORIE + '}';
    }

    

  
    
    
    
}
