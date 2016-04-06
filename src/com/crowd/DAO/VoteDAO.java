/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.DAO;

import com.crowd.IDAO.IVoteDAO;
import com.crowd.Util.MyConnexion;
import com.crowd.entities.Vote;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oussama
 */
public class VoteDAO implements IVoteDAO {

    private Connection connection;

    public VoteDAO() {
      connection =MyConnexion.getInstance();
    }

    @Override
    public void addVote(Vote vote) {
        try {
            String req = "insert into vote (id, comment_id,created_at,value) values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, vote.getId());
            ps.setInt(2, vote.getComment_id());
            ps.setString(3, vote.getCreated_at());
            ps.setInt(4, vote.getValue());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateVote(Vote vote) {
        String requete = "update vote set value=?, comment_id=?, created_at=?  where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, vote.getValue());
            ps.setInt(2, vote.getComment_id());
            ps.setString(3, vote.getCreated_at());
            ps.setInt(4, vote.getId());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void removeVoteByCommentId(int comment_id) {
        String requete = "delete from vote where comment_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, comment_id);
            ps.executeUpdate();
            System.out.println("vote supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public void removeVoteById(int id) {
        String requete = "delete from vote where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("vote supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public void removeVoteByDate(String created_at) {
        String requete = "delete from vote where created_at=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, created_at);
            ps.executeUpdate();
            System.out.println("vote supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public List<Vote> findAll() {

        List<Vote> listeVote = new ArrayList<>();
        String requete = "select * from vote";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Vote vote = new Vote();
                vote.setId(resultat.getInt(1));
                vote.setComment_id(resultat.getInt(2));
                vote.setCreated_at(resultat.getString(3));
                vote.setValue(resultat.getInt(4));
                listeVote.add(vote);
            }
            return listeVote;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Votes " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Vote findVoteById(int id) {
        Vote vote = new Vote();
        String requete = "select * from vote where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                vote.setId(resultat.getInt(1));
                vote.setComment_id(resultat.getInt(2));
                vote.setCreated_at(resultat.getString(3));
                vote.setValue(resultat.getInt(4));
            }
            return vote;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du vote " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Vote findVoteByCommentId(int comment_id) {
        Vote vote = new Vote();
        String requete = "select * from vote where comment_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, comment_id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                vote.setId(resultat.getInt(1));
                vote.setComment_id(resultat.getInt(2));
                vote.setCreated_at(resultat.getString(3));
                vote.setValue(resultat.getInt(4));
            }
            return vote;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du vote " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Vote findVoteByDate(String created_at) {
        Vote vote = new Vote();
        String requete = "select * from vote where created_at=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, created_at);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                vote.setId(resultat.getInt(1));
                vote.setComment_id(resultat.getInt(2));
                vote.setCreated_at(resultat.getString(3));
                vote.setValue(resultat.getInt(4));
            }
            return vote;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du vote " + ex.getMessage());
            return null;
        }
    }


}
