/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.DAO;

import com.crowd.Util.MyConnexion;
import com.crowd.entities.Projet;
import com.crowd.entities.categorieProjet;
import com.crowd.entities.typeProjet;
import com.crowd.IDAO.iprojet;
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
 * @author MohamedAmine
 */
public class ProjetDao implements iprojet {

    private PreparedStatement pstm;

    private Connection cnx;

    public ProjetDao() {
        cnx = MyConnexion.getInstance();
    }

    @Override
    public void add(Projet p) {

        String req = "insert into projet (NOM_PROJET,RESUME,BUDJET,argent,ID_CATEGORIE_PROJET,ID_TYPE,FICHIER) values (?,?,?,?,?,?,?) ";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getNOM_PROJET());
            ps.setString(2, p.getRESUME());

            ps.setDouble(3, p.getBUDJET());
            ps.setDouble(4, p.getArgent());
            ps.setInt(5, p.getCATEGORIE().getID_CATEGORIE_PROJET());

            ps.setInt(6, p.getType().getID_TYPE());
                ps.setString(7, p.getFICHIER());
            ps.executeUpdate();
            System.out.println("ok");

        } catch (SQLException ex) {
            System.out.println("non");

            Logger.getLogger(ProjetDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void remove(Projet p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Projet p, int id) {
        try {
            String request = "update projet set NOM_PROJET=?, RESUME=?,BUDJET=?, ID_CATEGORIE_PROJET=? , ID_TYPE=?    where ID_PROJET=?";
            PreparedStatement ps = cnx.prepareStatement(request);

            ps.setString(1, p.getNOM_PROJET());
            ps.setString(2, p.getRESUME());
            ps.setDouble(3, p.getBUDJET());
            ps.setInt(4, p.getCATEGORIE().getID_CATEGORIE_PROJET());
            ps.setInt(5, p.getType().getID_TYPE());
            ps.setInt(6, id);

            int result = ps.executeUpdate();
            ps.close();
            return result == 1;
        } catch (SQLException ex) {
            Logger.getLogger(iprojet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Projet getProjet(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Projet> display() {

        List<Projet> ListeProjet = new ArrayList<Projet>();

        String requete = "select NOM_PROJET,RESUME,BUDJET,argent,ID_PROJET,ID_TYPE,ID_CATEGORIE_PROJET,FICHIER from projet";
        try {
            Statement statement = cnx.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Projet Projet = new Projet();
                Projet.setNOM_PROJET(resultat.getString(1));
                Projet.setRESUME(resultat.getString(2));
                Projet.setBUDJET(resultat.getDouble(3));
                Projet.setArgent(resultat.getDouble(4));
                Projet.setID_PROJET(resultat.getInt(5));
                Projet.setID_Type(resultat.getInt(6));
                    Projet.setID_Cat(resultat.getInt(7));
                    Projet.setFICHIER(resultat.getString(8));
                ListeProjet.add(Projet);
            }
            System.out.println(ListeProjet);
            return ListeProjet;
        } catch (SQLException ex) {
            //Logger.getLogger(CommentaireDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des Projets " + ex.getMessage());
            return null;
        }

    }

    public List<Projet> displaytest() {

        List<Projet> ListeProjet = new ArrayList<Projet>();

        String requete = "select NOM_PROJET,RESUME,BUDJET,argent,ID_CATEGORIE_PROJET,ID_TYPE from projet";
        try {
            Statement statement = cnx.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Projet Projet = new Projet();
                Projet.setNOM_PROJET(resultat.getString(1));
                Projet.setRESUME(resultat.getString(2));
                Projet.setBUDJET(resultat.getDouble(3));
                Projet.setArgent(resultat.getDouble(4));
                Projet.setCATEGORIE(new categorieProjet(resultat.getInt(5)));
                Projet.setType(new typeProjet(resultat.getInt(4)));
                ListeProjet.add(Projet);
            }

            return ListeProjet;
        } catch (SQLException ex) {
            //Logger.getLogger(CommentaireDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des Projets " + ex.getMessage());
            return null;
        }

    }

    public boolean supprimerProjjet(int id) {

        String requete = "delete from projet where ID_PROJET=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("projet supprimé avec succès");
            return true;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
            return false;
        }
    }

    public List<Projet> findByNOM_PROJET(String d) {
        List<Projet> listeProjets = new ArrayList<Projet>();

        String requete = "select NOM_PROJET,RESUME,BUDJET,argent from projet where NOM_PROJET like '%" + d + "%';";

        try {
            pstm = cnx.prepareStatement(requete);

//            pstm.setString(1, d);
            ResultSet resultat = pstm.executeQuery();

            while (resultat.next()) {
                Projet Projet = new Projet();
                Projet.setNOM_PROJET(resultat.getString(1));
                Projet.setRESUME(resultat.getString(2));
                Projet.setBUDJET(resultat.getDouble(3));
                Projet.setArgent(resultat.getDouble(4));
                listeProjets.add(Projet);
            }

            return listeProjets;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }

    }

}