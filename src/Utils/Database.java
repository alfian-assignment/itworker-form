/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Alfian Andi Nugraha
 */
public class Database {
    public static Connection connection;
    public static Connection getConnection() {
        if(Database.connection != null) {
            return Database.connection;
        }
        
        try {
            String url = "jdbc:mysql://localhost:3306/pbop_responsi_5190411404";
            String username = "root";
            String password = "";
            
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Database.connection = DriverManager.getConnection(url, username, password);
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
        
        return Database.connection;
    }
}
