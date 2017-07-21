package com.projectjee.model;

import com.projectjee.dao.CategorieDao;

import javax.faces.bean.ManagedProperty;

/**
 * Created by Marwen on 4/23/17.
 */
public class PanierItem {
    private int produit;
    private int qnt;



    public PanierItem(String produit, String qnt) {
        this.produit = Integer.parseInt(produit);
        this.qnt = Integer.parseInt(qnt);
    }

    public PanierItem(int produit, int qnt) {
        this.produit = produit;
        this.qnt = qnt;
    }

    public int getProduit() {
        return produit;
    }

    public void setProduit(int produit) {
        this.produit = produit;
    }

    public int getQnt() {
        return qnt;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PanierItem that = (PanierItem) o;

        if (produit != that.produit) return false;
        return qnt == that.qnt;
    }

    @Override
    public int hashCode() {
        int result = produit;
        result = 31 * result + qnt;
        return result;
    }
}
