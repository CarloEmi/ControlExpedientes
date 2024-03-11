package edu.unam.modelo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "reunion")
public class Reunion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_reunion")
    private Long idReunion;
    @Column(name="fecha")
    private LocalDate fecha;
    @Column(name="orden_del_dia", nullable = false, length = 50 )
    private String ordendelDia;
    @Column(name="estado_reunion")
    private Boolean estadoReunion;
    @Column(name="hora_inicio")
    private LocalDateTime horaInicio;
    @Column(name="hora_fin")
    private LocalDateTime horaFin;

    @ManyToMany(mappedBy = "reunion")
    private Set<Involucrado> involucrado = new HashSet<>();

    @OneToMany(mappedBy = "reunion")
    private List<Asistencia> asistencia = new ArrayList<>();

    @ManyToMany(mappedBy = "reunion")
    private Set<Expediente> expediente = new HashSet<>();
    
    @OneToMany(mappedBy = "reunion")
    private List<Minuta> minuta = new ArrayList<>();
    
    //Constructores
    public Reunion() {
    }

    public Reunion(LocalDate fecha, Boolean estadoReunion, LocalDateTime horaInicio, LocalDateTime horaFin) {
        this.fecha = fecha;
        this.estadoReunion = estadoReunion;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    //set y get
    public Long getIdReunion() {
        return idReunion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Boolean isEstadoReunion() {
        return estadoReunion;
    }

    public void setEstadoReunion(Boolean estadoReunion) {
        this.estadoReunion = estadoReunion;
    }

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDateTime getHoraFin() {
        return horaFin;
    }
    public void sethoraFin(LocalDateTime horaFin) {
        this.horaFin = horaFin;
    }
    public void setHoraFin(LocalDateTime horaFin) {
        this.horaFin = horaFin;
    }
    public Boolean getEstadoReunion() {
        return estadoReunion;
    }
    
    public String getOrdendelDia() {
        return ordendelDia;
    }

    public void setOrdendelDia(String ordendelDia) {
        this.ordendelDia = ordendelDia;
    }

    public Set<Involucrado> getInvolucrado() {
        return involucrado;
    }

    public void setInvolucrado(Set<Involucrado> involucrado) {
        this.involucrado = involucrado;
    }

    public List<Asistencia> getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(List<Asistencia> asistencia) {
        this.asistencia = asistencia;
    }

    public Set<Expediente> getExpediente() {
        return expediente;
    }

    public void setExpediente(Set<Expediente> expediente) {
        this.expediente = expediente;
    }

    public List<Minuta> getMinuta() {
        return minuta;
    }

    public void setMinuta(List<Minuta> minuta) {
        this.minuta = minuta;
    }

    @Override
    public String toString() {
        return idReunion + " " + fecha + " " + estadoReunion+ " " + horaInicio + " " + horaFin;
    }

     public void agregarMinuta(Minuta minuta) {
        this.minuta.add(minuta);
    }
    public void agregarAsistencia(Asistencia asistencia) {
        this.asistencia.add(asistencia);
    }

    public void agregarExpediente(Expediente e) {
        this.expediente.add(e);
        e.agregarReunion(this);
    }

    public void agregarInvolucrado(Involucrado e) {
        this.involucrado.add(e);
        e.agregarReunion(this);
    }

}
