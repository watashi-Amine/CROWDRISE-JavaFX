/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.entities;

/**
 *
 * @author Oussama
 */
public class Solution {

    private int idsolution, idprobleme,author_id;
    private String description, fichier, duree, budjets;

    public Solution() {
    }

    public Solution(int idsolution, int idprobleme, int author_id, String description, String fichier, String duree, String budjets) {
        this.idsolution = idsolution;
        this.idprobleme = idprobleme;
        this.author_id = author_id;
        this.description = description;
        this.fichier = fichier;
        this.duree = duree;
        this.budjets = budjets;
    }

    public int getIdsolution() {
        return idsolution;
    }

    public void setIdsolution(int idsolution) {
        this.idsolution = idsolution;
    }

    public int getIdprobleme() {
        return idprobleme;
    }

    public void setIdprobleme(int idprobleme) {
        this.idprobleme = idprobleme;
    }


    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFichier() {
        return fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getBudjets() {
        return budjets;
    }

    public void setBudjets(String budjets) {
        this.budjets = budjets;
    }

    @Override
    public String toString() {
         return "Solution{"+"idsolution="+idsolution+", idprobleme="+idprobleme+", author_id="+
                 author_id+", description="+description+", author_id="+author_id+", fichier="+fichier
                 +", duree="+duree+", budjets="+budjets;
    
    }

}
