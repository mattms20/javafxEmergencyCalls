/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplicationm;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Matt Markopoulos
 */
public class FXMLRegisterController implements Initializable {
    

    @FXML
    private Button buttonGotoLogin;

    @FXML
    private Button buttonRegister;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFullname;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;


            Connection con;
        PreparedStatement pst;
        ResultSet rs;
        
        //register validation
    @FXML
    void register() {
        


        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String fullname = txtFullname.getText();
        String email = txtEmail.getText();
    
        if (username.equals("") || password.equals("")||email.equals("")||fullname.equals(""))   
        {
            JOptionPane.showMessageDialog(null, "Παρακαλώ συμπληρώστε όλα τα πεδία");
        }

        else
        {
            try {
                con = DBConnection.getConnection();
                
                pst =con.prepareStatement("select * from users where username=?");
                
                pst.setString(1,username);
                
                
                rs =pst.executeQuery();
                
                if (rs.next())
                {
                    JOptionPane.showMessageDialog(null, "This username is already taken");
                    
                    txtUsername.requestFocus();
                }
                else
                {
                    addUsers(username,password,fullname,email);
                    
                    
                }
            }  catch (SQLException ex) {
                Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //button to go to login screen
        @FXML
    void gotoLogin()
    {
        try{
                               buttonRegister.getScene().getWindow().hide();
                       
                       Parent root1 = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
                       Stage mainStage = new Stage();
                       mainStage.setTitle("Login");
                       Scene scene = new Scene(root1);
                       mainStage.setScene(scene);  
                       mainStage.show();}
        catch(Exception e)
        {e.printStackTrace();}
    }
    /*@FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }*/
    //add users after succesfully validating
    private void addUsers(String username,String password,String fullname,String email)
    {
        try{

                con = DBConnection.getConnection();

                pst =con.prepareStatement("INSERT INTO `users` ( `username`, `password`, `fullname`, `email`) VALUES ( '"+username+"', '"+password+"', '"+fullname+"', '"+email+"');");

                pst.execute();
                
                JOptionPane.showMessageDialog(null, "Registered Successfully");
                
                        try{
                        buttonRegister.getScene().getWindow().hide();
                       
                       Parent root1 = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
                       Stage mainStage = new Stage();
                       mainStage.setTitle("Login");
                       Scene scene = new Scene(root1);
                       mainStage.setScene(scene);  
                       mainStage.show();}
                        catch(Exception e)
                        {e.printStackTrace();}

        }
        catch(Exception e)
        {
            
            JOptionPane.showMessageDialog(null, "Register Failed");
}
        
    }

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtUsername.setOnAction(e->txtPassword.requestFocus());
        txtPassword.setOnAction(e->txtFullname.requestFocus());
        txtFullname.setOnAction(e->txtEmail.requestFocus());
        txtEmail.setOnAction(e->register());
    }    
    
}
