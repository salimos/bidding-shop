/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectjee.dao;

import com.projectjee.model.SousCategorie;
import com.projectjee.util.DataBaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Marwen
 */
@ManagedBean(name = "sousCategorieDao")
@RequestScoped
public class SousCategorieDao {
    private DataBaseConnector db = DataBaseConnector.getDatabaseConnector();
    @ManagedProperty(value="#{param.categorieId}")
    private int categorieId;
    
    @ManagedProperty(value="#{param.sousCategorieId}")
    private int sousCategorieId;
    private String nom;
    
    /**
     * Creates a new instance of SousCategorieDao
     */
    public SousCategorieDao() {
    }
    
    public ArrayList<SousCategorie> all(int catid){

        ArrayList<SousCategorie> list = new ArrayList<SousCategorie>();
        try {
            
            ResultSet rs = db.getResults("Select * from souscategories where categorie_id=" + catid);
            while(rs.next()){
                list.add(new SousCategorie(rs.getInt(1),rs.getString(2),rs.getInt(3)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public SousCategorie one(int catid){
        
        try {
            
            ResultSet rs = db.getResults("Select * from souscategories where id=" + catid);
            if(rs.next()){
                return (new SousCategorie(rs.getInt(1),rs.getString(2),rs.getInt(3)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<SousCategorie> all(String catid){
        return all(Integer.parseInt(catid));
    }
    
    
    public void Ajouter(){
        System.out.println(categorieId);
        db.execUpdate("insert into souscategories values(NULL,'" + nom +"'," + categorieId + ")");
    }
    
    public void delete(){
        db.execUpdate("Delete from categories where id=" + sousCategorieId);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCategorieId(int categorieId) {
        this.categorieId = categorieId;
    }

    public int getCategorieId() {
        return categorieId;
    }

    public int getSousCategorieId() {
        return sousCategorieId;
    }

    public void setSousCategorieId(int sousCategorieId) {
        this.sousCategorieId = sousCategorieId;
    }
    
    
    
    
    
}
