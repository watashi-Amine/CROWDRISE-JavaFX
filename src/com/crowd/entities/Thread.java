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
public class Thread {
    private int  is_commentable, num_comments;
    private String id,permalink, last_comment_at;

    public Thread() {
    }

    public Thread(int num_comments, String id) {
        this.num_comments = num_comments;
        this.id = id;
    }

    public Thread(String id) {
        this.id = id;
    }
    

    public Thread(int is_commentable, int num_comments, String id, String permalink, String last_comment_at) {
        this.is_commentable = is_commentable;
        this.num_comments = num_comments;
        this.id = id;
        this.permalink = permalink;
        this.last_comment_at = last_comment_at;
    }

    public int getIs_commentable() {
        return is_commentable;
    }

    public void setIs_commentable(int is_commentable) {
        this.is_commentable = is_commentable;
    }

    public int getNum_comments() {
        return num_comments;
    }

    public void setNum_comments(int num_comments) {
        this.num_comments = num_comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getLast_comment_at() {
        return last_comment_at;
    }

    public void setLast_comment_at(String last_comment_at) {
        this.last_comment_at = last_comment_at;
    }

//    @Override
//    public String toString() {
//        return "Thread{" + "is_commentable=" + is_commentable + ", num_comments=" + num_comments + ", id=" + id + ", permalink=" + permalink + ", last_comment_at=" + last_comment_at + '}';
//    }
//    
    @Override
    public String toString() {
        return id;
    }
    
}
