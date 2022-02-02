package sample;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

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
    private Text date_is_null;

    @FXML
    void printBilan(MouseEvent event) {

    }

    @FXML
    void printFacture(MouseEvent event) {

    }

    @FXML
    void printFiche(MouseEvent event) {
        try {
            String path = "C:\\Users\\Arthur\\IdeaProjects\\SkiLoc\\Fiche_Client.pdf";
            PdfWriter pdfWriter = new PdfWriter(path);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new com.itextpdf.layout.Document(pdfDocument);
            pdfDocument.setDefaultPageSize(PageSize.A4);

            float col = 280f;
            float columnWidth[] = {col, col};
            Table table = new Table(columnWidth);

            table.addCell(new Cell().add(new Paragraph("FICHE CLIENT")));
            table.addCell(new Cell().add(new Paragraph("SkiLoc")));

            document.add(table);

            document.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void printRapport(MouseEvent event) {

    }

    @FXML
    void returnArticle(MouseEvent event) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/skiloc","root","toor");

            String selectedLocation = cb_location.getSelectionModel().getSelectedItem().toString();

            System.out.println("Enregistrement Retour ...");

            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM locations WHERE id = ?");
            pstmt.setString(1,selectedLocation);
            pstmt.executeUpdate();
            System.out.println("Retour effectué !");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation Retour");
            alert.setHeaderText("Le retour à bien été effectué.");
            alert.showAndWait();

            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
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
            txt_nom.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px");
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
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/skiloc", "root", "toor");
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT id FROM locations");
            String selectedMateriel1 = "";
            String selectedDebut1 = "";
            String selectedRetour1 = "";
            String selectedMateriel2 = "";
            String selectedDebut2 = "";
            String selectedRetour2 = "";
            String selectedMateriel3 = "";
            String selectedDebut3 = "";
            String selectedRetour3 = "";
            LocalDate compareDateDebut1 = dp_debut1.getValue();
            LocalDate compareDateRetour1 = dp_retour1.getValue();
            LocalDate compareDateDebut2 = dp_debut1.getValue();
            LocalDate compareDateRetour2 = dp_retour1.getValue();
            LocalDate compareDateDebut3 = dp_debut1.getValue();
            LocalDate compareDateRetour3 = dp_retour1.getValue();
            boolean locVerif = false;
            boolean dispoVerif = true;

            String selectedClient = cb_client.getSelectionModel().getSelectedItem().toString();

            if (cb_materiel1.getSelectionModel().getSelectedItem() != null) {
                selectedMateriel1 = cb_materiel1.getSelectionModel().getSelectedItem().toString();
                selectedDebut1 = dp_debut1.getValue().toString();
                selectedRetour1 = dp_retour1.getValue().toString();

                if (compareDateDebut1.isAfter(compareDateRetour1)) {
                    dp_debut1.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
                    dp_retour1.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
                    date_is_null.setStyle(("-fx-opacity: 100%"));

                }
            }

            if (cb_materiel2.getSelectionModel().getSelectedItem() != null) {
                selectedMateriel2 = cb_materiel2.getSelectionModel().getSelectedItem().toString();
                selectedDebut2 = dp_debut2.getValue().toString();
                selectedRetour2 = dp_retour2.getValue().toString();

                if (compareDateDebut2.isAfter(compareDateRetour2)) {
                    dp_debut2.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
                    dp_retour2.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
                    date_is_null.setStyle(("-fx-opacity: 100%"));

                }
            }

            if (cb_materiel3.getSelectionModel().getSelectedItem() != null) {
                selectedMateriel3 = cb_materiel3.getSelectionModel().getSelectedItem().toString();
                selectedDebut3 = dp_debut3.getValue().toString();
                selectedRetour3 = dp_retour3.getValue().toString();

                if (compareDateDebut3.isAfter(compareDateRetour3)) {
                    dp_debut3.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
                    dp_retour3.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
                    date_is_null.setStyle(("-fx-opacity: 100%"));

                }
            }

            Statement statement2 = connection.createStatement();
            ResultSet result2 = statement2.executeQuery("SELECT modele FROM locations");
            ArrayList<String> modeles = new ArrayList<>();
            while (result2.next()) {
                modeles.add(result2.getString("modele"));
            }

            if (modeles.contains(selectedMateriel1) | modeles.contains(selectedMateriel2) | modeles.contains(selectedMateriel3)  ) {
                if (modeles.contains(selectedMateriel1))
                {cb_materiel1.setStyle("-fx-text-fill: red; -fx-border-color: red ; -fx-border-width: 2px");}
                if (modeles.contains(selectedMateriel2))
                {cb_materiel2.setStyle("-fx-text-fill: red; -fx-border-color: red ; -fx-border-width: 2px");}
                if (modeles.contains(selectedMateriel3))
                {cb_materiel3.setStyle("-fx-text-fill: red; -fx-border-color: red ; -fx-border-width: 2px");}
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("Attention");
                alert2.setHeaderText("Cet article est déjà loué !");
                alert2.showAndWait();
                dispoVerif = false;

            }

            System.out.println("Enregistrement location ...");

            int id = 0;
            while (result.next()) {
                id = result.getInt("id");
            }
            id++;
            int id2 = id + 1;
            int id3 = id2 + 1;

            if (selectedMateriel1.length() != 0 && compareDateDebut1.isBefore(compareDateRetour1) && dispoVerif) {


                PreparedStatement pstmt = connection.prepareStatement("INSERT INTO locations (id, dateDebut, dateFin, nom, modele) VALUES (?,?,?,?,?)");
                pstmt.setInt(1, id);
                pstmt.setString(2, selectedDebut1);
                pstmt.setString(3, selectedRetour1);
                pstmt.setString(4, selectedClient);
                pstmt.setString(5, selectedMateriel1);
                pstmt.executeUpdate();
                System.out.println("Matériel 1 enregistré");
                locVerif = true;
            }

            if (selectedMateriel2.length() != 0 && compareDateDebut2.isBefore(compareDateRetour2) && dispoVerif) {


                PreparedStatement pstmt2 = connection.prepareStatement("INSERT INTO locations (id, dateDebut, dateFin, nom, modele) VALUES (?,?,?,?,?)");
                pstmt2.setInt(1, id2);
                pstmt2.setString(2, selectedDebut2);
                pstmt2.setString(3, selectedRetour2);
                pstmt2.setString(4, selectedClient);
                pstmt2.setString(5, selectedMateriel2);
                pstmt2.executeUpdate();
                System.out.println("Matériel 2 enregistré");
                locVerif = true;
            }

            if (selectedMateriel3.length() != 0 && compareDateDebut3.isBefore(compareDateRetour3) && dispoVerif) {


                PreparedStatement pstmt3 = connection.prepareStatement("INSERT INTO locations (id, dateDebut, dateFin, nom, modele) VALUES (?,?,?,?,?)");
                pstmt3.setInt(1, id3);
                pstmt3.setString(2, selectedDebut3);
                pstmt3.setString(3, selectedRetour3);
                pstmt3.setString(4, selectedClient);
                pstmt3.setString(5, selectedMateriel3);
                pstmt3.executeUpdate();
                System.out.println("Matériel 3 enregistré");
                locVerif = true;
            }

            if (locVerif) {
                System.out.println("Location enregistrée !");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation Location");
                alert.setHeaderText("La location à bien été enregistrée.");
                alert.showAndWait();
            }

            populateLocText();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void refresh(MouseEvent mouseEvent) {

        populateLocText();
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
                data.add(new String(rs.getString(1)));
            }
            cb_facture.setItems(data);
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void populateLocText() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/skiloc", "root", "toor")) {
            txt_locations.setText("\n");
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM locations");
            while (result.next()) {
                String id = result.getString(1);
                if (id.isEmpty()) {
                    id = "0";
                }
                String loc = id + "  |" + result.getString(2) + "  |" + result.getString(3) + "  |" + result.getString(4) + "  |" + result.getString(5) + "  \n";
                txt_locations.appendText(loc);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
