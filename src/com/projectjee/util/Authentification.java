/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectjee.util;

import com.projectjee.dao.CategorieDao;
import com.projectjee.dao.UserDao;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marwen
 */
@ManagedBean(name = "authentification")
@SessionScoped
public class Authentification implements Serializable {

    
    private String email;
    private String password;
    private int id;
    private boolean connected = false;
    private boolean admin = false;

    //@ManagedProperty(value="#{userDao}")
    private UserDao userDao;
    /**
     * Creates a new instance of Authentification
     */
    public Authentification() {
    }
    
    public String login(){
        System.out.println("In login with " + email + " " + password);
        userDao = new UserDao();
        if(email.equals("admin") && password.equals("admin")){
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", "admin");
            session.setAttribute("userid", "0");
            session.setAttribute("role", "admin");
            connected = true;
            admin = true;
            System.out.println("In");
            return "admin";
        }



        if( (id = getUserDao().exist(getEmail(), getPassword())) != -1){
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", getEmail());
            session.setAttribute("userid", Integer.toString(id));
            session.setAttribute("role", "user");
            connected = true;
            System.out.println("In");
            return "home";
        }else{
            System.out.println("Fail");
            email = "";
            password = "";
            connected = false;
            FacesContext.getCurrentInstance().addMessage("msg",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Erreur", "Email ou mot de passe incorrecte"));
            return "inscription";
        }
    }
    
    public String logout() {
		HttpSession session = SessionUtils.getSession();
        connected = false;
		session.invalidate();
		return "login";
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the userDao
     */
    public UserDao getUserDao() {
        return userDao;
    }

    /**
     * @param userDao the userDao to set
     */
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
