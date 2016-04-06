/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.IDAO;

import com.crowd.entities.Thread;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Oussama
 */
public interface IThreadDAO {

    void addThread(Thread vote);

    void updateThread(Thread vote);

    void removeThreadByCommentNum(int num_comments);

    void removeThreadById(String id);

    void removeThreadByDate(String last_comment_at);

    List<Thread> findAll();

    Thread findThreadById(String id);

    Thread findVoteByCommentNum(int num_comments);

    Thread findVoteByDate(String last_comment_at);

    ObservableList<Thread> listerThread();
}
