/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplicationm;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static javafxapplicationm.DBConnection.getConnection;
import javax.swing.JOptionPane;


/**
 *
 * @author Matt Markopoulos Pipinis
 */
public class FXMLMenuController implements Initializable {
    
    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonUpdate;

    @FXML
    private TableColumn<Calls, Integer> col_ID;

    @FXML
    private TableColumn<Calls, String> col_address;

    @FXML
    private TableColumn<Calls, String> col_city;

    @FXML
    private TableColumn<Calls, String> col_division;

    @FXML
    private TableColumn<Calls, String> col_incident;

    @FXML
    private TableColumn<Calls, String> col_name;

    @FXML
    private TableColumn<Calls, String> col_phone;

    @FXML
    private TableColumn<Calls, String> col_surname;

    @FXML
    private TableView<Calls> tableUsers;
    
    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtDivision;

    @FXML
    private TextArea txtIncident;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtSurname;
    
        @FXML
    private TextField txtID;
        
            @FXML
    private Button buttonEmpty;
            
                @FXML
    private Button buttonLogout;
                
                    @FXML
    private ComboBox comboDivision;
                    
                    
    @FXML
    private ComboBox<?> comboDivisonType;
    
        @FXML
    private TextField txtTime;
        
            @FXML
    private TableColumn<Calls, String> col_time;
            
    @FXML
    private Label labelUsername;

        
       //     @FXML
   // private TextField filterField;

    
    ObservableList<Calls> listM;
    //ObservableList<Calls> dataList;
    
    int index = -1;
    
    Connection conn =null;
    
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    //add calls from form to database
    public void addCalls()
    {
        
        boolean Isnumber;
        try {
            Long.parseLong(txtPhone.getText());
            Isnumber= true;
        } catch (NumberFormatException e) {
            Isnumber=false;
        }
        
        if(Isnumber==false)
        {
            JOptionPane.showMessageDialog(null, "Ο αριθμός τηλεφώνου δεν θα πρέπει να περιέχει γράμματα");
        }
        else if (!txtDivision.getText().equals("100")&&!txtDivision.getText().equals("199")&&!txtDivision.getText().equals("166")){
            JOptionPane.showMessageDialog(null, "Πληκτρολογήστε μια αρψή (πχ. 100, 199, 166)");
        }
        else
        {
        conn = DBConnection.getConnection();
        String sql= "insert into calls (name,surname,phone,city,address,division,incident,time) values(?,?,?,?,?,?,?,?)";
        try{
            pst=conn.prepareStatement(sql);
            pst.setString(1,txtName.getText());
            pst.setString(2,txtSurname.getText());
            pst.setString(3,txtPhone.getText());
            pst.setString(4,txtCity.getText());
            pst.setString(5,txtAddress.getText());
            pst.setString(6,txtDivision.getText());
            pst.setString(7,txtIncident.getText());
            pst.setString(8,txtTime.getText());
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "ΠΕΡΙΣΤΑΤΙΚΟ ΠΡΟΣΤΕΘΗΚΕ");
            updateTable();
            comboDivision.getSelectionModel().select(0);
            
            
        }
        catch(Exception e){
                        JOptionPane.showMessageDialog(null, e);

        }
        }
    }
    
        // get selected from list to form
    @FXML
    void getSelected(MouseEvent event){
        index = tableUsers.getSelectionModel().getSelectedIndex();
        if(index<= -1)
        {
            return;
        }
        txtName.setText(col_name.getCellData(index));
        txtSurname.setText(col_surname.getCellData(index));
        txtPhone.setText(col_phone.getCellData(index));
        txtCity.setText(col_city.getCellData(index));
        txtAddress.setText(col_address.getCellData(index));
        txtDivision.setText(col_division.getCellData(index));
        txtIncident.setText(col_incident.getCellData(index));
        txtTime.setText(col_time.getCellData(index));
        txtID.setText(col_ID.getCellData(index).toString());
        
        
    } 
    
    // change info to already listed call
    public void edit()
    {
        boolean Isnumber;
        try {
            Long.parseLong(txtPhone.getText());
            Isnumber= true;
        } catch (NumberFormatException e) {
            Isnumber=false;
        }
        
        if (txtID.getText().equals(""))
        {JOptionPane.showMessageDialog(null, "Δεν έχετε επιλέξει περιστατικό για να γίνει ανανέωση");}

        
        else if(Isnumber==false)
        {
            JOptionPane.showMessageDialog(null, "Ο αριθμός τηλεφώνου δεν θα πρέπει να περιέχει γράμματα");
        }
        else if (!txtDivision.getText().equals("100")&&!txtDivision.getText().equals("199")&&!txtDivision.getText().equals("166")){
            JOptionPane.showMessageDialog(null, "Πληκτρολογήστε μια αρψή (πχ. 100, 199, 166)");
        }
        else{
        try
        {
        conn = DBConnection.getConnection();
        String value1= txtName.getText();
        String value2= txtSurname.getText();
        String value3= txtPhone.getText();
        String value4= txtCity.getText();
        String value5= txtAddress.getText();
        String value6= txtDivision.getText();
        String value7= txtIncident.getText();
        String value8= txtID.getText();
        String value9= txtTime.getText();
        
        String sql = "update calls set name= '"+value1+"',surname='"+value2+"',phone='"+value3+"',city='"+value4+"',address='"+value5+"',division='"+value6+"',incident='"+value7+"',time='"+value9+"' where callID ='"+value8+"'";
        //
        pst= conn.prepareStatement(sql);
        pst.execute();
        JOptionPane.showMessageDialog(null,"Τα στοιχεία ανανεώθηκαν");
        updateTable();
        comboDivision.getSelectionModel().select(0);
        
        }
        catch(Exception e)
        {
        JOptionPane.showMessageDialog(null, e);
        }
        }
    }
    
    //εκκαθαριση φόρμας
    public void empty()
    {
        txtID.setText("");
        txtName.setText("");
        txtName.requestFocus();//or
        txtSurname.setText("");
        txtCity.setText("");
        txtAddress.setText("");
        txtDivision.setText("");
        txtIncident.setText("");
        txtPhone.setText("");
        txtTime.setText("");
        
        
    }
    
    //διαγραφή κλήσης
    public void delete(){
        conn=DBConnection.getConnection();
        String sql = "delete from calls where callID = ?";
        try
        {
            pst= conn.prepareStatement(sql);
            pst.setString(1, txtID.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Operation Completed");
            updateTable();
            comboDivision.getSelectionModel().select(0);
        }
        catch(Exception e)
        {
        JOptionPane.showMessageDialog(null,"e");
        }
    }
    
    //get class Calls to Table
        public void createTable(){
        col_ID.setCellValueFactory(new PropertyValueFactory<Calls, Integer>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<Calls, String>("name"));
        col_surname.setCellValueFactory(new PropertyValueFactory<Calls, String>("surname"));
        col_phone.setCellValueFactory(new PropertyValueFactory<Calls, String>("phone"));
        col_city.setCellValueFactory(new PropertyValueFactory<Calls, String>("city"));
        col_address.setCellValueFactory(new PropertyValueFactory<Calls, String>("address"));
        col_division.setCellValueFactory(new PropertyValueFactory<Calls, String>("division"));
        col_incident.setCellValueFactory(new PropertyValueFactory<Calls, String>("incident"));
        col_time.setCellValueFactory(new PropertyValueFactory<Calls, String>("time"));
        }
    
        //update the table drom database
    public void updateTable(){

        try {
            listM = DBConnection.getDataCalls();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableUsers.setItems(listM);
    }
    
        public void updateTable100(){
        
        try {
            listM = DBConnection.get100Calls();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableUsers.setItems(listM);
    }
        
    public void updateTable199(){

        try {
            listM = DBConnection.get199Calls();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableUsers.setItems(listM);
    }
                
    public void updateTable166(){

        try {
            listM = DBConnection.get166Calls();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableUsers.setItems(listM);
    }
    
    //select from combo box which calls will be shown
        @FXML
    void selectCombo(ActionEvent event) {
        String s =comboDivision.getSelectionModel().getSelectedItem().toString();
        switch (s){
                case "ΟΛΑ":
                    updateTable();
                    break;
                case "ΑΣΤΥΝΟΜΙΑ - 100":
                    updateTable100();
                    break;
                case "ΠΥΡΟΣΒΕΣΤΙΚΗ - 199":
                    updateTable199();
                    break;
                case "ΕΚΑΒ - 166":
                    updateTable166();
                    break;
                default:
                    updateTable();
                    break;
        }
        
    }
    //logout function
    @FXML
    public void logout(ActionEvent event) throws IOException{
        
             Alert alert = new Alert(AlertType.CONFIRMATION, "Αποσύνδεση; ?", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                //do stuff
                
                           JOptionPane.showMessageDialog(null, "Αντίο σας");//or
                   
                       buttonLogout.getScene().getWindow().hide();
                       
                       Parent root1 = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
                       Stage mainStage = new Stage();
                       mainStage.setTitle("Login");
                       Scene scene = new Scene(root1);
                       mainStage.setScene(scene);  
                       mainStage.show();}
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        labelUsername.setText(FXMLLoginController.menuUsername);
        createTable();
        updateTable();
        comboDivision.getItems().addAll("ΟΛΑ","ΑΣΤΥΝΟΜΙΑ - 100","ΠΥΡΟΣΒΕΣΤΙΚΗ - 199", "ΕΚΑΒ - 166");
        comboDivision.getSelectionModel().select(0);
                txtName.setOnAction(e->txtSurname.requestFocus());
        txtSurname.setOnAction(e->txtPhone.requestFocus());
        txtPhone.setOnAction(e->txtCity.requestFocus());
        txtCity.setOnAction(e-> txtAddress.requestFocus());
        txtAddress.setOnAction(e->txtDivision.requestFocus());
        txtDivision.setOnAction(e->txtTime.requestFocus());
        txtTime.setOnAction(e->txtIncident.requestFocus());
        
            
    }    
    
    public void setUserInformation(String username, String fullname)
    {

        
        
    }
    
}
