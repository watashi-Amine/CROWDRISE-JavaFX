/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.DAO;

import com.crowd.IDAO.IMembre;
import com.crowd.Util.DateUtil;
import com.crowd.Util.MultipartUtility;
import com.crowd.Util.MyConnexion;
import com.crowd.Util.Password;
import static com.crowd.Util.Password.checkPassword;
import com.crowd.entities.Membre;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASR1
 */
public class MembreDAO implements IMembre {

    private Connection cnx;

    public MembreDAO() {
        cnx = MyConnexion.getInstance();
    }

    @Override
    public void add(Membre m) {
        Password p = new Password();
        String hash = p.hashPassword(m.getPassword());
        String salt;
        salt = p.salt;

        String req = "insert into user (nom,  username,username_canonical,  password,  email,email_canonical,  roles,enabled,salt,locked,expired,credentials_expired,prenom,date_naissance,userimage,imagename) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            
                        FileInputStream fis = new FileInputStream(m.getUserimage());

            
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, m.getNom());
            ps.setString(2, m.getUsername());
            ps.setString(3, m.getUsername());//username_cononical
            ps.setString(4, hash);//password hasher en bcrypt
            ps.setString(5, m.getEmail());//email
            ps.setString(6, m.getEmail());//email_cononical
            ps.setString(7, "a:0:{}");
            ps.setBoolean(8, true);//enabled
            ps.setString(9, salt);
            ps.setBoolean(10, false);//locked
            ps.setBoolean(11, false);//expired
            ps.setBoolean(12, true);//cordinental_expired
            ps.setString(13, m.getPrenom());
            ps.setString(14, m.getDate_naissance());
            ps.setBinaryStream(15, (InputStream) fis, (int) m.getUserimage().length());
            ps.setString(16, m.getImagename());
//*********************************upload web service photo de profile*****//
            String charset = "UTF-8";
     //   File uploadFile1 = new File("D:/logique.pdf");
            //     File uploadFile2 = new File("C://Users/ASR1/Downloads/avatar.png");
            String requestURL = "http://localhost:80/1/upload.php";

            try {
                MultipartUtility multipart = new MultipartUtility(requestURL, charset);

                multipart.addHeaderField("User-Agent", "CodeJava");
                multipart.addHeaderField("Test-Header", "Header-Value");

                multipart.addFormField("Username", "TestUser");

                multipart.addFilePart("uploaded_file[]", m.getUserimage());
         //   multipart.addFilePart("fileUpload[]", uploadFile1);

                List<String> response = multipart.finish();

                System.out.println("SERVER REPLIED:");

                for (String line : response) {
                    System.out.println(line);
                }
                ///****************************************************//
            } catch (IOException ex) {
                System.err.println(ex);
        
    }
            
            
            
            
          


        
            
            

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MembreDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MembreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateMembre(Membre membre) {
        String requete = "update user set nom=? where id=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, membre.getNom());
            ps.setInt(2, membre.getId_membre());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
   public String selectrole(int id){
        String role="";
       String requete = "select roles from user where id=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1, id);
            
             ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               role=rs.getString(1);
            }
      
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }  
                    return role;

    }

    public void removeMembreyId(Membre membre) {
        String requete = "delete from user where id=? ";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1, membre.getId_membre());
            ps.executeUpdate();
            System.out.println("membre supprimer avec succes");
        } catch (Exception e) {
            System.out.println("erreur lors de la suppression" + e.getMessage());
        }

    }

    @Override
    public Membre findMembreById(int id) {

        Membre membre = new Membre();
        String requete = "select * from user where id=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                membre.setId_membre(rs.getInt(1));
                membre.setNom(rs.getString(2));
                membre.setUsername(rs.getString(3));
                membre.setPassword(rs.getString(4));
                membre.setEmail(rs.getString(5));
                membre.setIsAdministrateur(rs.getBoolean(6));
            }
            membre.toString();
            return membre;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du membre " + ex.getMessage());
            return null;
        }
    }
    
      @Override
    public ObservableList<Membre> findMembreByAnyCritere(String recherche) {
        
   ObservableList<Membre> list;
        String requete = "select id,nom,username,password,email,roles,date_naissance,prenom from user where nom LIKE ?";

        try {
            list = FXCollections.observableArrayList();
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, "%"+recherche+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Membre membre = new Membre();
                membre.setId_membre(rs.getInt(1));
                membre.setNom(rs.getString(2));

                membre.setUsername(rs.getString(3));
                membre.setPassword(rs.getString(4));
                membre.setEmail(rs.getString(5));
                membre.setIsAdministrateur(rs.getBoolean(6));
                membre.setDate_naissance(rs.getString(7));
                membre.setPrenom(rs.getString(8));
                list.add(membre);

            }

            return list;
        } catch (Exception e) {
            System.out.println("(MembreDAO:fonction listerbycritere )erreur lors de l affichage" + e.getMessage());
            return null;
        }
    
    }


    public ObservableList<Membre> listerMembre() {

        ObservableList<Membre> list;
        String requete = "select id,nom,username,password,email,roles,date_naissance,prenom from user";

        try {
            list = FXCollections.observableArrayList();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(requete);
            while (rs.next()) {
                Membre membre = new Membre();
                membre.setId_membre(rs.getInt(1));
                membre.setNom(rs.getString(2));

                membre.setUsername(rs.getString(3));
                membre.setPassword(rs.getString(4));
                membre.setEmail(rs.getString(5));
                membre.setIsAdministrateur(rs.getBoolean(6));
                membre.setDate_naissance(rs.getString(7));
                membre.setPrenom(rs.getString(8));
                list.add(membre);

            }

            return list;
        } catch (Exception e) {
            System.out.println("(MembreDAO:fonction lister )erreur lors de l affichage" + e.getMessage());
            return null;
        }

    }
    
    
    

    @Override
    public boolean authentification(String username, String password) {

        Password p = new Password();
        System.out.println("username:" + username + "\n password:" + password);
        String requete = "select password from user where username=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    System.out.println(rs.getString(1) + "---->pass hasher");
                    try {
                        if (p.checkPassword(password, rs.getString(1)) == true) {

                            System.out.println("authentification reussite");
                            return true;

                        }
                    } catch (IllegalArgumentException e) {

                    }

                }
            }

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du membre " + ex.getMessage());
            return false;
        }

        return false;
    }

    @Override
    public Membre informationMembre(String username) {
        String requete = "select nom,prenom,username,email,password,date_naissance,id from user where username=?";
                Membre membre = new Membre();

        try {
            PreparedStatement ps=cnx.prepareStatement(requete);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
           
            while (rs.next()) {
                //membre.setId_membre(rs.getInt(1));
                membre.setId_membre(rs.getInt(7));
                membre.setNom(rs.getString(1));
                membre.setPrenom(rs.getString(2));
                membre.setUsername(rs.getString(3));
                membre.setEmail(rs.getString(4));
                membre.setPassword(rs.getString(5));
                membre.setDate_naissance(rs.getString(6));

             

            }
            return membre;
            } catch (Exception e) {
            System.out.println("(MembreDAO:fonction infomationMembre )erreur lors de l affichage" + e.getMessage());
            return null;
        }
    }

   

   @Override
    public List<Membre> findMembre() {

        List<Membre> listeMembre = new ArrayList<Membre>();

        String requete = "select username from user ";

        try {
          PreparedStatement  pstm = cnx.prepareStatement(requete);

//            pstm.setString(1, d);
            ResultSet resultat = pstm.executeQuery();

            while (resultat.next()) {
                Membre mm = new Membre();
                mm.setUsername(resultat.getString(1));
                listeMembre.add(mm);
            }

            return listeMembre;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }

    }

     @Override
    public int findMembreByusername(String name) {
         int membre = 0;
        String requete = "select id from user where username=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                membre=rs.getInt(1);
               
            }
            return membre;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du membre " + ex.getMessage());
            return 0;
        } }
    
    
}
