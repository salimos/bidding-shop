package com.projectjee.util;

import com.projectjee.dao.CategorieDao;
import com.projectjee.dao.EnchereDao;
import com.projectjee.dao.PanierEnchaireDao;
import com.projectjee.dao.ProduitDao;
import com.projectjee.model.PanierEnchaireItem;
import com.projectjee.model.PanierItem;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Marwen on 4/22/17.
 */

@ManagedBean(name = "Panier")
@RequestScoped
public class Panier {



    @ManagedProperty(value="#{param.idprod}")
    private int idprod;
    //@ManagedProperty(value="#{param.qnt}")
    private int qnt = 1;
    @ManagedProperty(value="#{PanierEnchaireDao}")
    private PanierEnchaireDao panierEnchaireDao;
    @ManagedProperty(value="#{authentification}")
    private Authentification authentification;
    public Panier() {
    }

    public void ajouter(){


        Cookie pc = CookieHelper.getCookie("panier");
        if(pc == null){
            pc = new Cookie("panier", "");
        }
        String list = pc.getValue();
        if(list.contains(idprod+":")){
            String l2 = "";
            String commands[] = list.split(",");
            for (String command :
                    commands) {
                if(!command.equals("")){
                    String tmp[] = command.split(":");
                    if(Integer.parseInt(tmp[0]) == idprod){
                        int q2 = qnt + Integer.parseInt(tmp[1]);
                        l2 += idprod + ":" + q2 + ",";
                    }else{
                        l2 +=tmp[0] + ":" + tmp[1] + ",";
                    }
                }
            }
            list = l2;
        }else{
            list +=idprod + ":" + qnt + ",";
        }



        FacesContext.getCurrentInstance().addMessage("msg",
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Succès", "Produit ajouté au panier"));

        System.out.println("Panier updated :" + list);
        CookieHelper.setCookie("panier",list,31536000);


    }

    public List<PanierItem> list(){
        List<PanierItem> l = new ArrayList<PanierItem>();
        Cookie pc = CookieHelper.getCookie("panier");

        if(pc == null){
           pc = new Cookie("panier", "");
        }
        String list = pc.getValue();

        System.out.println("Panier :" + list);


        if(authentification.isConnected()){
            for(PanierEnchaireItem p: panierEnchaireDao.AllbyUser(authentification.getId())){
                if(!list.contains(p.getIdProduit() + ":")){
                    list += p.getIdProduit() + ":1,";
                }
            }
        }

        if(!list.equals("")){

            String commands[] = list.split(",");

            for (String command :
                    commands) {
                if(!command.equals("")){
                    String tmp[] = command.split(":");
                    l.add(new PanierItem(tmp[0],tmp[1]));

                }
            }
        }




        return l;
    }


    public void delete(){
        Cookie pc = CookieHelper.getCookie("panier");
        if(pc == null){
            pc = new Cookie("panier", "");
        }
        String list = pc.getValue();


            String commands[] = list.split(",");
            list = "";
            for (String command :
                    commands) {
                if(!command.equals("")){
                    String tmp[] = command.split(":");
                    if(Integer.parseInt(tmp[0]) != idprod){
                        list +=tmp[0] + ":" + tmp[1] + ",";
                    }
                }
            }
        CookieHelper.setCookie("panier",list,31536000);


    }

    public float total(List<PanierItem> l,ProduitDao produitDao){
        float s =0;

        for (PanierItem p :
                l) {
            s += p.getQnt() * produitDao.one(p.getProduit()).getPrix();
        }

        return s;
    }

    public void vider(){
        CookieHelper.setCookie("panier","",31536000);
    }

    public int getIdprod() {
        return idprod;
    }

    public void setIdprod(int idprod) {
        this.idprod = idprod;
    }

    public int getQnt() {
        return qnt;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
    }

    public PanierEnchaireDao getPanierEnchaireDao() {
        return panierEnchaireDao;
    }

    public void setPanierEnchaireDao(PanierEnchaireDao panierEnchaireDao) {
        this.panierEnchaireDao = panierEnchaireDao;
    }

    public Authentification getAuthentification() {
        return authentification;
    }

    public void setAuthentification(Authentification authentification) {
        this.authentification = authentification;
    }
}
