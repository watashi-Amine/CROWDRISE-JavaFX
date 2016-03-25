/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.IDAO;

import com.crowd.entities.typeProjet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author MohamedAmine
 */
public interface itype {
    
    public typeProjet findtypeProjetByid(int id) throws SQLException;
    public void add(typeProjet t);
    public void remove(typeProjet t);
    public void remove1(int t);
      public boolean supprimerProjet(int id) ;
    public void update(typeProjet t);
    public typeProjet getType(int id);
    public List<typeProjet> display();
     public List<typeProjet> findtypeProjet() ;
       public typeProjet findtypeProjetByName(String type) throws SQLException;
}
