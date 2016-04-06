/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.DAO;


import com.crowd.IDAO.ISolutionDAO;
import com.crowd.Util.MyConnexion;
import com.crowd.entities.Solution;
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
public class SolutionDAO implements ISolutionDAO {

    private Connection connection;

    public SolutionDAO() {
       connection =MyConnexion.getInstance();
    }

    @Override
    public void addSolution(Solution solution) {
        try {
            String req = "insert into solution (idsolution, idprobleme,author_id,description, fichier, duree, budjets) values (?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);

            ps.setInt(1, solution.getIdsolution());
            ps.setInt(2, solution.getIdprobleme());
            ps.setInt(3, solution.getAuthor_id());
            ps.setString(4, solution.getDescription());
            ps.setString(5, solution.getFichier());
            ps.setString(6, solution.getDuree());
            ps.setString(7, solution.getBudjets());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateSolution(Solution solution) {
        String requete = "update solution set description=?, fichier=?, duree=?, budjets=?   where idsolution=? or idprobleme=? or author_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, solution.getDescription());
            ps.setString(2, solution.getFichier());
            ps.setString(3, solution.getDuree());
            ps.setString(4, solution.getBudjets());
            ps.setInt(5, solution.getIdsolution());
            ps.setInt(6, solution.getIdprobleme());
            ps.setInt(7, solution.getAuthor_id());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void removeSolutionByAuthorId(int author_id) {
        String requete = "delete from solution where author_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, author_id);
            ps.executeUpdate();
            System.out.println("solution supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public void removeSolutionById(int idsolution) {
        String requete = "delete from solution where idsolution=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, idsolution);
            ps.executeUpdate();
            System.out.println("solution supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public void removeSolutionByIdProblemme(int idprobleme) {
        String requete = "delete from solution where idprobleme=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, idprobleme);
            ps.executeUpdate();
            System.out.println("solution supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public List<Solution> findAll() {

        List<Solution> listeSolution = new ArrayList<>();
        String requete = "select * from solution";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Solution solution = new Solution();
                solution.setAuthor_id(resultat.getInt(1));
                solution.setIdprobleme(resultat.getInt(2));
                solution.setDescription(resultat.getString(3));
                solution.setFichier(resultat.getString(4));
                solution.setDuree(resultat.getString(5));
                solution.setBudjets(resultat.getString(6));
                solution.setIdsolution(resultat.getInt(7));

                listeSolution.add(solution);
            }
            return listeSolution;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des solutions " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Solution findSolutionById(int idsolution) {
        Solution solution = new Solution();
        String requete = "select * from solution where idsolution=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, idsolution);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                solution.setAuthor_id(resultat.getInt(1));
                solution.setIdprobleme(resultat.getInt(2));
                solution.setDescription(resultat.getString(3));
                solution.setFichier(resultat.getString(4));
                solution.setDuree(resultat.getString(5));
                solution.setBudjets(resultat.getString(6));
                solution.setIdsolution(resultat.getInt(7));
            }
            return solution;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du solution " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Solution findSolutionByAuthorId(int author_id) {
       Solution solution = new Solution();
        String requete = "select * from solution where author_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, author_id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                solution.setAuthor_id(resultat.getInt(1));
                solution.setIdprobleme(resultat.getInt(2));
                solution.setDescription(resultat.getString(3));
                solution.setFichier(resultat.getString(4));
                solution.setDuree(resultat.getString(5));
                solution.setBudjets(resultat.getString(6));
                solution.setIdsolution(resultat.getInt(7));
            }
            return solution;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du solution " + ex.getMessage());
            return null;
        }}

    @Override
    public Solution findSolutionByIdProblemme(int idprobleme) {
        Solution solution = new Solution();
        String requete = "select * from solution where idprobleme=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, idprobleme);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                solution.setAuthor_id(resultat.getInt(1));
                solution.setIdprobleme(resultat.getInt(2));
                solution.setDescription(resultat.getString(3));
                solution.setFichier(resultat.getString(4));
                solution.setDuree(resultat.getString(5));
                solution.setBudjets(resultat.getString(6));
                solution.setIdsolution(resultat.getInt(7));
            }
            return solution;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du solution " + ex.getMessage());
            return null;
        }}

}
