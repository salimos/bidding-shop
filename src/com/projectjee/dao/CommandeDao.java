package com.projectjee.dao;

import com.projectjee.model.Commande;
import com.projectjee.model.PanierItem;
import com.projectjee.util.Authentification;
import com.projectjee.util.DataBaseConnector;
import com.projectjee.util.Panier;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Marwen on 4/23/17.
 */
@ManagedBean(name = "CommandeDao")
@RequestScoped
public class CommandeDao {

    private DataBaseConnector db = DataBaseConnector.getDatabaseConnector();

    @ManagedProperty(value="#{authentification}")
    private Authentification authentification;


    @ManagedProperty(value="#{PanierEnchaireDao}")
    private PanierEnchaireDao panierEnchaireDao;

    public DataBaseConnector getDb() {
        return db;
    }

    public void setDb(DataBaseConnector db) {
        this.db = db;
    }

    public PanierEnchaireDao getPanierEnchaireDao() {
        return panierEnchaireDao;
    }

    public void setPanierEnchaireDao(PanierEnchaireDao panierEnchaireDao) {
        this.panierEnchaireDao = panierEnchaireDao;
    }

    @ManagedProperty(value="#{Panier}")
    private Panier panier;

    private String nom;
    private String prenom;
    private String addr1;
    private String addr2;
    private String ville;
    private String notes;

    @ManagedProperty(value="#{param.totale}")
    private float totale;
    private Timestamp date;

    public CommandeDao() {
    }


    public List<Commande> all(){
        List<Commande> list = new ArrayList<Commande>();
        try {


            ResultSet rs = db.getResults("SELECT * FROM `commandes`");
            while(rs.next()){
                list.add(
                    new Commande(rs.getInt(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getFloat(9),
                            rs.getTimestamp(10))
                );
            }
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Commande> allbyuser(){
        List<Commande> list = new ArrayList<Commande>();
        try {


            ResultSet rs = db.getResults("SELECT * FROM `commandes` where user_id="+ authentification.getId());
            while(rs.next()){
                list.add(
                        new Commande(rs.getInt(1),
                                rs.getInt(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5),
                                rs.getString(6),
                                rs.getString(7),
                                rs.getString(8),
                                rs.getFloat(9),
                                rs.getTimestamp(10))
                );
            }
            rs.close();


        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<PanierItem> getProduits(int id){
        List<PanierItem> list = new ArrayList<PanierItem>();
        try {
            ResultSet rs = db.getResults("SELECT * FROM `commande_produit` where id_commande="+ id);
            while(rs.next()){
                list.add(
                        new PanierItem(rs.getInt(2),rs.getInt(3))
                );
            }
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public String ajouter(){

        int commid = db.execUpdate("INSERT INTO `commandes` (`id`, `user_id`, `nom`, `prenom`, `addr1`, `addr2`, `ville`, `notes`, `totale`, `date`) VALUES(NULL, " +
                authentification.getId() + ", '" +
                nom + "', '" +
                prenom + "', '" +
                addr1 + "', '" +
                addr2 + "', '" +
                ville + "', '" +
                notes + "', " +
                totale + ", NOW()" +
                ");");

        for (PanierItem pi : panier.list()) {
            db.execUpdate("INSERT INTO `commande_produit` (`id_commande`, `id_produit`, `qnt`) VALUES ('" +
                    commid + "', '" +
                    pi.getProduit() + "', '" +
                    pi.getQnt() + "');");
            db.execUpdate("UPDATE produits SET qnt=qnt-"+ pi.getQnt() +", vendu= vendu+" +pi.getQnt() + " where id =" + pi.getProduit());
            panierEnchaireDao.delete(authentification.getId(),pi.getProduit());
        }



        panier.vider();
        FacesContext.getCurrentInstance().addMessage("msg",
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Succès", "Commande validé"));

        return "home";
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Authentification getAuthentification() {
        return authentification;
    }

    public void setAuthentification(Authentification authentification) {
        this.authentification = authentification;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }
}
