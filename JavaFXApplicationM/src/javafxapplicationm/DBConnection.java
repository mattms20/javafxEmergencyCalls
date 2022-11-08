/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplicationm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author Matt Markopoulos Pipinis
 */

//to establish connection to the database
public class DBConnection {
    public  static Connection conn=null;
    
    public static Connection getConnection() {
        String dbName = "callssos";
        String dbUser ="root";
        String dbPass ="";
        String url = "jdbc:mysql://localhost/"+dbName;
        
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= (Connection)DriverManager.getConnection(url,dbUser,dbPass);
            //JOptionPane.showMessageDialog(null, "Connection Established");
        }
        catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null, e);
                    e.printStackTrace();
                }
        
        return conn;
 
    }
    //to get all the calls about 100
    public static ObservableList<Calls> get100Calls() throws SQLException{
        
        conn= getConnection();
        ObservableList<Calls> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps=conn.prepareStatement("select * from calls where division = '100'");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){

                list.add(new Calls(Integer.parseInt(rs.getString("callID")),rs.getString("phone"),rs.getString("name"),rs.getString("surname"),rs.getString("city"),rs.getString("address"),rs.getString("division"),rs.getString("incident"),rs.getString("time")));
                
            }
                
        }
        catch(Exception e){
        JOptionPane.showMessageDialog(null, e);}
                    
                     
    return list;
    }
    //to get all the calls
    public static ObservableList<Calls> getDataCalls() throws SQLException{
        
        conn= getConnection();
        ObservableList<Calls> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps=conn.prepareStatement("select * from calls");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){

                list.add(new Calls(Integer.parseInt(rs.getString("callID")),rs.getString("phone"),rs.getString("name"),rs.getString("surname"),rs.getString("city"),rs.getString("address"),rs.getString("division"),rs.getString("incident"),rs.getString("time")));
                
            }
                
        }
        catch(Exception e){
        JOptionPane.showMessageDialog(null, e);}
                    
                     
    return list;
    }
    //to get all the calls about 199
    public static ObservableList<Calls> get199Calls() throws SQLException{
        
        conn= getConnection();
        ObservableList<Calls> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps=conn.prepareStatement("select * from calls where division ='199'");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){

                list.add(new Calls(Integer.parseInt(rs.getString("callID")),rs.getString("phone"),rs.getString("name"),rs.getString("surname"),rs.getString("city"),rs.getString("address"),rs.getString("division"),rs.getString("incident"),rs.getString("time")));
                
            }
                
        }
        catch(Exception e){
        JOptionPane.showMessageDialog(null, e);}
                    
                     
    return list;
    }
    //to get all the calls about 166
                public static ObservableList<Calls> get166Calls() throws SQLException{
        
        conn= getConnection();
        ObservableList<Calls> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps=conn.prepareStatement("select * from calls where division = '166'");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){

                list.add(new Calls(Integer.parseInt(rs.getString("callID")),rs.getString("phone"),rs.getString("name"),rs.getString("surname"),rs.getString("city"),rs.getString("address"),rs.getString("division"),rs.getString("incident"),rs.getString("time")));
                
            }
                
        }
        catch(Exception e){
        JOptionPane.showMessageDialog(null, e);}
                    
                     
    return list;
    }
    
    
}