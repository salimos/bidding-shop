package com.projectjee.dao;

import com.projectjee.model.Enchere;
import com.projectjee.model.Produit;
import com.projectjee.util.Authentification;
import com.projectjee.util.DataBaseConnector;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Marwen on 4/23/17.
 */
@ManagedBean(name = "EnchereDao")
public class EnchereDao {

    private DataBaseConnector db = DataBaseConnector.getDatabaseConnector();



    @ManagedProperty(value="#{authentification}")
    private Authentification authentification;


    @ManagedProperty(value="#{param.produitId}")
    private int idProduit;
    private float prix;


    public EnchereDao() {
    }

    public List<Enchere> all(){


        ArrayList<Enchere> list = new ArrayList<Enchere>();
        try {

            ResultSet rs = db.getResults("Select * from encheres");
            while(rs.next()){
                list.add(new Enchere(rs.getInt(1),rs.getInt(2),rs.getFloat(3),rs.getTimestamp(4)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public List<Enchere> allByUser(int id){


        ArrayList<Enchere> list = new ArrayList<Enchere>();
        try {

            ResultSet rs = db.getResults("Select * from encheres where id_user=" + id);
            while(rs.next()){
                list.add(new Enchere(rs.getInt(1),rs.getInt(2),rs.getFloat(3),rs.getTimestamp(4)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public List<Enchere> allByProduit(int id){


        ArrayList<Enchere> list = new ArrayList<Enchere>();
        try {

            ResultSet rs = db.getResults("Select * from encheres where id_produit=" + id + " ORDER BY `prix` DESC LIMIT 5");
            while(rs.next()){
                list.add(new Enchere(rs.getInt(1),rs.getInt(2),rs.getFloat(3),rs.getTimestamp(4)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }


    public Enchere oneByProduit(int id){
        try {
            ResultSet rs = db.getResults("Select * from encheres where id_produit=" + id + " ORDER BY `prix` DESC LIMIT 1");
            if(rs.next()){
                return (new Enchere(rs.getInt(1),rs.getInt(2),rs.getFloat(3),rs.getTimestamp(4)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public void ajouter(){
        db.execUpdate("INSERT INTO `encheres` (`id_user`, `id_produit`, `prix`, `date`) VALUES (" +  authentification.getId() + ", " + idProduit +" , " + prix +", NOW());");
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

    public Authentification getAuthentification() {
        return authentification;
    }

    public void setAuthentification(Authentification authentification) {
        this.authentification = authentification;
    }

    public float max(Produit p,List<Enchere> e){
        if(e.size() == 0){
            return (p.getPrix()+1F);
        }
        return e.get(0).getPrix()+1F;
    }
}
