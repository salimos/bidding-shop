/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectjee.model;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Marwen
 */
@Named(value = "sousCategorie")
@Dependent
public class SousCategorie {
    private int id;
    private String nom;
    private int categorieId;

    /**
     * Creates a new instance of SousCategorie
     */
    public SousCategorie() {
    }

    public SousCategorie(int id, String name, int categorieId) {
        this.id = id;
        this.nom = name;
        this.categorieId = categorieId;
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
     * @return the name
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the name to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the categorieId
     */
    public int getCategorieId() {
        return categorieId;
    }

    /**
     * @param categorieId the categorieId to set
     */
    public void setCategorieId(int categorieId) {
        this.categorieId = categorieId;
    }
    
    
    
}
