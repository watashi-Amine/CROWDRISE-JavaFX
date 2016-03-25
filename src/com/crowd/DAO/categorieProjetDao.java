/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.DAO;

import com.crowd.Util.MyConnexion;
import com.crowd.entities.categorieProjet;
import com.crowd.entities.typeProjet;
import com.crowd.IDAO.icategorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MohamedAmine
 */
public class categorieProjetDao implements icategorie   {
    
        private PreparedStatement pstm;

       private Connection cnx;

    public categorieProjetDao() {
        cnx=MyConnexion.getInstance();
    }
    
    

    @Override
    public void add(categorieProjet c) {
 	


        String req ="insert into categorie_projet (CATEGORIE) values (?) ";
        try {
            PreparedStatement ps=cnx.prepareStatement(req);
            ps.setString(1,c.getCATEGORIE());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(categorieProjetDao.class.getName()).log(Level.SEVERE, null, ex);
        }


    }


    
    @Override
    public void remove(categorieProjet c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(categorieProjet c) {
            String req = "update categorie_projet set CATEGORIE =? where ID_CATEGORIE_PROJET=?";
        try {
            pstm = cnx.prepareStatement(req);

            pstm.setString(1, c.getCATEGORIE());
            pstm.setInt(2, c.getID_CATEGORIE_PROJET());
          
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(typeProjetDao.class.getName()).log(Level.SEVERE, null, ex);
        }



    }

    @Override
    public categorieProjet getProjet(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<categorieProjet> display() {

       List<categorieProjet> ListecategorieProjet = new ArrayList<categorieProjet>();

        String requete = "select * from categorie_projet";
        try {
            Statement statement = cnx.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                categorieProjet categorieProjet = new categorieProjet();
                categorieProjet.setID_CATEGORIE_PROJET(resultat.getInt(1));
                categorieProjet.setCATEGORIE(resultat.getString(2));
                

                ListecategorieProjet.add(categorieProjet);
            }
            
            return   ListecategorieProjet;
        } catch (SQLException ex) {
            //Logger.getLogger(CommentaireDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des categorieProjets " + ex.getMessage());
            return null;
        }



    }
    
    
     public boolean supprimercategorieProjet(int id) {
        
            String requete = "delete from categorie_projet where ID_CATEGORIE_PROJET=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("categorie_projet supprimé avec succès");
            return true;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
            return false;
        }
    }

    
      public List<categorieProjet> findcategorieProjet() {
List<categorieProjet> listecategorieProjet = new ArrayList<categorieProjet>();
   
        String requete = "select CATEGORIE from categorie_projet ";
       
        
        try {
            pstm = cnx.prepareStatement(requete);
            
//            pstm.setString(1, d);
            ResultSet resultat = pstm.executeQuery();
           
            
            
            while (resultat.next()) {
                categorieProjet categorieProjet = new categorieProjet();
                categorieProjet.setCATEGORIE(resultat.getString(1));
                listecategorieProjet.add(categorieProjet);
            }
           
            return listecategorieProjet;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }
    
    }

    
       public categorieProjet findcategorieProjetByName(String nomcategorie) throws SQLException{
    categorieProjet categorieProjet = new categorieProjet();
        String requete = "select * from categorie_projet where 	CATEGORIE=?";
       
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, nomcategorie);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                categorieProjet.setID_CATEGORIE_PROJET(resultat.getInt(1));
                categorieProjet.setCATEGORIE(resultat.getString(2));
               
            }
          if (Objects.nonNull(categorieProjet)) {
            return categorieProjet;
        }

        throw new UnsupportedOperationException();
    
    }



 public categorieProjet findcategorieProjetById(int id) throws SQLException{
    categorieProjet categorieProjet = new categorieProjet();
        String requete = "select * from categorie_projet where ID_CATEGORIE_PROJET=?";
       
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                categorieProjet.setID_CATEGORIE_PROJET(resultat.getInt(1));
                categorieProjet.setCATEGORIE(resultat.getString(2));
               
            }
          if (Objects.nonNull(categorieProjet)) {
            return categorieProjet;
        }

        throw new UnsupportedOperationException();
    
    }





}
