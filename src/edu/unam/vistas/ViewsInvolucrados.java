package edu.unam.vistas;

import edu.unam.App;
import edu.unam.repositorios.Repositorio;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;


public class ViewsInvolucrados {
    private final Repositorio repositorio;
    //private final ServicioCliente servicioCliente;
    private EntityManagerFactory emf;
    public ViewsInvolucrados() {
        emf = Persistence.createEntityManagerFactory("controlExpediente");
        repositorio = new Repositorio(emf);
        
    }
    @FXML
    private Button ButtonActualizar;

    @FXML
    private Button ButtonAgregar1;

    @FXML
    private Button ButtonEliminar;

    @FXML
    private Button ButtonLimpiar1;

    @FXML
    private Button ButtonVolver;

    @FXML
    private TextField CampoCorreo;

    @FXML
    private TextField CampoDni;

    @FXML
    private ComboBox<?> CampoExpediente;

    @FXML
    private ComboBox<?> CampoIniciante;

    @FXML
    private ComboBox<?> CampoMiembro;

    @FXML
    private TextField CampoNombreCompeto;

    @FXML
    private TextField CampoRol;

    @FXML
    private TextField CampoTelefono;

    @FXML
    private TableColumn<?, ?> ColumnaEsIniciante;

    @FXML
    private TableColumn<?, ?> ColumnaEsMiembro;

    @FXML
    private TableColumn<?, ?> ColumnaExpediente;

    @FXML
    private TableColumn<?, ?> ColumnaFecha;

    @FXML
    private TableColumn<?, ?> ColumnaId;

    @FXML
    private TableColumn<?, ?> ColumnaInvolucrado;

    @FXML
    private TableColumn<?, ?> ColumnaPresencia;

    @FXML
    private TableColumn<?, ?> ColumnaReunion;

    @FXML
    private TableColumn<?, ?> ColumnaRol;

    @FXML
    private AnchorPane PanelInvolucrado;

    @FXML
    private TableView<?> TablaInvolucrados;

    @FXML
    private TextField TextBuscador;

    @FXML
    private TextField TextBuscador1;

    @FXML
    private Label TextDNI;

    @FXML
    private Label TextEmail;

    @FXML
    private Label TextExpediente;

    @FXML
    private Label TextIniciante;

    @FXML
    private Label TextMiembro;

    @FXML
    private Label TextNombreCompleto;

    @FXML
    private Label TextRol;

    @FXML
    private Label TexttTlefono;

    @FXML
    void AccionActualizar(ActionEvent event) {

    }

    @FXML
    void AccionAgregar(ActionEvent event) {

    }

    @FXML
    void AccionEliminar(ActionEvent event) {

    }

    @FXML
    void AccionLimpiar(ActionEvent event) {

    }

    @FXML
    void AccionVolver(ActionEvent event)  throws IOException {
         App.setRoot("ViewsHomeController");
    }

    @FXML
    void CampoBuscarDni(ActionEvent event) {

    }

    @FXML
    void CampoBuscarNombre(ActionEvent event) {

    }

    @FXML
    void IngresarDNI(ActionEvent event) {

    }

    @FXML
    void IngresarEmail(ActionEvent event) {

    }

    @FXML
    void IngresarRol(ActionEvent event) {

    }

    @FXML
    void IngresoNombreCompleto(ActionEvent event) {

    }

    @FXML
    void SeleccionExpediente(ActionEvent event) {

    }

    @FXML
    void SeleccionIniciante(ActionEvent event) {

    }

    @FXML
    void SeleccionMiembro(ActionEvent event) {

    }

}