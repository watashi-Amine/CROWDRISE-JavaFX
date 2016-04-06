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
public class Vote {

    private int id, comment_id, value;
    private String created_at;

    public Vote() {
    }

    public Vote(int id, int comment_id, int value, String created_at) {
        this.id = id;
        this.comment_id = comment_id;
        this.value = value;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Vote{"+"id="+id+", comment_id="+comment_id+", value="+value+", created_at="+created_at; //To change body of generated methods, choose Tools | Templates.
    }

}
