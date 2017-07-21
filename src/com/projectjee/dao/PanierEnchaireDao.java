package com.projectjee.dao;

import com.projectjee.model.PanierEnchaireItem;
import com.projectjee.model.PanierItem;
import com.projectjee.model.Produit;
import com.projectjee.util.DataBaseConnector;
import com.projectjee.util.Panier;
import java.util.List;


import javax.faces.bean.ManagedBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Marwen on 4/24/17.
 */
@ManagedBean(name = "PanierEnchaireDao")
public class PanierEnchaireDao {
    private DataBaseConnector db = DataBaseConnector.getDatabaseConnector();


    public List<PanierEnchaireItem> all(){

        String query = "Select * from PanierEnchaire";
        List<PanierEnchaireItem> list = new ArrayList<PanierEnchaireItem>();
        try {
            ResultSet rs = db.getResults(query);
            while (rs.next()) {
                list.add(new PanierEnchaireItem(rs.getInt(1), rs.getInt(2)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }



    public List<PanierEnchaireItem> AllbyUser(int id){

        String query = "Select * from PanierEnchaire where idUser=" + id;
        List<PanierEnchaireItem> list = new ArrayList<PanierEnchaireItem>();
        try {
            ResultSet rs = db.getResults(query);
            while (rs.next()) {
                list.add(new PanierEnchaireItem(rs.getInt(1), rs.getInt(2)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void ajouter(int iduser,int idproduit){
        db.execUpdate("INSERT INTO `PanierEnchaire` (`idUser`, `idproduit`) VALUES ('" + iduser +"', '"+ idproduit +"');");
    }


    public void delete(int iduser,int idproduit){
        db.execUpdate("DELETE FROM PanierEnchaire where idUser=" + iduser + " and idproduit=" + idproduit);
    }

}
