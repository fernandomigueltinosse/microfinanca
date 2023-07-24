/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;


public class DB {
    private static Connection conn = null;
    
    
    public static Connection getConnection(){
        
      if (conn == null) {
            try {
                Properties props = loadProperties();
                String port = (String) props.get("port");
                String database = (String) props.get("database");
                String host = (String) props.get("host");
                String user = (String) props.get("user");
                String password = (String) props.get("password");
                String url = "jdbc:mysql://" + host + ":" + port + "/" + database+"?autoReconnect=true&useSSL=false";
                conn = DriverManager.getConnection(url,props);

                // Verificar se a conexão foi estabelecida com sucesso
                if (conn != null) {
                    System.out.println("Conexão com o banco de dados realizada com sucesso!");
                } else {
                    System.out.println("Erro ao conectar-se ao banco de dados.");
                    return null; // Retorna null caso a conexão falhe
                }
            } catch (SQLException e) {
                //JOptionPane.showMessageDialog(null, e.getMessage());
                return null; // Retorna null caso a conexão falhe
            }
        }
        return conn;
    }

    ////////////////////////////////////////////////////////////////////////////
    private static Properties loadProperties(){
        try(FileInputStream fis=new FileInputStream("src/db/config.properties")) {
             Properties props =new Properties ();
             props.load (fis);
            return  props;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }
////////////////////////////////////////////////////////////////////////////////
    public static void closeStatement(Statement st){
        try {
            if(st!=null){
                st.close();
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
     /////////////////////////////////////////////////////////////////////////////
      public static void closeResultSet(ResultSet rs){
        try {
            if(rs!=null){
                rs.close();
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
    
    /////////////////////////////////////////////////////////////////////////////
    public static void closeConnection(){
        try {
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
    

