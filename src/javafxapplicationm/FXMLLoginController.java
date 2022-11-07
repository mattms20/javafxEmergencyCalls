/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplicationm;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Matt Markopoulos
 */
public class FXMLLoginController implements Initializable {
    static String menuUsername="";
    Parent root=null;
    
    @FXML
private AnchorPane content;
    
        @FXML
    private Button buttonGotoRegister;

    @FXML
    private Button buttonLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

     /*   @FXML
    void goToRegister(ActionEvent event){

        JOptionPane.showMessageDialog(null, "Loading...");
    }*/
    
    @FXML
    void login() {
        


        String username = txtUsername.getText();
        String password = txtPassword.getText();
    
        if (username.equals("") && password.equals(""))   
        {
            JOptionPane.showMessageDialog(null, "Username or Password Blank");
        }
        else if (password.equals(""))
        {
             JOptionPane.showMessageDialog(null, "Password Blank");
        }
        else if (username.equals(""))
        {
             JOptionPane.showMessageDialog(null, "Username Blank");//or
        }
        else
        {
         validateLogin(username, password);
        }

    }
    /*@FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }*/
    //validate login
    public void validateLogin(String username, String password){
        DBConnection connectNow = new DBConnection();
        Connection connectDB = connectNow.getConnection();
        
        String verifyLoginSQL ="SELECT * FROM users WHERE username= '"+username+"' AND password ='"+password+"'";
        
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLoginSQL);
            
            
                if (queryResult.next())
                {
                   JOptionPane.showMessageDialog(null, "Login Success");//or
                   menuUsername=username;
                       buttonLogin.getScene().getWindow().hide();
                       
                       Parent root1 = FXMLLoader.load(getClass().getResource("FXMLMenu.fxml"));
                       Stage mainStage = new Stage();
                       mainStage.setTitle("*****ΚΛΗΣΕΙΣ ΕΚΤΑΚΤΗΣ ΑΝΑΓΚΗΣ*****");
                       Scene scene = new Scene(root1);
                       mainStage.setScene(scene);  
                       mainStage.setResizable(false);
                       mainStage.show();
                }
                else
                {
                   JOptionPane.showMessageDialog(null, "Invalid credentials, please try again");
                    txtUsername.setText("");
                    txtPassword.setText("");
                    txtUsername.requestFocus();//or
                }
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        //button to go to register page
    }
    @FXML
    void gotoRegister()
    {
        try{
                               buttonLogin.getScene().getWindow().hide();
                       
                       Parent root1 = FXMLLoader.load(getClass().getResource("FXMLRegister.fxml"));
                       Stage mainStage = new Stage();
                       mainStage.setTitle("Register");
                       Scene scene = new Scene(root1);
                       mainStage.setScene(scene);  
                       
                       mainStage.show();}
        catch(Exception e)
        {e.printStackTrace();}
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtUsername.setOnAction(e->txtPassword.requestFocus());
        txtPassword.setOnAction(e->login());
    }    
    
}
