/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectjee.dao;

import com.projectjee.util.DataBaseConnector;
import com.projectjee.model.Produit;
import com.projectjee.util.SessionUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Marwen
 */
@ManagedBean(name = "produitDao")
@RequestScoped
public class ProduitDao {

    @ManagedProperty(value="#{param.idProd}")
    private int idProd;

    private String nom;
    private String image1;
    private String image2;
    private String description;
    private int type;
    private float prix;
    private Date datefin;

    private Part photo1;
    private Part photo2;

    private int id_sous_cat;

    private int qnt = 1;

    public int getQnt() {
        return qnt;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
    }

    private DataBaseConnector db = DataBaseConnector.getDatabaseConnector();

    /**
     * Creates a new instance of ProduitDao
     */
    public ProduitDao() {
    }

    public ArrayList<Produit> all(){
        return all(new HashMap<String,String>());
    }

    public ArrayList<Produit> all(Map<String,String> params) {

        String query;
        
        if(params.get("categorie_id") == null && params.get("souscategorie_id") == null){
            query = "Select * from produits where (type=2 or (type=1 and datefin > now())) and qnt>0 ";
        }else{
            if(params.get("souscategorie_id") != null){
                query = "Select * from produits where (type=2 or (type=1 and datefin > now())) and qnt>0 and id_sous_cat=" + params.get("souscategorie_id");
            }else{
                query = "Select * from produits where (type=2 or (type=1 and datefin > now())) and qnt>0 and id_sous_cat in (SELECT id FROM `souscategories` where categorie_id=" + params.get("categorie_id") + ")";
            }
        }
        
        if(params.get("search") != null){
            if(query.contains("where")){
                query += " AND `nom` LIKE '%" + params.get("search") +"%'";
            }else{
                query += " WHERE (type=2 or (type=1 and datefin > now())) and qnt>0 and `nom` LIKE '%" + params.get("search") +"%'";
            }
        }


        query += " ORDER BY `id` DESC";
        
        ArrayList<Produit> list = new ArrayList<Produit>();
        try {

            ResultSet rs = db.getResults(query);
            while (rs.next()) {
                list.add(new Produit(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("image1"),
                        rs.getString("image2"),
                        rs.getString("description"),
                        rs.getInt("type"),
                        rs.getFloat("prix"),
                        rs.getTimestamp("datefin"),
                        rs.getInt("id_user"),
                        rs.getInt("id_sous_cat"),
                        rs.getInt("etat"),
                        rs.getInt("qnt"),
                        rs.getInt("vendu")
                ));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Produit> allbyUser(int id) {

        String query = "Select * from produits where id_user="+id;

        ArrayList<Produit> list = new ArrayList<Produit>();
        try {

            ResultSet rs = db.getResults(query);
            while (rs.next()) {
                list.add(new Produit(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("image1"),
                        rs.getString("image2"),
                        rs.getString("description"),
                        rs.getInt("type"),
                        rs.getFloat("prix"),
                        rs.getTimestamp("datefin"),
                        rs.getInt("id_user"),
                        rs.getInt("id_sous_cat"),
                        rs.getInt("etat"),
                        rs.getInt("qnt"),
                        rs.getInt("vendu")
                ));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }


    public ArrayList<Produit> activeEnchairebyUser(int id) {

        String query = "Select DISTINCT * from produits where type=1 and etat=0 and datefin > now() and id in (Select id_produit from encheres where id_user="+ id +")";

        ArrayList<Produit> list = new ArrayList<Produit>();
        try {

            ResultSet rs = db.getResults(query);
            while (rs.next()) {
                list.add(new Produit(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("image1"),
                        rs.getString("image2"),
                        rs.getString("description"),
                        rs.getInt("type"),
                        rs.getFloat("prix"),
                        rs.getTimestamp("datefin"),
                        rs.getInt("id_user"),
                        rs.getInt("id_sous_cat"),
                        rs.getInt("etat"),
                        rs.getInt("qnt"),
                        rs.getInt("vendu")
                ));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }


    public Produit one(int id){
        
        try {

            ResultSet rs = db.getResults("Select * from produits where id=" + id);
            if (rs.next()) {
                return (new Produit(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("image1"),
                        rs.getString("image2"),
                        rs.getString("description"),
                        rs.getInt("type"),
                        rs.getFloat("prix"),
                        rs.getTimestamp("datefin"),
                        rs.getInt("id_user"),
                        rs.getInt("id_sous_cat"),
                        rs.getInt("etat"),
                        rs.getInt("qnt"),
                        rs.getInt("vendu")
                ));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }



    public Produit one(String id){
            return one(Integer.parseInt(id));
    }


    public List<Produit> getExpiredProducts(){
        String query = "Select * from produits where type=1 and etat=0 and datefin < now()";
        ArrayList<Produit> list = new ArrayList<Produit>();
        try {
            ResultSet rs = db.getResults(query);
            while (rs.next()) {
                list.add(new Produit(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("image1"),
                        rs.getString("image2"),
                        rs.getString("description"),
                        rs.getInt("type"),
                        rs.getFloat("prix"),
                        rs.getTimestamp("datefin"),
                        rs.getInt("id_user"),
                        rs.getInt("id_sous_cat"),
                        rs.getInt("etat"),
                        rs.getInt("qnt"),
                        rs.getInt("vendu")
                ));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void save() {

        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String prefix = Long.toString(timestamp.getTime());

            InputStream in = photo1.getInputStream();

            File f = new File("/Users/Marwen/Developer/pjee/web/uploads/" + prefix + photo1.getSubmittedFileName());
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }

            out.close();
            in.close();

            InputStream in2 = photo2.getInputStream();
            File f2 = new File("/Users/Marwen/Developer/pjee/web/uploads/" + prefix + photo2.getSubmittedFileName());
            f2.createNewFile();
            FileOutputStream out2 = new FileOutputStream(f2);

            byte[] buffer2 = new byte[1024];
            int length2;

            while ((length2 = in2.read(buffer2)) > 0) {
                out2.write(buffer2, 0, length2);
            }
            out2.close();
            in2.close();



            db.execUpdate("INSERT INTO `produits` (`id`, `nom`, `description`, `datefin`, `prix`, `type`, `image1`, `image2`, `id_user`, `id_sous_cat`,`qnt`)VALUES(NULL, '"
                    + nom + "', '"
                    + description + "', ' "
                    + new Timestamp(datefin.getTime()) + "', "
                    + prix + ", "
                    + type + ", '"
                    + "/uploads/" + prefix + photo1.getSubmittedFileName() + "', '"
                    + "/uploads/" + prefix + photo2.getSubmittedFileName() + "',"
                    + SessionUtils.getUserId() + ","
                    + id_sous_cat + ","
                    + qnt
                    + ");");



            nom = "";
            description = "";
            datefin = new Date();
            photo1 = null;
            photo2 = null;
            prix = 0;
            FacesContext.getCurrentInstance().addMessage("msg",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Succès", "Produit ajouté"));

        } catch (IOException ex) {
            Logger.getLogger(ProduitDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update() {

    }

    public void updateExpired(int id,float prix){
        db.execUpdate("UPDATE `produits` SET `etat` = '1',`prix` = '" + prix +"' WHERE `id` = '" + id  +"';");
    }


    public void delete(){
        db.execUpdate("delete from produits where id=" + idProd);
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
     * @param image1 the image1 to set
     */
    public void setImage1(String image1) {
        this.image1 = image1;
    }

    /**
     * @return the image2
     */
    public String getImage2() {
        return image2;
    }

    /**
     * @param image2 the image2 to set
     */
    public void setImage2(String image2) {
        this.image2 = image2;
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

    /**
     * @return the datefin
     */
    public Date getDatefin() {
        return datefin;
    }

    /**
     * @param datefin the datefin to set
     */
    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    /**
     * @return the photo1
     */
    public Part getPhoto1() {
        return photo1;
    }

    /**
     * @param photo1 the photo1 to set
     */
    public void setPhoto1(Part photo1) {
        this.photo1 = photo1;
    }

    /**
     * @return the photo2
     */
    public Part getPhoto2() {
        return photo2;
    }

    /**
     * @param photo2 the photo2 to set
     */
    public void setPhoto2(Part photo2) {
        this.photo2 = photo2;
    }

    public void setId_sous_cat(int id_sous_cat) {
        this.id_sous_cat = id_sous_cat;
    }

    public int getId_sous_cat() {
        return id_sous_cat;
    }


    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }
}
