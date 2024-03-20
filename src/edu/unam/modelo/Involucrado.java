package edu.unam.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "involucrado")
public class Involucrado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_involucrado")
    private Long idInvolucrado;
    @Column(name="nombre_apellido")
    private String nombre;
    @Column(name="dni")
    private Long dni;
    @Column(name="telefono")
    private int telefono;
    @Column(name="email")
    private String email;
    @Column(name="rol")
    private String rol;
    @Column(name="es_miembroconsejo")
    private Boolean esmiembroConsejo;
    @Column(name="es_iniciante")
    private Boolean esIniciante;


    @ManyToOne
    @JoinColumn(name="idExpediente")
    private Expediente expediente;
    
    @OneToMany(mappedBy = "involucrado")
    private List<Asistencia> asistencia = new ArrayList<>();
    
    @ManyToMany
    @JoinColumn(name="idReunion")
    private Set<Reunion> reunion = new HashSet<>();


    
    //Constructores
    public Involucrado(){

    }
    public Involucrado(String nombre, Long dni, int telefono, String email, String rol, Boolean esmiembroConsejo,
            Boolean esIniciante, Expediente expediente) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
        this.rol = rol;
        this.esmiembroConsejo = esmiembroConsejo;
        this.esIniciante = esIniciante;
        this.expediente = expediente;
        expediente.agregarInvolucrado(this);
    }

    public Long getIdInvolucrado() {
        return idInvolucrado;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getTelefono() {
        return telefono;
    }
    public void setDetallesdeContaco(int telefono) {
        this.telefono = telefono;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    public boolean getEsmiembroConsejo() {
        return esmiembroConsejo;
    }
    public void setEsmiembroConsejo(boolean esmiembroConsejo) {
        this.esmiembroConsejo = esmiembroConsejo;
    }
    public boolean getEsIniciante() {
        return esIniciante;
    }
    public void setEsIniciante(boolean esIniciante) {
        this.esIniciante = esIniciante;
    }
    public Expediente getExpediente() {
        return expediente;
    }
    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }
    public List<Asistencia> getAsistencia() {
        return asistencia;
    }
    public void setAsistencia(List<Asistencia> asistencia) {
        this.asistencia = asistencia;
    }
    public Set<Reunion> getReunion() {
        return reunion;
    }
    public void setReunion(Set<Reunion> reunion) {
        this.reunion = reunion;
    }
    public Long getDni() {
        return dni;
    }
    public void setDni(Long dni) {
        this.dni = dni;
    }
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    public void setEsmiembroConsejo(Boolean esmiembroConsejo) {
        this.esmiembroConsejo = esmiembroConsejo;
    }
    public void setEsIniciante(Boolean esIniciante) {
        this.esIniciante = esIniciante;
    }
    @Override
    public String toString() {
        return idInvolucrado +" "+ nombre + " "+ dni +" " + telefono + " " + email + " " + rol + " " + esmiembroConsejo +" "+ esIniciante+ " "+ expediente ;
    }

    public void agregarAsistencia(Asistencia asistencia) {
        this.asistencia.add(asistencia);
    }

    public void agregarReunion(Reunion reunion) {
        this.reunion.add(reunion);
    }
}
