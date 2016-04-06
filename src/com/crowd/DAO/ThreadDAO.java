/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.DAO;

import com.crowd.entities.Thread;
import com.crowd.IDAO.IThreadDAO;
import com.crowd.Util.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Oussama
 */
public class ThreadDAO implements IThreadDAO {

    private final Connection connection;

    private PreparedStatement pstm;

    public ThreadDAO() {
        connection = MyConnexion.getInstance();
    }

    @Override
    public void addThread(Thread thread) {
        try {
            String req = "insert into thread (id, permalink, is_commentable, num_comments, last_comment_at) values (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, thread.getId());
            ps.setString(2, thread.getPermalink());
            ps.setInt(3, thread.getIs_commentable());
            ps.setInt(4, thread.getNum_comments());
            ps.setString(5, thread.getLast_comment_at());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateThread(Thread thread) {
        String requete = "update thread set permalink=?, is_commentable=?, num_comments=?, last_comment_at=?  where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, thread.getPermalink());
            ps.setInt(2, thread.getIs_commentable());
            ps.setInt(3, thread.getNum_comments());
            ps.setString(1, thread.getLast_comment_at());
            ps.setString(5, thread.getId());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void removeThreadByCommentNum(int num_comments) {
        String requete = "delete from thread where num_comments=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, num_comments);
            ps.executeUpdate();
            System.out.println("thread supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public void removeThreadById(String id) {
        String requete = "delete from thread where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("thread supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public void removeThreadByDate(String last_comment_at) {
        String requete = "delete from thread where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, last_comment_at);
            ps.executeUpdate();
            System.out.println("thread supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public List<Thread> findAll() {

        List<Thread> listeth = new ArrayList<Thread>();

        String requete = "select id from thread ";

        try {
            pstm = connection.prepareStatement(requete);

            ResultSet resultat = pstm.executeQuery();

            while (resultat.next()) {
                Thread th = new Thread();
                th.setId(resultat.getString("id"));
                listeth.add(th);
            }
            System.out.println(listeth);
            return listeth;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Thread findThreadById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Thread findVoteByCommentNum(int num_comments) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Thread findVoteByDate(String last_comment_at) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Thread> listerThread() {

        ObservableList<Thread> list;

        String requete = "select id from thread ";

        try {
            pstm = connection.prepareStatement(requete);
            list = FXCollections.observableArrayList();
            ResultSet resultat = pstm.executeQuery();

            while (resultat.next()) {
                Thread th = new Thread();
                th.setId(resultat.getString("id"));
                list.add(th);
            }
            System.out.println(list+"lllllrrrr");
            return list;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }
    }

}
