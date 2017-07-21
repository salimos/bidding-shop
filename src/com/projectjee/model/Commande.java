package com.projectjee.model;

import com.projectjee.util.Panier;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Marwen on 4/23/17.
 */
public class Commande {
    private int id;
    private int userId;
    private String nom;
    private String prenom;
    private String addr1;
    private String addr2;
    private String ville;
    private String notes;
    private float totale;
    private Timestamp date;

    private List<PanierItem> produits;

    public Commande(int id, int userId, String nom, String prenom, String addr1, String addr2, String ville, String notes, float totale, Timestamp date) {
        this.id = id;
        this.userId = userId;
        this.nom = nom;
        this.prenom = prenom;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.ville = ville;
        this.notes = notes;
        this.totale = totale;
        this.date = date;
    }

    public Commande() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public float getTotale() {
        return totale;
    }

    public void setTotale(float totale) {
        this.totale = totale;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getDate() {
        return date;
    }

    public List<PanierItem> getProduits() {
        return produits;
    }

    public void setProduits(List<PanierItem> produits) {
        this.produits = produits;
    }
}
