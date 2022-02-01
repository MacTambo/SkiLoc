package sample;

import javafx.scene.control.*;
import javafx.stage.PopupWindow;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;

//84
public class Controller {

    String connexionUrl = "jdbc:mysql://localhost:3306/skiloc; root; toor";


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


        txt_nom.setStyle("-fx-border-color: none");
        txt_prenom.setStyle("-fx-border-color: none ");
        txt_adresse.setStyle("-fx-border-color: none ");
        txt_codep.setStyle("-fx-border-color: none ");
        txt_ville.setStyle("-fx-border-color: none ");

        String nom = txt_nom.getText();
        String nomToTest = nom.toLowerCase();
        if (nom.isEmpty()) {
            txt_nom.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
        }
        String prenom = txt_prenom.getText();
        if (prenom.isEmpty()) {
            txt_prenom.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
        }
        String adresse = txt_adresse.getText();
        if (adresse.isEmpty()) {
            txt_adresse.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
        }
        String codePostal = txt_codep.getText();
        if (codePostal.isEmpty()) {
            txt_codep.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
        }
        String ville = txt_ville.getText();
        if (ville.isEmpty()) {
            txt_ville.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/skiloc", "root", "toor")) {

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT nom FROM clients");
            ArrayList<String> names = new ArrayList<>();
            while (result.next()) {
                names.add(result.getString("nom").toLowerCase());
            }

            if (names.contains(nomToTest)) {
                txt_nom.setStyle("-fx-text-fill: red; -fx-border-color: red ; -fx-border-width: 2px");
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("Attention");
                alert2.setHeaderText("Ce client existe déjà dans la base !");
                alert2.showAndWait();
                nom = "";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Integer id = 0;

        if (nom.isEmpty() | prenom.isEmpty() | adresse.isEmpty() | codePostal.isEmpty() | ville.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText("Merci de ne laisser aucun champ client vide.");
            alert.showAndWait();
        } else {
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/skiloc", "root", "toor")) {

                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("SELECT id FROM clients");

                while (result.next()) {
                    id = result.getInt("id");
                }
                System.out.println(id);
                id++;
                String idStr = id.toString();

                PreparedStatement pstmt = connection.prepareStatement("INSERT INTO clients(id,nom,prenom,adresse,code_postal,ville) VALUES (?,?,?,?,?,?)");

                pstmt.setString(1, idStr);
                pstmt.setString(2, nom);
                pstmt.setString(3, prenom);
                pstmt.setString(4, adresse);
                pstmt.setString(5, codePostal);
                pstmt.setString(6, ville);
                pstmt.executeUpdate();
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Confirmation");
                alert2.setHeaderText("Client ajouté à la base de données SkiLoc !");
                alert2.showAndWait();
                System.out.println("client ajouté");

                txt_nom.setText("");
                txt_prenom.setText("");
                txt_adresse.setText("");
                txt_codep.setText("");
                txt_ville.setText("");


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void saveLoc(MouseEvent event) {

    }

}
