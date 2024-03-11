package edu.unam;


import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.application.Application;
import javafx.stage.Stage;


public class App  extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("controlExpediente");
        
    }
}


 /*ProductoVencible producto3 = new ProductoVencible("Queso", LocalDate.of(2022, 1, 1));
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