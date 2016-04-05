/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.IDAO;

import com.crowd.entities.Projet;
import java.util.List;

/**
 *
 * @author MohamedAmine
 */
public interface iprojet {
     public void add(Projet p);
    public void remove(Projet p);
    public boolean update(Projet p,int id);
    public Projet getProjet(int id);
    public List<Projet> display();
      public boolean supprimerProjjet(int id) ;
      public  List<Projet> findByNOM_PROJET(String d);
       public List<Projet> displaytest(int i) ;
      public List<Projet> findByCat(String d);
      public List<Projet> findByNOM_PROJETAndUser(String d,int i) ;
        public void addthis(Projet p) ;
}
