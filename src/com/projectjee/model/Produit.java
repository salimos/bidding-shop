/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectjee.model;

import java.sql.Date;
import java.sql.Timestamp;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Marwen
 */
@ManagedBean(name = "produit")
@Dependent
public class Produit {
    
    private int id;
    private String nom;
    private String image1;
    private String image2;
    private String description;
    /*
    Si type = 1 c'est in enchaire
    Sinn c'est un vente immidiat
     */
    private int type;
    private float prix;
    private Timestamp datefin;
    private int idUser;
    private int idSousCat;
    /*
    Si etat un 0 donc produit non expiré
    Si 1 donc expiré, cette variable utilisé pour testes si un produit expiré et non son enchaire non traité encore.
     */
    private int etat;
    private int qnt;
    private int vendu;

    /**
     * Creates a new instance of Produit
     */
    public Produit() {
    }

    public Produit(int id, String nom, String image1, String image2, String description, int type, float prix, Timestamp datefin, int idUser,int idSousCat,int etat,int qnt,int vendu) {
        this.id = id;
        this.nom = nom;
        this.image1 = image1;
        this.image2 = image2;
        this.description = description;
        this.type = type;
        this.prix = prix;
        this.datefin = datefin;
        this.idUser = idUser;
        this.idSousCat = idSousCat;
        this.etat = etat;
        this.qnt = qnt;
        this.vendu = vendu;
    }

    public Produit(int id, String nom, String image1, String image2, String description, int type, float prix, Timestamp datefin, int idUser,int idSousCat,int etat) {
        this.id = id;
        this.nom = nom;
        this.image1 = image1;
        this.image2 = image2;
        this.description = description;
        this.type = type;
        this.prix = prix;
        this.datefin = datefin;
        this.idUser = idUser;
        this.idSousCat = idSousCat;
        this.etat = etat;
        this.qnt = 1;
        this.vendu = 0;
    }

    public Produit(int id, String nom, String image1, String image2, String description, int type, float prix, Timestamp datefin, int idUser,int idSousCat,int etat,int qnt) {
        this.id = id;
        this.nom = nom;
        this.image1 = image1;
        this.image2 = image2;
        this.description = description;
        this.type = type;
        this.prix = prix;
        this.datefin = datefin;
        this.idUser = idUser;
        this.idSousCat = idSousCat;
        this.etat = etat;
        this.qnt = qnt;
        this.vendu = 0;
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

    /**
     * @return the image1
     */
    public String getImage1() {
        return image1;
    }

    /**
     * @param image the image1 to set
     */
    public void setImage1(String image) {
        this.image1 = image;
    }
    
    
    /**
     * @return the image2
     */
    public String getImage2() {
        return image2;
    }

    /**
     * @param image the image2 to set
     */
    public void setImage2(String image) {
        this.image2 = image;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDatefin() {
        return datefin;
    }

    public void setDatefin(Timestamp datefin) {
        this.datefin = datefin;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the prix
     */
    public float getPrix() {
        return prix;
    }

    /**
     * @param prix the prix to set
     */
    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdSousCat() {
        return idSousCat;
    }

    public void setIdSousCat(int idSousCat) {
        this.idSousCat = idSousCat;
    }


    public boolean expired(){
        return this.datefin.before(new Timestamp(System.currentTimeMillis()));
    }

    public int getQnt() {
        return qnt;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getVendu() {
        return vendu;
    }

    public void setVendu(int vendu) {
        this.vendu = vendu;
    }
}
