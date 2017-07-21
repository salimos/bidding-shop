package com.projectjee.model;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by Marwen on 4/23/17.
 */
public class Enchere {
    private int idUser;
    private int idProduit;
    private float prix;
    private Timestamp date;

    public Enchere() {
    }

    public Enchere(int idUser, int idProduit, float prix, Timestamp date) {

        this.idUser = idUser;
        this.idProduit = idProduit;
        this.prix = prix;
        this.date = date;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Enchere{" +
                "idUser=" + idUser +
                ", idProduit=" + idProduit +
                ", prix=" + prix +
                ", date=" + date +
                '}';
    }
}
