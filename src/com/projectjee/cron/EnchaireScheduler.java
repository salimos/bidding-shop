package com.projectjee.cron;

import com.projectjee.dao.EnchereDao;
import com.projectjee.dao.PanierEnchaireDao;
import com.projectjee.dao.ProduitDao;
import com.projectjee.model.Enchere;
import com.projectjee.model.Produit;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.faces.bean.ManagedProperty;
import java.util.List;

/**
 * Created by Marwen on 4/24/17.
 */
@Singleton
public class EnchaireScheduler {

    @ManagedProperty(value="#{produitDao}")
    private ProduitDao produitDao;
    @ManagedProperty(value="#{EnchereDao}")
    private EnchereDao enchereDao;

    @ManagedProperty(value="#{PanierEnchaireDao}")
    private PanierEnchaireDao panierEnchaireDao;

    @Schedule(hour = "*", minute = "*", second = "*/2", persistent = false)
    private void updateEnchaire(){
        System.out.println("Cron job started");
        if(produitDao == null){
            produitDao = new ProduitDao();
        }
        if(enchereDao == null){
            enchereDao = new EnchereDao();
        }
        if(panierEnchaireDao == null){
            panierEnchaireDao = new PanierEnchaireDao();
        }
        List<Produit> list = produitDao.getExpiredProducts();

        for (Produit p : list) {
            Enchere e = enchereDao.oneByProduit(p.getId());
            System.out.println("Cron : " + e);
            if(e!=null){
                panierEnchaireDao.ajouter(e.getIdUser(),p.getId());
                produitDao.updateExpired(p.getId(),e.getPrix());
            }
        }
        System.out.println("Cron job ended");
    }

    public ProduitDao getProduitDao() {
        return produitDao;
    }

    public void setProduitDao(ProduitDao produitDao) {
        this.produitDao = produitDao;
    }

    public EnchereDao getEnchereDao() {
        return enchereDao;
    }

    public void setEnchereDao(EnchereDao enchereDao) {
        this.enchereDao = enchereDao;
    }

    public PanierEnchaireDao getPanierEnchaireDao() {
        return panierEnchaireDao;
    }

    public void setPanierEnchaireDao(PanierEnchaireDao panierEnchaireDao) {
        this.panierEnchaireDao = panierEnchaireDao;
    }
}
