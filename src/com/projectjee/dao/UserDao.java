/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectjee.dao;

import com.projectjee.util.DataBaseConnector;
import com.projectjee.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * @author Marwen
 */
@ManagedBean(name = "userDao")
@RequestScoped
public class UserDao {


    private String nom;
    private String prenom;
    private String tel;
    private String password;
    private String password2;
    private String email;

    private DataBaseConnector db = DataBaseConnector.getDatabaseConnector();

    /**
     * Creates a new instance of UserDao
     */
    public UserDao() {
    }


    public int exist(String email, String password) {
        try {


            ResultSet rs = db.getResults("Select * from users where email='" + email + "' and password='" + password + "'");
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<User> all() {
        ArrayList<User> list = new ArrayList<User>();
        try {


            ResultSet rs = db.getResults("Select * from users");
            while (rs.next()) {
                list.add(new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public User one(int id) {
        try {

            ResultSet rs = db.getResults("Select * from users where id=" + id);
            if (rs.next()) {
                return (new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void ajouter() {
        if (password.equals(password2)) {


            db.execUpdate("INSERT INTO `users` (`id`, `nom`, `password`, `email`, `prenom`, `tel`) VALUES (NULL, '" + nom + "', '" + password + "', '" + email + "', '" + prenom + "', '" + tel + "');");
            nom = "";
            password = "";
            email = "";
            prenom = "";
            tel = "";
            password2 = "";

            FacesContext.getCurrentInstance().addMessage("msg",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Succès", "Utilisateur inscrit, veuiller se connecter"));
        } else {
            FacesContext.getCurrentInstance().addMessage("msg",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Erreur", "Le mot de passe et la confirmation ne correspondent pas"));
            password = "";
            password2 = "";
        }
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
