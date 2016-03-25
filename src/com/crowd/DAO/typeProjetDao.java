/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.DAO;

import com.crowd.Util.MyConnexion;
import com.crowd.entities.typeProjet;
import com.crowd.IDAO.itype;
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
public class typeProjetDao implements itype {

    private Connection cnx;

    private Statement stm;
    private PreparedStatement pstm;

    public typeProjetDao() {
        cnx = MyConnexion.getInstance();
    }

    
    
    
    
    @Override
    public void add(typeProjet t) {

        String req = "insert into type_projet (TYPE) values (?) ";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getTYPE());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(typeProjetDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void remove(typeProjet t) {

        String query = "delete from type_projet where ID_TYPE = ?";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = cnx.prepareStatement(query);
            preparedStmt.setInt(1,3 );

            preparedStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(typeProjetDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(typeProjet t) {
 String req = "update type_projet set TYPE =? where ID_TYPE=?";
        try {
            pstm = cnx.prepareStatement(req);

            pstm.setString(1, t.getTYPE());
            pstm.setInt(2, t.getID_TYPE());
          
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(typeProjetDao.class.getName()).log(Level.SEVERE, null, ex);
        }



    }

    @Override
    public typeProjet getType(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<typeProjet> display() {
            List<typeProjet> ListeTypeProjet = new ArrayList<typeProjet>();

        String requete = "select * from type_projet";
        try {
            Statement statement = cnx.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                typeProjet type = new typeProjet();
                type.setID_TYPE(resultat.getInt(1));
                type.setTYPE(resultat.getString(2));
                

                ListeTypeProjet.add(type);
            }
//            System.out.println(ListeTypeProjet);
            return   ListeTypeProjet;
        } catch (SQLException ex) {
            //Logger.getLogger(CommentaireDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des types " + ex.getMessage());
            return null;
        }









    }

    @Override
    public void remove1(int t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
     public boolean supprimerProjet(int id) {
        
            String requete = "delete from type_projet where ID_TYPE=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Projet supprimé avec succès");
            return true;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
            return false;
        }
    }
     
     
     
     
      public List<typeProjet> findtypeProjet() {
List<typeProjet> listetypeProjet = new ArrayList<typeProjet>();
   
        String requete = "select TYPE from type_projet ";
       
        
        try {
            pstm = cnx.prepareStatement(requete);
            
//            pstm.setString(1, d);
            ResultSet resultat = pstm.executeQuery();
           
            
            
            while (resultat.next()) {
                typeProjet typeProjet = new typeProjet();
                typeProjet.setTYPE(resultat.getString(1));
                listetypeProjet.add(typeProjet);
            }
            System.out.println(listetypeProjet);
            return listetypeProjet;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }
    
    }

    
     
  
       public typeProjet findtypeProjetByName(String type) throws SQLException{
    typeProjet typeProjet = new typeProjet();
        String requete = "select * from type_projet where 	TYPE=?";
       
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, type);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                typeProjet.setID_TYPE(resultat.getInt(1));
                typeProjet.setTYPE(resultat.getString(2));
               
            }
          if (Objects.nonNull(typeProjet)) {
            return typeProjet;
        }

        throw new UnsupportedOperationException();
    
    }
   
     
     
      public typeProjet findtypeProjetByid(int id) throws SQLException{
    typeProjet typeProjet = new typeProjet();
        String requete = "select * from type_projet where ID_TYPE=?";
       
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                typeProjet.setID_TYPE(resultat.getInt(1));
                typeProjet.setTYPE(resultat.getString(2));
               
            }
          if (Objects.nonNull(typeProjet)) {
            return typeProjet;
        }

        throw new UnsupportedOperationException();
    
    }
     
}
