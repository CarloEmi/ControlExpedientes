package edu.unam.vistas;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


import edu.unam.App;
import edu.unam.modelo.Accion;
import edu.unam.modelo.Expediente;
import edu.unam.repositorios.Repositorio;
import edu.unam.servicios.ServicioAccion;
import edu.unam.servicios.ServivcioExpediente;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

public class ViewsAccion {
    private Repositorio repositorio;
    private EntityManagerFactory emf;
    private ServicioAccion servicioAccion;
    private ServivcioExpediente servicioExpediente;

    public ViewsAccion() {
        emf = Persistence.createEntityManagerFactory("controlExpediente");
        repositorio = new Repositorio(emf);
        servicioExpediente = new ServivcioExpediente(repositorio);
        servicioAccion = new ServicioAccion(repositorio);
    }
    @FXML
    private TableView<Accion> PanelTablaAccion;

    @FXML
    private Button buttonActualizar;

    @FXML
    private Button buttonAgregar;

    @FXML
    private Button buttonEliminar;

    @FXML
    private Button buttonLimpiar;

    @FXML
    private Button buttonVolver;

    @FXML
    private TextArea campoDescripcion;

    @FXML
    private DatePicker campoFecha;

    @FXML
    private TableColumn<Accion, String> columnadescripcion;

    @FXML
    private TableColumn<Accion, String> columnaexpediente;

    @FXML
    private TableColumn<Accion, LocalDate> columnafecha;

    @FXML
    private TableColumn<Accion, Long> columnaid;

    @FXML
    private ComboBox<Expediente> comboBoxExpediente;

    @FXML
    private TextField textBuscador;

    @FXML
    void accionActualizar(ActionEvent event) {

    }

    @FXML
    void accionAgregar(ActionEvent event) {
        Alert alertError = new Alert(Alert.AlertType.INFORMATION);
        alertError.setTitle("Éxito");
        alertError.setHeaderText(null);
        alertError.setContentText("Los Datos de la Accion se han agregado Correctamente");

        
        String descripcionIngresada;
        LocalDate fechaIngresada;
        Expediente expedienteSeleccionado;
        
        try {
                // Se obtienen los datos de los campos y se realiza la validación
                descripcionIngresada = campoDescripcion.getText().trim();
                fechaIngresada = campoFecha.getValue();
                expedienteSeleccionado = comboBoxExpediente.getValue();            
                // Obtenemos la lista de todos los expedientes disponibles
    
                // Validar que no haya campos vacíos
                if (descripcionIngresada.isEmpty() || fechaIngresada == null || expedienteSeleccionado == null) {
                    alertError.setContentText("Por favor, complete todos los campos con los datos correctos.");
                    alertError.showAndWait();
                    return;
                }
                // Verificar que la fecha de ingreso sea la fecha actual
                if (!fechaIngresada.equals(LocalDate.now())) {
                    alertError.setContentText("La fecha de ingreso debe ser la fecha actual.");
                    alertError.showAndWait();
                    return;
                }
                Accion newAccion = new Accion();
                newAccion.setDescripcion(descripcionIngresada);
                newAccion.setFecha(fechaIngresada);
                newAccion.setExpediente(expedienteSeleccionado);;

                servicioAccion.agregarAccion(newAccion.getDescripcion(),newAccion.getFecha(), newAccion.getExpediente());            
                PanelTablaAccion.getItems().add(newAccion);
        
                // Mostrar mensaje de éxito
                alertError.showAndWait();

                // Limpiar los campos de texto después de agregar la Acción
                campoDescripcion.clear();
                campoFecha.setValue(null);
                comboBoxExpediente.setValue(null);

                PanelTablaAccion.refresh();
        } catch (IllegalArgumentException e) {
            // Capturar excepción si hay datos inválidos
            alertError.setContentText("Error: " + e.getMessage());
            alertError.setContentText("Los campos estan incompletos oa puesto datos erroneos");
            alertError.showAndWait();
        }
    }

    @FXML
    void accionEliminar(ActionEvent event) {
        Accion datosAccion = PanelTablaAccion.getSelectionModel().getSelectedItem();
            try {
                PanelTablaAccion.refresh();
                // Eliminar el expediente de la tabla
                servicioAccion.eliminarAccion(datosAccion.getIdAccion());
                PanelTablaAccion.getItems().remove(datosAccion);
                PanelTablaAccion.refresh();      
                // Para mostrar un mensaje de éxito
                Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
                alertSuccess.setTitle("Éxito");
                alertSuccess.setHeaderText(null);
                alertSuccess.setContentText("La Accion se eliminó correctamente.");
                alertSuccess.showAndWait();
            } catch (Exception e) {
                // Mostrar mensaje de que el expediente no se pudo eliminar
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("Error");
                alertError.setHeaderText(null);
                alertError.setContentText("No se puede eliminar la Accion.");
                alertError.showAndWait();
            }
            PanelTablaAccion.refresh();
    }
    @FXML
    public void initialize(){
        buttonActualizar.setDisable(true);
        buttonEliminar.setDisable(true);
        try {
            /*// Obtener lista de expedientes disponibles
            List<Expediente> expedientesDisponibles = servicioExpediente.buscarTodos();

            // Limpiar ComboBox y luego agregar expedientes disponibles
            comboBoxExpediente.getItems().clear();
            //comboBoxExpediente.getItems().addAll(expedientesDisponibles);*/
        // Configurar el ComboBox para mostrar solo el número de expediente, iniciante y fecha
        comboBoxExpediente.setConverter(new StringConverter<Expediente>() {
            @Override
            public String toString(Expediente expediente) {
                if (expediente == null) {
                    return "";
                }
                return String.format("%d - %s", expediente.getNroExpediente(), expediente.getTema());
            }

            @Override
            public Expediente fromString(String string) {
                // No es necesario implementar esta conversión para un ComboBox
                return null;
            }
        });

        try {
            // Obtener lista de expedientes disponibles
            List<Expediente> expedientesDisponibles = servicioExpediente.buscarTodos();

            // Limpiar ComboBox y luego agregar expedientes disponibles
            comboBoxExpediente.getItems().addAll(expedientesDisponibles);
        } catch (Exception e) {
            // Manejar cualquier excepción que pueda ocurrir al cargar los expedientes
            e.printStackTrace();
        }
        } catch (Exception e) {
            // Manejar cualquier excepción que pueda ocurrir al cargar los expedientes
            e.printStackTrace();
        }

        columnaid.setCellValueFactory(new PropertyValueFactory<>("idAccion"));
        columnadescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        columnafecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columnaexpediente.setCellValueFactory(new PropertyValueFactory<>("expediente"));
        //columnaexpediente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExpediente().getnroExpediente()));
        // Obtener todos los expedientes de la base de datos
        List<Accion> acciones = servicioAccion.listarAccion();
        
        if (acciones.isEmpty()) { 
            PanelTablaAccion.setPlaceholder(new Label("No existen Acciones para visualizar."));
        } else {
            PanelTablaAccion.getItems().addAll(acciones);
        }
        // Listener para la selección de la tabla
        PanelTablaAccion.getSelectionModel().selectedItemProperty().addListener((obs, oldSeleccion, newSeleccion) -> {
            if (newSeleccion != null) {
                buttonActualizar.setDisable(false);
                buttonEliminar.setDisable(false); 
                campoDescripcion.setText(newSeleccion.getDescripcion());
                campoFecha.setValue(newSeleccion.getFecha());
                comboBoxExpediente.setValue(newSeleccion.getExpediente());
            } else {
                buttonActualizar.setDisable(true);
                buttonEliminar.setDisable(true);
                campoDescripcion.clear();
                campoFecha.setValue(null);
                comboBoxExpediente.setValue(null);
            }
        });
    
        // Refrescar la tabla después de agregar un expediente
        PanelTablaAccion.refresh();
        PanelTablaAccion.refresh();
        //botonactualizar
        buttonActualizar.setOnAction((ActionEvent event) -> {
            // Obtener el expediente seleccionado en la tabla
            Accion datosAccion = PanelTablaAccion.getSelectionModel().getSelectedItem();
        
            try {
                if (campoDescripcion == null) {
                    throw new IllegalArgumentException("La descripcion no puede estar vacía.");
                }
                // Corrección aquí: obtener el número de expediente del campo de texto
                datosAccion.setDescripcion(campoDescripcion.getText());
                datosAccion.setFecha(campoFecha.getValue());
                datosAccion.setExpediente(comboBoxExpediente.getValue());
                // Validar que el número de expediente sea mayor que cero
                Alert alertError = new Alert(Alert.AlertType.INFORMATION);
                // Verificar que el número de expediente sea solo números
                // Verificar que la fecha de ingreso sea la fecha actual
                if (!datosAccion.getFecha().equals(LocalDate.now())) {
                    alertError.setContentText("La fecha de ingreso debe ser la fecha actual.");
                    alertError.showAndWait();
                    return;
                }
                // Llamar al servicio para editar el expediente
                servicioAccion.editarAccion(datosAccion.getIdAccion(),datosAccion.getDescripcion(), datosAccion.getFecha(), datosAccion.getExpediente());
                    
                // Refrescar la tabla con los nuevos datos de la base de datos
                PanelTablaAccion.refresh();
                // Después de editar, deseleccionar el elemento
                PanelTablaAccion.getSelectionModel().clearSelection();
            
                // Mostrar mensaje de éxito
                Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
                alertSuccess.setTitle("Éxito");
                alertSuccess.setHeaderText(null);
                alertSuccess.setContentText("La Accion se se editó correctamente.");
            
                alertSuccess.showAndWait();
            } catch (Exception e) {
                // En caso de error, mostrar mensaje de error genérico
                Alert alertError = new Alert(Alert.AlertType.INFORMATION);
                alertError.setTitle("Error");
                alertError.setHeaderText(null);
                alertError.setContentText("No se puede editar la Accion");
        
                alertError.showAndWait();
            }
        });
    }
    @FXML
    void accionLimpiar(ActionEvent event) {
        campoDescripcion.clear();
        campoFecha.setValue(null);
        comboBoxExpediente.setValue(null);
    }

    @FXML
    void accionVolver(ActionEvent event)throws IOException {
        App.setRoot("ViewsHomeController");
    }

    @FXML
    void buscarnroExpediente(KeyEvent event) {
        
    }

    @FXML
    void campoBuscar(ActionEvent event) {

    }

    @FXML
    void seleccionExpediente(ActionEvent event) {

    }

    @FXML
    void seleccionarFecha(ActionEvent event) {

    }
 

}

