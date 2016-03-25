/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mcd
 */
public class MyConnexion {
    private static Connection cnx;
    private String url="jdbc:mysql://localhost/wwww";
    private String user="root";
    private String pwd="";

    
    
    private MyConnexion() {
        try {
            cnx=DriverManager.getConnection(url, user, pwd);
        } catch (SQLException ex) {
            Logger.getLogger(MyConnexion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur"+ex.getMessage());
        }
    }
    
   public static Connection getInstance()
   {if (cnx==null) new MyConnexion();
   return cnx;
   }
    
}
