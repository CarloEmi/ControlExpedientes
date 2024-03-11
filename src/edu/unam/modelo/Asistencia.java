package edu.unam.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;


@Entity
@Table(name = "asistencia")
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_presencia")
    private Long idAsistencia;
    @Column(name="presencia")
    private boolean presencia;
    @Column(name="fecha")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "idReunion")
    private Reunion reunion;

    @ManyToOne
    @JoinColumn(name = "idInvolucrado")
    private Involucrado involucrado;

    //Contructores
    public Asistencia(){

    }
    public Asistencia(boolean presencia,LocalDate fecha,Involucrado involucrado, Reunion reunion) {
        this.presencia = presencia;
        this.fecha=fecha;
        this.involucrado=involucrado;
        involucrado.agregarAsistencia(this);
        reunion.agregarAsistencia(this);
    }
    //set y get

    public Long getIdAsistencia() {
        return idAsistencia;
    }
    public boolean isPresente() {
        return presencia;
    }
    public void setPresente(boolean presencia) {
        this.presencia = presencia;
    }
    public boolean isPresencia() {
        return presencia;
    }
    public void setPresencia(boolean presencia) {
        this.presencia = presencia;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public Reunion getReunion() {
        return reunion;
    }
    public void setReunion(Reunion reunion) {
        this.reunion = reunion;
    }
    public Involucrado getInvolucrado() {
        return involucrado;
    }
    public void setInvolucrado(Involucrado involucrado) {
        this.involucrado = involucrado;
    }
    
    @Override
    public String toString() {
        return idAsistencia + " " + presencia + " " + involucrado + " "+ reunion;
    }

    
}
