/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.entities;

import java.io.File;
import java.util.Date;



/**
 *
 * @author ASR1
 */
public class Membre {
    
    
    private int id_membre;

   
    private String nom;
    private String prenom;
    private String date_naissance;
    private String username;
    private String email;
    private boolean enabled;
    private String salt;
    private String password;
    private String last_login;

    private Boolean isAdministrateur;

    private File userimage;
    private String imagename;
    public Membre() {
    }

    public Membre( String nom, String prenom,String username, String password, String email,String date_naissance,Boolean isAdministrateur,File userimage,String imagename) {
        
        this.nom = nom;
        this.prenom=prenom;
        this.username = username;
        this.password = password;
        this.email = email;
        this.date_naissance=date_naissance;
        this.isAdministrateur = isAdministrateur;
        this.userimage=userimage;
        this.imagename=imagename;
        
    }

    public int getId_membre() {
        return id_membre;
    }

    public void setId_membre(int id_membre) {
        this.id_membre = id_membre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsAdministrateur() {
        return isAdministrateur;
    }

    public void setIsAdministrateur(Boolean isAdministrateur) {
        this.isAdministrateur = isAdministrateur;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public File getUserimage() {
        return userimage;
    }

    public void setUserimage(File userimage) {
        this.userimage = userimage;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    
  
    @Override
    public String toString() {
        return "Membre{" + "id_membre=" + id_membre + ", nom=" + nom + ", username=" + username + ", password=" + password + ", email=" + email + ", isAdministrateur=" + isAdministrateur + '}';
    }
    
    
}
