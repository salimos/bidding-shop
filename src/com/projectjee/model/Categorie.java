/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectjee.model;

import com.projectjee.dao.CategorieDao;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Marwen
 */
@ManagedBean(name = "categorie")
@Dependent
public class Categorie {
    private int id;
    private boolean editable;
    
    @ManagedProperty(value="#{categorieDao}")
    private CategorieDao categorieDao;
    
    private String nom;

    /**
     * Creates a new instance of Categorie
     */
    public Categorie() {
    }

    public Categorie(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
    
    public void update(){
        categorieDao.update(this);
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isEditable() {
        return editable;
    }
    
    
   
    
}
