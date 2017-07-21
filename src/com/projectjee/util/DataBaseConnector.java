/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectjee.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 *
 * @author Marwen
 */
public class DataBaseConnector {

    private Connection con;
    private static DataBaseConnector db;
    
    public static DataBaseConnector getDatabaseConnector(){
        if(db == null)
            db = new DataBaseConnector("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1/projetjee", "root", "");
        return db;
    }

    public DataBaseConnector(String driver, String url, String username, String password) {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement du driver:" + e.getMessage());
        }
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("Erreur lors connection " + e.getMessage());
        }

    }

    public ResultSet getResults(String q) {
        ResultSet rs = null;
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(q);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       
        return rs;
    }

    public int execUpdate(String q) {
        int x = 0;
        try {
            Statement stmt = con.createStatement();
            x = stmt.executeUpdate(q, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                x = rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return x;
    }
}
