package edu.unam.modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "expediente")
public class Expediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_expediente")
    private Long idExpediente;
    @Column(name="iniciante", nullable = false, length = 50 )
    private String iniciante;
    @Column(name="tema", nullable = false, length = 50 )
    private String tema;
    @Column(name="texto_nota", nullable = false, length = 50 )
    private String textoNota;
    @Column(name="fecha_ingreso_facultad")
    private LocalDate fechaingresoFacultad;
    @Column(name="estado_expediente")
    private Boolean estadoExpediente;

    @OneToMany(mappedBy = "expediente")
    private List<Involucrado> involucrado = new ArrayList<>();

    @OneToMany(mappedBy = "expediente",cascade = CascadeType.ALL)
    private List<Accion> accion = new ArrayList<>();

    @OneToMany(mappedBy = "expediente")
    private List<Minuta> minuta = new ArrayList<>();

    @ManyToMany
    private Set<Reunion> reunion = new HashSet<>();

    //contructores
    public Expediente() {
    }
    public Expediente(String iniciante, String tema,String textoNota, LocalDate fechaingresoFacultad,Boolean estadoExpediente) {
        this.iniciante = iniciante;
        this.tema=tema;
        this.textoNota = textoNota;
        this.fechaingresoFacultad = fechaingresoFacultad;
        this.estadoExpediente = estadoExpediente;   
    }

    //get y set
    public Long getIdExpediente() {
        return idExpediente;
    }
    public String getIniciante() {
        return iniciante;
    }
    public void setIniciante(String iniciante) {
        this.iniciante = iniciante;
    }
    public String getTextoNota() {
        return textoNota;
    }
    public void setTextoNota(String textoNota) {
        this.textoNota = textoNota;
    }
    public LocalDate getFechaingresoFacultad() {
        return fechaingresoFacultad;
    }
    public void setFechaingresoFacultad(LocalDate fechaingresoFacultad) {
        this.fechaingresoFacultad = fechaingresoFacultad;
    }
 
    public Boolean isEstadoExpediente() {
        return estadoExpediente;
    }
    public void setEstadoExpediente(boolean estadoExpediente) {
        this.estadoExpediente = estadoExpediente;
    }
    
    public Boolean getEstadoExpediente() {
        return estadoExpediente;
    }
    public void setEstadoExpediente(Boolean estadoExpediente) {
        this.estadoExpediente = estadoExpediente;
    }
    public String getTema() {
        return tema;
    }
    public void setTema(String tema) {
        this.tema = tema;
    }
    public List<Involucrado> getInvolucrado() {
        return involucrado;
    }
    public void setInvolucrado(List<Involucrado> involucrado) {
        this.involucrado = involucrado;
    }
    public List<Accion> getAccion() {
        return accion;
    }
    public void setAccion(List<Accion> accion) {
        this.accion = accion;
    }
    public List<Minuta> getMinuta() {
        return minuta;
    }
    public void setMinuta(List<Minuta> minuta) {
        this.minuta = minuta;
    }
    public Set<Reunion> getReunion() {
        return reunion;
    }
    public void setReunion(Set<Reunion> reunion) {
        this.reunion = reunion;
    }


    @Override
    public String toString() {
        return  idExpediente + " " + iniciante +" " + tema+ " " + textoNota + " " + fechaingresoFacultad + " "  + estadoExpediente;
    }

    public void agregarAccion(Accion accion) {
        this.accion.add(accion);
    }
    public void agregarMinuta(Minuta minuta) {
        this.minuta.add(minuta);
    }
    public void agregarInvolucrado(Involucrado involucrado) {
        this.involucrado.add(involucrado);
    }
    public void agregarReunion(Reunion reunion) {
        this.reunion.add(reunion);
    }
    
}

