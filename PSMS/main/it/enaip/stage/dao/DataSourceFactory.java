/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.enaip.stage.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Davide 
 */
public class DataSourceFactory {

    public static Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                     "DAVIDE",
                     "Admin1234"
            );
        } catch (Exception e) {
        	throw(new RuntimeException(e));
        }

        return conn;

    }
    
}
