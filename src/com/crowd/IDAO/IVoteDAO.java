/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.IDAO;

import com.crowd.entities.Vote;
import java.util.List;

/**
 *
 * @author Oussama
 */
public interface IVoteDAO {

    void addVote(Vote vote);

    void updateVote(Vote vote);

    void removeVoteByCommentId(int comment_id);

    void removeVoteById(int id);

    void removeVoteByDate(String created_at);

    List<Vote> findAll();

    Vote findVoteById(int id);

    Vote findVoteByCommentId(int comment_id);

    Vote findVoteByDate(String created_at);
}
