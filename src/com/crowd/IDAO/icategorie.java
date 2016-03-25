/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.IDAO;

import com.crowd.entities.categorieProjet;
import com.crowd.entities.typeProjet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author MohamedAmine
 */
public interface icategorie {
     public categorieProjet findcategorieProjetById(int id) throws SQLException;
    public void add(categorieProjet c);
    public void remove(categorieProjet c);
    public void update(categorieProjet c);
    public categorieProjet getProjet(int id);
    public List<categorieProjet> display();
    public boolean supprimercategorieProjet(int id) ;
    public List<categorieProjet> findcategorieProjet() ;
    
     public categorieProjet findcategorieProjetByName(String nomcategorie) throws SQLException ;
    
    
}
