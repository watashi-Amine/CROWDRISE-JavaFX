/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.DAO;

import com.crowd.IDAO.ICommentDAO;
import com.crowd.Util.MyConnexion;
import com.crowd.entities.Comment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class CommentDAO implements ICommentDAO {

    private final Connection connection;

    private PreparedStatement pstm;

    public CommentDAO() {
        connection = MyConnexion.getInstance();
    }

    @Override
    public void addComment(Comment comment) {
         try {
            String req = "insert into comment (author_id,thread_id,ancestors,depth,state,body,score,created_at) values (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, comment.getAuthor_id());
            ps.setString(2, comment.getThread_id());
            ps.setString(3, comment.getAncestors());
            ps.setInt(4, comment.getDepth());
            ps.setInt(5, comment.getState());
            ps.setString(6, comment.getBody());
            ps.setInt(7, comment.getScore());
            ps.setString(8, comment.getCreated_at());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addCommentaire(Comment comment) {
        try {
            String req = "insert into comment (author_id,thread_id,body,score,created_at) values (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, comment.getAuthor_id());
            ps.setString(2, comment.getThread_id());
            ps.setString(3, comment.getBody());
            ps.setInt(4, comment.getScore());
            ps.setString(5, comment.getCreated_at());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<Comment> findAll() {

        ObservableList<Comment> listecomment;
        String requete = "select * from comment";
        try {
            listecomment = FXCollections.observableArrayList();
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Comment comment = new Comment();
                comment.setId(resultat.getInt(1));
                comment.setThread_id(resultat.getString(2));
                comment.setBody(resultat.getString("body"));
                comment.setAncestors(resultat.getString(5));
                comment.setDepth(resultat.getInt(6));
                comment.setCreated_at(resultat.getString(7));
                comment.setState(resultat.getInt(8));
                comment.setScore(resultat.getInt(9));
                comment.setAuthor_id(resultat.getInt(3));
                listecomment.add(comment);
            }
            return listecomment;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des comments " + ex.getMessage());
            return null;
        }
    }

    @Override
    public ObservableList<Comment> findAlluser() {

        ObservableList<Comment> listecomment;
        String requete = "select c.id, c.thread_id, c.author_id,c.body,c.created_at, c.score,u.username from user u,comment c where c.author_id=u.id ";
        try {
            listecomment = FXCollections.observableArrayList();
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Comment comment = new Comment();
                comment.setId(resultat.getInt("id"));
                comment.setThread_id(resultat.getString("thread_id"));
                comment.setBody(resultat.getString("body"));
                comment.setCreated_at(resultat.getString("created_at"));
                comment.setScore(resultat.getInt("score"));
                comment.setAuthor_id(resultat.getInt("author_id"));
                comment.setUsername(resultat.getString("username"));
                listecomment.add(comment);
            }
            return listecomment;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des comments " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Comment> findBybodyAndUser(String d, int i) {
        List<Comment> listeCOMMENT = new ArrayList<Comment>();

        String requete = "SELECT id, thread_id, ancestors, depth, created_at, state, score FROM comment WHERE body like '%' ? '%' And author_id = ?;";

        try {

            pstm = connection.prepareStatement(requete);
            pstm.setString(1, d);
            pstm.setInt(2, i);
            ResultSet resultat = pstm.executeQuery();
            while (resultat.next()) {
                Comment comm = new Comment();
                comm.setId(resultat.getInt(1));
                comm.setThread_id(resultat.getString(2));
                comm.setAncestors(resultat.getString(3));
                comm.setDepth(resultat.getInt(4));
                comm.setCreated_at(resultat.getString(5));
                comm.setState(resultat.getInt(6));
                comm.setScore(resultat.getInt(7));
                listeCOMMENT.add(comm);
            }

            return listeCOMMENT;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }

    }

    @Override
    public Comment findCommentByAuthorId(int author_id) {
        Comment comment = new Comment();
        String requete = "select * from comment where author_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, author_id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                comment.setId(resultat.getInt(1));
                comment.setThread_id(resultat.getString(2));
                comment.setBody(resultat.getString(4));
                comment.setAncestors(resultat.getString(5));
                comment.setDepth(resultat.getInt(6));
                comment.setCreated_at(resultat.getString(7));
                comment.setState(resultat.getInt(8));
                comment.setScore(resultat.getInt(9));
                comment.setAuthor_id(resultat.getInt(3));
            }
            return comment;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du commentaire " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Comment findCommentByBody(String body) {
        Comment comment = new Comment();
        String requete = "select * from comment where body=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, body);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                comment.setId(resultat.getInt(1));
                comment.setThread_id(resultat.getString(2));
                comment.setBody(resultat.getString(4));
                comment.setAncestors(resultat.getString(5));
                comment.setDepth(resultat.getInt(6));
                comment.setCreated_at(resultat.getString(7));
                comment.setState(resultat.getInt(8));
                comment.setScore(resultat.getInt(9));
                comment.setAuthor_id(resultat.getInt(3));

            }
            return comment;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du commentaire " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Comment findCommentByDateCreation(String created_at) {
        Comment comment = new Comment();
        String requete = "select * from comment where created_at=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, created_at);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                comment.setId(resultat.getInt(1));
                comment.setThread_id(resultat.getString(2));
                comment.setBody(resultat.getString(4));
                comment.setAncestors(resultat.getString(5));
                comment.setDepth(resultat.getInt(6));
                comment.setCreated_at(resultat.getString(7));
                comment.setState(resultat.getInt(8));
                comment.setScore(resultat.getInt(9));
                comment.setAuthor_id(resultat.getInt(3));
            }
            return comment;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du commentaire " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Comment findCommentById(int id) {
        Comment comment = new Comment();
        String requete = "select * from comment where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                comment.setId(resultat.getInt(1));
                comment.setThread_id(resultat.getString(2));
                comment.setBody(resultat.getString(4));
                comment.setAncestors(resultat.getString(5));
                comment.setDepth(resultat.getInt(6));
                comment.setCreated_at(resultat.getString(7));
                comment.setState(resultat.getInt(8));
                comment.setScore(resultat.getInt(9));
                comment.setAuthor_id(resultat.getInt(3));
                System.out.println(comment);
            }

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du commentaire " + ex.getMessage());
            return null;
        }
        return comment;

    }

    @Override
    public Comment findCommentByThread(String Th) {
        Comment comment = new Comment();
        String requete = "select * from comment where thread_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, Th);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                comment.setId(resultat.getInt(1));
                comment.setThread_id(resultat.getString(2));
                comment.setBody(resultat.getString(4));
                comment.setAncestors(resultat.getString(5));
                comment.setDepth(resultat.getInt(6));
                comment.setCreated_at(resultat.getString(7));
                comment.setState(resultat.getInt(8));
                comment.setScore(resultat.getInt(9));
                comment.setAuthor_id(resultat.getInt(3));

            }
            return comment;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du commentaire " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Comment> findbyuser(String u) {
        List<Comment> listeCOMMENT = new ArrayList<Comment>();

        String requete = "SELECT id, thread_id, created_at, body, score FROM comment WHERE body like '%' ? '%';";

        try {

            pstm = connection.prepareStatement(requete);
            pstm.setString(1, u);
            ResultSet resultat = pstm.executeQuery();
            while (resultat.next()) {
                Comment comm = new Comment();
                comm.setId(resultat.getInt(1));
                comm.setThread_id(resultat.getString(2));
                comm.setCreated_at(resultat.getString(3));
                comm.setBody(resultat.getString(6));
                comm.setScore(resultat.getInt(7));
                listeCOMMENT.add(comm);
            }

            return listeCOMMENT;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Comment> finduser() {

        List<Comment> listeuser = new ArrayList<Comment>();

        String requete = "select author_id from comment ";

        try {
            pstm = connection.prepareStatement(requete);

            ResultSet resultat = pstm.executeQuery();

            while (resultat.next()) {
                Comment comm1 = new Comment();
                comm1.setAuthor_id(resultat.getInt("author_id"));
                listeuser.add(comm1);
            }
            System.out.println(listeuser);
            return listeuser;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }

    }

    @Override
    public void removeCommentByAuthorId(int author_id) {
        String requete = "delete from comment where author_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, author_id);
            ps.executeUpdate();
            System.out.println("Commentaire supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public void removeCommentByCreationDate(String created_at) {
        String requete = "delete from comment where created_at=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, created_at);
            ps.executeUpdate();
            System.out.println("Commentaire supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public void removeCommentById(int id) {
        String requete = "delete from comment where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Commentaire supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public void updateComment(Comment comment) {
        String requete = "update comment c,user u set c.score=?, c.created_at=?, c.body=?,c.author_id=u.id where u.username=? and c.id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, comment.getScore());
            ps.setString(2, comment.getCreated_at());
            ps.setString(3, comment.getBody());
            ps.setString(4, comment.getUsername());
            ps.setInt(5, comment.getId());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

}
