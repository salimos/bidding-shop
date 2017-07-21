package com.projectjee.model;

/**
 * Created by Marwen on 4/24/17.
 */
public class PanierEnchaireItem {
    private int iduser;
    private int idProduit;

    public PanierEnchaireItem(int iduser, int idProduit) {
        this.iduser = iduser;
        this.idProduit = idProduit;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }
}
