package edu.unam.modelo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;



@Entity
@Table(name = "minuta")
public class Minuta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_minuta")
    private Long idMinuta;
    @Column(name="fecha_de_la_reunion")
    private LocalDateTime fechadelaReunion;
    @Column(name="hora_de_la_reunion")
    private LocalDateTime horadelaReunion;
    @Column(name="desiciones_tomadas", nullable = false)
    private String desicionesTomadas;
    @Column(name="notas_adicionales", nullable = false)
    private String notasAdicionales;

    @ManyToOne
    @JoinColumn(name = "idReunion")
    private Reunion reunion;
    @ManyToOne
    @JoinColumn(name="idExpediente")
    private Expediente expediente;
    
    //contructores
    public Minuta(){
    }
    
    public Minuta(LocalDateTime fechadelaReunion, LocalDateTime horadelaReunion,
            String desicionesTomadas, String notasAdicionales, Reunion reunion,Expediente expediente) {
        this.fechadelaReunion = fechadelaReunion;
        this.horadelaReunion = horadelaReunion;
        this.desicionesTomadas = desicionesTomadas;
        this.notasAdicionales = notasAdicionales;
        this.reunion=reunion;
        this.expediente=expediente;
        expediente.agregarMinuta(this);
        reunion.agregarMinuta(this);
    }

    public Long getIdMinuta() {
        return idMinuta;
    }
    
    public void setFechadelaReunion(LocalDateTime fechadelaReunion) {
        this.fechadelaReunion = fechadelaReunion;
    }
    public LocalDateTime getHoradelaReunion() {
        return horadelaReunion;
    }
    public void setHoradelaReunion(LocalDateTime horadelaReunion) {
        this.horadelaReunion = horadelaReunion;
    }
    public String getDesicionesTomadas() {
        return desicionesTomadas;
    }
    public void setDesicionesTomadas(String desicionesTomadas) {
        this.desicionesTomadas = desicionesTomadas;
    }
    public String getNotasAdicionales() {
        return notasAdicionales;
    }
    public void setNotasAdicionales(String notasAdicionales) {
        this.notasAdicionales = notasAdicionales;
    }
    
    public Reunion getReunion() {
        return reunion;
    }

    public void setReunion(Reunion reunion) {
        if (reunion == null){
            throw new IllegalArgumentException("La reunion no puede ser null");
        }
        this.reunion = reunion;
    }
    
    public LocalDateTime getFechadelaReunion() {
        return fechadelaReunion;
    }
    
    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    @Override
    public String toString() {
        return idMinuta + " " + fechadelaReunion + " " + horadelaReunion  + " " + desicionesTomadas + " " + notasAdicionales + " " +reunion+ " "+ expediente;
    } 
}
