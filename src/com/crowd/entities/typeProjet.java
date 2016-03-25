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
public class typeProjet {
    
    
     private int ID_TYPE	 ;
    private String TYPE ;

    public typeProjet() {
    }

    public typeProjet(int ID_TYPE, String TYPE) {
        this.ID_TYPE = ID_TYPE;
        this.TYPE = TYPE;
    }

    public typeProjet(String TYPE) {
        this.TYPE = TYPE;
    }

    public typeProjet(int ID_TYPE) {
        this.ID_TYPE = ID_TYPE;
    }

    
    
    
    public int getID_TYPE() {
        return ID_TYPE;
    }

    public void setID_TYPE(int ID_TYPE) {
        this.ID_TYPE = ID_TYPE;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    @Override
    public String toString() {
        return TYPE ;
    }
    
    
    
}
