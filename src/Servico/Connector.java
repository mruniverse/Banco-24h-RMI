/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servico;
import Servidor.Conta;
import java.sql.*;
/**
 *
 * @author yuril
 */
public class Connector {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String CONN_STRING = 
            "jdbc:mysql://localhost:3306/rmi_db";

    public Connector() {
    }
    
    public Connection connect() {
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            System.out.println("Connected...");
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        return conn;
    }
}
