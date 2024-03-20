package edu.unam;


import java.io.IOException;

import edu.unam.repositorios.Repositorio;
import edu.unam.servicios.ServivcioExpediente;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App  extends Application {
    private static Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("controlExpediente");
        Repositorio repositorio = new Repositorio(emf);
        ServivcioExpediente ServivcioExpediente = new ServivcioExpediente(repositorio);
        scene = new Scene(loadFXML("ViewsHomeController"), 1100, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/edu/unam/vistas/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }
}


 /*
 public class App extends Application {
    private static Scene scene;

    @SuppressWarnings("unused")
    @Override
    public void start(Stage stage) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LaFuerzaPU");

        // Crear Repositorio
        Repositorio repositorio = new Repositorio(emf);

        ServicioGrupoMuscular servicioGrupoMuscular = new ServicioGrupoMuscular(repositorio);
        ServicioEjercicio servicioEjercicio = new ServicioEjercicio(repositorio);
        ServicioCliente servicioCliente = new ServicioCliente(repositorio);
        scene = new Scene(loadFXML("homeView"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/edu/unam/vistas/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
 
 
 
 ProductoVencible producto3 = new ProductoVencible("Queso", LocalDate.of(2022, 1, 1));
        Colectivo colectivo = new Colectivo("Mercedes Benz", "1114", 50);
        Automovil ford = new Automovil("ford","mustang","negro");
        Automovil vw = new Automovil("VW", "Gol", "Rojo"); 

        em.getTransaction().begin();
        Expediente exp = new Expediente("Empleado","Inicio de  año Lectivo", LocalDate.of(2022, 1, 1),true);
        
        em.persist(exp);
        em.getTransaction().commit(); 
        Servicio servicio = new Servicio(new Repositorio(emf));
        Expediente exp = new Expediente("Empleado","Inicio de  año Lectivo", LocalDate.of(2022, 1, 1),true);
        */