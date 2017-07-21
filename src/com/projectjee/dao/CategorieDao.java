/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectjee.dao;

import com.projectjee.model.Categorie;
import com.projectjee.model.SousCategorie;
import com.projectjee.util.DataBaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

/**
 *
 * @author Marwen
 */

@ManagedBean(name = "categorieDao")
@RequestScoped
public class CategorieDao {
    
    
    @ManagedProperty(value="#{param.categorieId}")
    private int categorieId;
    private Categorie categorie;
    private String nom;
    private DataBaseConnector db = DataBaseConnector.getDatabaseConnector();
    
    public ArrayList<Categorie> all(){
        ArrayList<Categorie> list = new ArrayList<Categorie>();
        try {
            
            
            ResultSet rs = db.getResults("Select * from categories");
            while(rs.next()){
                list.add(new Categorie(rs.getInt(1),rs.getString(2)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public Categorie one(int id){
        
        try {
            ResultSet rs = db.getResults("Select * from categories where id=" + id);
            if(rs.next()){
                return (new Categorie(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void Ajouter(){
        db.execUpdate("insert into categories values(NULL,'" + nom +"')");
    }
    /**
     * Creates a new instance of CategorieDao
     */
    public CategorieDao() {
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void delete(){
        db.execUpdate("Delete from categories where id=" + categorieId);
    }
    
    
    
    public void update(Categorie c){
        db.execUpdate("Update categories where id=" + c.getId() + "set ");
    }

    public void setCategorieId(int categorieId) {
        this.categorieId = categorieId;
    }

    public int getCategorieId() {
        return categorieId;
    }
    
    
    public List<SelectItem> selectList() {
        
        SousCategorieDao sousCategorieDao = new SousCategorieDao();
        List<SelectItem> l = new ArrayList<SelectItem>();;
        
        for (Categorie cat : all()) {
            
            SelectItemGroup g1 = new SelectItemGroup(cat.getNom());
            ArrayList<SousCategorie> souscats = sousCategorieDao.all(cat.getId());
            SelectItem tmp[] = new SelectItem[souscats.size()];
            int i = 0;
            for(SousCategorie scat : souscats){
                tmp[i] = new SelectItem(scat.getId(), scat.getNom());
                i++;
            }
            g1.setSelectItems(tmp);
            //g1.setSelectItems(new SelectItem[]{new SelectItem("BMW", "BMW"), new SelectItem("Mercedes", "Mercedes"), new SelectItem("Volkswagen", "Volkswagen")});
            
            l.add(g1);
        }
        return l;
    }

    
    
}
