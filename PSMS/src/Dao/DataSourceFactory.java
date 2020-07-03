/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Davide
 */
class DataSourceFactory {

    public static Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                     "DAVIDE",
                     "Admin1234"
            );
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("ERRORE");
        }

        return conn;

    }
    
}
