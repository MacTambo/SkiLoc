package sample;

import connectDb.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Observable;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

//84
public class Controller {

    @FXML
    private Button btn_bilan;

    @FXML
    private Button btn_confirm_loc;

    @FXML
    private Button btn_facture;

    @FXML
    private Button btn_fiche_cli;

    @FXML
    private Button btn_rapport;

    @FXML
    private Button btn_retour;

    @FXML
    private Button btn_save_client;

    @FXML
    private ComboBox<?> cb_article;

    @FXML
    private ComboBox<?> cb_client;


    @FXML
    private ComboBox<?> cb_facture;

    @FXML
    private ComboBox<?> cb_fiche;

    @FXML
    private ComboBox<?> cb_location;

    @FXML
    private ComboBox<?> cb_materiel1;

    @FXML
    private ComboBox<?> cb_materiel2;

    @FXML
    private ComboBox<?> cb_materiel3;

    @FXML
    private DatePicker dp_debut1;

    @FXML
    private DatePicker dp_debut2;

    @FXML
    private DatePicker dp_debut3;

    @FXML
    private DatePicker dp_rapport1;

    @FXML
    private DatePicker dp_rapport2;

    @FXML
    private DatePicker dp_retour1;

    @FXML
    private DatePicker dp_retour2;

    @FXML
    private DatePicker dp_retour3;

    @FXML
    private TextField txt_adresse;

    @FXML
    private TextField txt_codep;

    @FXML
    private TextArea txt_locations;

    @FXML
    private TextField txt_nom;

    @FXML
    private TextField txt_prenom;

    @FXML
    private TextField txt_ville;

    @FXML
    void printBilan(MouseEvent event) {

    }

    @FXML
    void printFacture(MouseEvent event) {

    }

    @FXML
    void printFiche(MouseEvent event) {

    }

    @FXML
    void printRapport(MouseEvent event) {

    }

    @FXML
    void returnArticle(MouseEvent event) {

    }

    @FXML
    void saveClient(MouseEvent event) {

    }

    @FXML
    void saveLoc(MouseEvent event) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/skiloc","root","toor");

            String selectedClient = cb_client.getSelectionModel().getSelectedItem().toString();
            String selectedMateriel1 = cb_materiel1.getSelectionModel().getSelectedItem().toString();
            String selectedDebut1 = dp_debut1.getValue().toString();
            String selectedRetour1 = dp_retour1.getValue().toString();

            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO locations (dateDebut, dateFin, nom, modele) VALUES (?,?,?,?)");
            pstmt.setString(1,selectedDebut1);
            pstmt.setString(2,selectedRetour1);
            pstmt.setString(3,selectedClient);
            pstmt.setString(4,selectedMateriel1);
            pstmt.executeUpdate();

            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void refresh(MouseEvent mouseEvent) {

        //CB CLIENTS

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/skiloc","root","toor");

            ResultSet rs = connection.createStatement().executeQuery("select * from clients");
            ObservableList data = FXCollections.observableArrayList();

            while (rs.next()){
                data.add(new String(rs.getString(2)));
            }
            cb_client.setItems(data);
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        //CB MATERIEL 1

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/skiloc","root","toor");

            ResultSet rs = connection.createStatement().executeQuery("select * from skis");
            ObservableList data = FXCollections.observableArrayList();

            while (rs.next()){
                data.add(new String(rs.getString(2)));
            }
            cb_materiel1.setItems(data);
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        //CB MATERIEL 2

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/skiloc","root","toor");

            ResultSet rs = connection.createStatement().executeQuery("select * from skis");
            ObservableList data = FXCollections.observableArrayList();

            while (rs.next()){
                data.add(new String(rs.getString(2)));
            }
            cb_materiel2.setItems(data);
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        //CB MATERIEL 3

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/skiloc","root","toor");

            ResultSet rs = connection.createStatement().executeQuery("select * from skis");
            ObservableList data = FXCollections.observableArrayList();

            while (rs.next()){
                data.add(new String(rs.getString(2)));
            }
            cb_materiel3.setItems(data);
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        //CB LOCATION

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/skiloc","root","toor");

            ResultSet rs = connection.createStatement().executeQuery("select * from locations");
            ObservableList data = FXCollections.observableArrayList();

            while (rs.next()){
                data.add(new String(rs.getString(1)));
            }
            cb_location.setItems(data);
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        //CB ARTICLE

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/skiloc","root","toor");

            ResultSet rs = connection.createStatement().executeQuery("select * from locations");
            ObservableList data = FXCollections.observableArrayList();

            while (rs.next()){
                data.add(new String(rs.getString(5)));
            }
            cb_article.setItems(data);
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        //CB FICHE

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/skiloc","root","toor");

            ResultSet rs = connection.createStatement().executeQuery("select * from clients");
            ObservableList data = FXCollections.observableArrayList();

            while (rs.next()){
                data.add(new String(rs.getString(2)));
            }
            cb_fiche.setItems(data);
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        //CB FACTURE

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/skiloc","root","toor");

            ResultSet rs = connection.createStatement().executeQuery("select * from locations");
            ObservableList data = FXCollections.observableArrayList();

            while (rs.next()){
                data.add(new String(rs.getString(5)));
            }
            cb_facture.setItems(data);
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
