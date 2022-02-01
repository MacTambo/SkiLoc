package sample;
//toto//
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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

}
