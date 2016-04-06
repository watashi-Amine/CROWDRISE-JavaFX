
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.IDAO;

import com.crowd.entities.Comment;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Oussama
 */
public interface ICommentDAO {

    void addComment(Comment comment);

    void addCommentaire(Comment comment);

    void updateComment(Comment comment);

    void removeCommentByAuthorId(int author_id);

    void removeCommentById(int id);

    void removeCommentByCreationDate(String created_at);

    ObservableList<Comment> findAll();

    ObservableList<Comment> findAlluser();

    List<Comment> finduser();

    List<Comment> findbyuser(String u);

    Comment findCommentById(int id);

    Comment findCommentByAuthorId(int author_id);

    Comment findCommentByBody(String body);

    Comment findCommentByThread(String Th);

    Comment findCommentByDateCreation(String created_at);

    public List<Comment> findBybodyAndUser(String d, int i);

//public List<Comment> findcommentById(int a);
}
