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
public class Comment {
    
    private Membre membre;
    private Thread thread;
    private int id, depth, state, score, author_id;
    private String thread_id, body, ancestors, created_at,username;

    public Comment() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Comment(int id, int author_id) {
        this.id = id;
        this.author_id = author_id;
    }

    public Comment(int id, String body) {
        this.id = id;
        this.body = body;
    }

    public Comment(int depth, int score, int author_id, String thread_id, String body, String ancestors, String created_at) {
        this.depth = depth;
        this.score = score;
        this.author_id = author_id;
        this.thread_id = thread_id;
        this.body = body;
        this.ancestors = ancestors;
        this.created_at = created_at;
    }

    public Comment(int state, int score, String thread_id, String body, String created_at) {
        this.state = state;
        this.score = score;
        this.thread_id = thread_id;
        this.body = body;
        this.created_at = created_at;
    }

    public Comment(int id, int depth, int state, int score, int author_id, String created_at, String thread_id, String body, String ancestors) {
        this.id = id;
        this.depth = depth;
        this.state = state;
        this.score = score;
        this.author_id = author_id;
        this.created_at = created_at;
        this.thread_id = thread_id;
        this.body = body;
        this.ancestors = ancestors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getThread_id() {
        return thread_id;
    }

    public void setThread_id(String thread_id) {
        this.thread_id = thread_id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAncestors() {
        return ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    @Override
    public String toString() {
        return author_id+"" ;
    }
//
//    @Override
//    public String toString() {
//        return "Comment{" + "id=" + id + ", depth=" + depth + ", state=" + state + ", score=" + score + ", author_id=" + author_id + ", created_at=" + created_at + ", thread_id=" + thread_id + ", body=" + body + ", ancestors= " + ancestors;
//    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

}
