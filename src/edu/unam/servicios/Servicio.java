/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unam.servicios;

import edu.unam.repositorios.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import edu.unam.modelo.Accion;
import edu.unam.modelo.Asistencia;
import edu.unam.modelo.Expediente;
import edu.unam.modelo.Involucrado;
import edu.unam.modelo.Minuta;
import edu.unam.modelo.OrdenDelDia;
import edu.unam.modelo.Reunion;
import edu.unam.modelo.Tema;


public class Servicio {

    private Repositorio repositorio;

    public Servicio(Repositorio p) {
        this.repositorio = p;
    }

    //ACCION
    //Listar
    public List<Accion> listarAccion() {
        return this.repositorio.buscarTodos(Accion.class);
    }
    
    //Buscar
    public Accion buscarAccion(Long idAccion) {
        return this.repositorio.buscar(Accion.class, idAccion);
    }
    //Agregar
    public void agregarAccion(String descripcion, LocalDate fecha, Expediente expediente) {
        if (descripcion.trim().length() == 0 || fecha  == null || expediente == null) {
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repositorio.iniciarTransaccion();
        Accion accion = new Accion(descripcion.toUpperCase().trim(), fecha , expediente);
        this.repositorio.insertar(accion);
        this.repositorio.confirmarTransaccion();
    }
    
    // Cambiar valor devuelto
    public void editarAccion(Long idAccion, String descripcion, LocalDate fecha, Expediente expediente) {
        if (descripcion.trim().length() == 0 || fecha  == null || expediente == null) {
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repositorio.iniciarTransaccion();
        Accion accion = this.repositorio.buscar(Accion.class, idAccion);
        if (accion != null) {
            accion.setDescripcion(descripcion);
            accion.setFecha(fecha);
            if (! accion.getExpediente().equals(expediente)) {
                accion.getExpediente().getAcciones().remove(accion);
                accion.setExpediente(expediente);
                expediente.getAcciones().add(accion);
            }
            this.repositorio.modificar(accion);
            this.repositorio.confirmarTransaccion();
        } else {
            this.repositorio.descartarTransaccion();
        }
    }
    //Eliminar 
    public int eliminarAccion(Long idAccion) {
        this.repositorio.iniciarTransaccion();
        Accion accion = this.repositorio.buscar(Accion.class, idAccion);
        // como se soluciona??
        if (accion != null ) {
            this.repositorio.eliminar(accion);
            this.repositorio.confirmarTransaccion();
            return 0;
        } else {
            this.repositorio.descartarTransaccion();
            return 1;
        }
    }
    
    
    //Expedientes
    //Listas
    public List<Expediente> listarExpedientes() {
        var expedientesRepositorio = this.repositorio.buscarTodos(Expediente.class);
        var listado = new ArrayList<Expediente>();
        for (var expediente : expedientesRepositorio) {
            if (expediente.isEstadoExpediente()) {
                listado.add(expediente);
            }
        }
        return listado;
    }
    
    //Buscar
    public Expediente buscarExpediente(Long idExpediente) {
        return this.repositorio.buscar(Expediente.class, idExpediente);
    }

    public int eliminarExpediente(Long idExpediente) {
        this.repositorio.iniciarTransaccion();
        Expediente expediente = this.repositorio.buscar(Expediente.class, idExpediente);
        // como se soluciona??
        if (expediente != null && expediente.getAcciones().isEmpty() && expediente.getInvolucrados().isEmpty()) {
            this.repositorio.eliminar(expediente);
            this.repositorio.confirmarTransaccion();
            return 0;
        } else {
            this.repositorio.descartarTransaccion();
            return 1;
        }
    }

    public void agregarExpediente(String iniciante, String textoNota , LocalDate fechaingresoFacultad, Boolean estadoExpediente) {
        if (iniciante.trim().length() == 0 ||textoNota.trim().length() == 0 || fechaingresoFacultad  == null || estadoExpediente == null) {
            //throw new IllegalArgumentException("Faltan datos en Expediente");
        }
        this.repositorio.iniciarTransaccion();
        Expediente expediente = new Expediente(iniciante, textoNota, fechaingresoFacultad , estadoExpediente);
        this.repositorio.insertar(expediente);
        this.repositorio.confirmarTransaccion();
    }

    public void editarExpediente(Long idExpediente, String iniciante, String textoNota, LocalDate fechaingresoFacultad, Boolean estadoExpediente) {
        if (iniciante.trim().length() == 0 ||textoNota.trim().length() == 0 || fechaingresoFacultad  == null || estadoExpediente == null) {
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repositorio.iniciarTransaccion();
        Expediente expediente = this.repositorio.buscar(Expediente.class, idExpediente);
        if (expediente != null) {
            expediente.setIniciante(iniciante.toUpperCase().trim());
            this.repositorio.modificar(expediente);
            this.repositorio.confirmarTransaccion();
        } else {
            this.repositorio.descartarTransaccion();
        }
    }
    //TEMA
    //Listar
    public List<Tema> listarTemas() {
        return this.repositorio.buscarTodos(Tema.class);
    }
    
    //Buscar
    public Tema buscarTema(Long idTema) {
        return this.repositorio.buscar(Tema.class, idTema);
    }
    //Agregar
    public void agregarTema(String titulo, String descripcion) {
        if (titulo.trim().length() == 0 || descripcion.trim().length() == 0 ) {
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repositorio.iniciarTransaccion();
        Tema tema = new Tema(titulo.toUpperCase().trim(), descripcion.toUpperCase().trim());
        this.repositorio.insertar(tema);
        this.repositorio.confirmarTransaccion();
    }
    
    
    //Eliminar 
    public int eliminarTema(Long idTema) {
        this.repositorio.iniciarTransaccion();
        Tema tema = this.repositorio.buscar(Tema.class, idTema);
        // como se soluciona??
        if (tema != null && tema.getMinutas().isEmpty() && tema.getOrdenesdelDia().isEmpty() ) {
            this.repositorio.eliminar(tema);
            this.repositorio.confirmarTransaccion();
            return 0;
        } else {
            this.repositorio.descartarTransaccion();
            return 1;
        }
    }
    //Editar
    public void editarTema(Long idTema, String titulo, String descripcion) {
        this.repositorio.iniciarTransaccion();
        Tema temas = this.repositorio.buscar(Tema.class, idTema);
        if (temas != null) {
            temas.setTitulo(titulo.toUpperCase().trim());
            this.repositorio.modificar(temas);
            this.repositorio.confirmarTransaccion();
        } else {
            this.repositorio.descartarTransaccion();
        }
    }



    //MINUTA
    //Listar
    public List<Minuta> listarMinuta() {
        return this.repositorio.buscarTodos(Minuta.class);
    }
    
    //Buscar
    public Minuta buscarMinuta(Long idMinuta) {
        return this.repositorio.buscar(Minuta.class, idMinuta);
    }
    //Agregar
    public void agregarMinuta(LocalDateTime fechadelaReunion, LocalDateTime horadelaReunion,
            String desicionesTomadas, String notasAdicionales, Reunion reunion,Tema temas) {
        if (fechadelaReunion == null || horadelaReunion  == null ||desicionesTomadas.trim().length() == 0 || reunion == null|| temas  == null) {
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repositorio.iniciarTransaccion();
        Minuta minuta = new Minuta(fechadelaReunion, horadelaReunion, desicionesTomadas.toUpperCase().trim(), notasAdicionales.toUpperCase().trim(), reunion, temas);
        this.repositorio.insertar(minuta);
        this.repositorio.confirmarTransaccion();
    }
    
    // Cambiar valor devuelto
    public void editarMinuta(Long idMinuta, LocalDateTime fechadelaReunion, LocalDateTime horadelaReunion,
    String desicionesTomadas, String notasAdicionales, Reunion reunion,Tema temas) {
        if (fechadelaReunion == null || horadelaReunion  == null ||desicionesTomadas.trim().length() == 0 || reunion == null|| temas  == null) {
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repositorio.iniciarTransaccion();
        Minuta minuta = this.repositorio.buscar(Minuta.class, idMinuta);
        if (minuta != null ) {
            minuta.setFechadelaReunion(fechadelaReunion);
            minuta.setHoradelaReunion(horadelaReunion);
            minuta.setDesicionesTomadas(desicionesTomadas);
            minuta.setNotasAdicionales(notasAdicionales);
            minuta.setReunion(reunion);
            if (! minuta.getTemas().equals(temas)) {
                minuta.getTemas().getMinutas().remove(minuta);
                minuta.setTemas(temas);
                temas.getMinutas().add(minuta);
            }
            this.repositorio.modificar(minuta);
            this.repositorio.confirmarTransaccion();
        } else {
            this.repositorio.descartarTransaccion();
        }
    }
    //Eliminar 
    public int eliminarMinuta(Long idMinuta) {
        this.repositorio.iniciarTransaccion();
        Minuta minuta = this.repositorio.buscar(Minuta.class, idMinuta);
        if (minuta != null ) {
            this.repositorio.eliminar(minuta);
            this.repositorio.confirmarTransaccion();
            return 0;
        } else {
            this.repositorio.descartarTransaccion();
            return 1;
        }
    }


    //ORDENDELDIA
    //Listar
    public List<OrdenDelDia> listarOrdenDelDia() {
        return this.repositorio.buscarTodos(OrdenDelDia.class);
    }
    
    //Buscar
    public OrdenDelDia buscarOrdenDelDia(Long idOden) {
        return this.repositorio.buscar(OrdenDelDia.class, idOden);
    }
    //Agregar
    public void agregarOrdenDelDia(LocalDate fecha, Boolean estado, Tema temas,Expediente expedientesAbiertos) {
        if (fecha == null || estado  == null ||expedientesAbiertos == null ||  temas  == null) {
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repositorio.iniciarTransaccion();
        OrdenDelDia ordenDelDia = new OrdenDelDia(fecha, estado, temas, expedientesAbiertos);
        this.repositorio.insertar(ordenDelDia);
        this.repositorio.confirmarTransaccion();
    }
    
    // Cambiar valor devuelto
    public void editarOrdenDelDia(Long idOrden, LocalDate fecha, Boolean estado, Tema temas,Expediente expedientesAbiertos) {
        if (fecha == null || estado  == null ||expedientesAbiertos == null ||  temas  == null) {
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repositorio.iniciarTransaccion();
        OrdenDelDia ordenDelDia = this.repositorio.buscar(OrdenDelDia.class, idOrden);
        if (ordenDelDia != null ) {
            ordenDelDia.setFecha(fecha);
            ordenDelDia.setEstado(estado);
            ordenDelDia.setExpedientesAbiertos(expedientesAbiertos);
            if (! ordenDelDia.getTemas().equals(temas)) {
                ordenDelDia.getTemas().getOrdenesdelDia().remove(ordenDelDia);
                ordenDelDia.setTemas(temas);
                temas.getOrdenesdelDia().add(ordenDelDia);
            }
            this.repositorio.modificar(ordenDelDia);
            this.repositorio.confirmarTransaccion();
        } else {
            this.repositorio.descartarTransaccion();
        }
    }
    //Eliminar 
    public int eliminarOdenDelDia(Long idOrden) {
        this.repositorio.iniciarTransaccion();
        OrdenDelDia ordenDelDia = this.repositorio.buscar(OrdenDelDia.class, idOrden);
        if (ordenDelDia != null ) {
            this.repositorio.eliminar(ordenDelDia);
            this.repositorio.confirmarTransaccion();
            return 0;
        } else {
            this.repositorio.descartarTransaccion();
            return 1;
        }
    }

    //Involucrados
    //Listar
    public List<Involucrado> listarInvolucrados() {
        return this.repositorio.buscarTodos(Involucrado.class);
    }
    
    //Buscar
    public Involucrado buscaroOrdenDelDia(Long idInvolucrados) {
        return this.repositorio.buscar(Involucrado.class, idInvolucrados);
    }
    //Agregar
    public void agregarInvolucrado(String nombre, Long dni,int telefono, String email, String rol, Boolean esmiembroConsejo,
    Boolean esIniciante, Expediente expedientes) {
        if ( nombre.trim().length() == 0 || dni==null || telefono== 0 ||  email.trim().length() == 0 || rol.trim().length() == 0 || esmiembroConsejo== null
        || esIniciante ==null || expedientes == null) {
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repositorio.iniciarTransaccion();
        Involucrado involucrado = new Involucrado(nombre, dni, telefono, email, rol, esmiembroConsejo, esIniciante,  expedientes);
        this.repositorio.insertar(involucrado);
        this.repositorio.confirmarTransaccion();
    }
    
    // Cambiar valor devuelto
    public void editarInvolucrado(Long idInvolucrado, String nombre, Long dni,int telefono, String email, String rol, Boolean esmiembroConsejo,
    Boolean esIniciante, Expediente expedientes) {
        if (  nombre.trim().length() == 0 || dni==null || telefono== 0 ||  email.trim().length() == 0 || rol.trim().length() == 0 || esmiembroConsejo== null
        || esIniciante ==null || expedientes == null) {
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repositorio.iniciarTransaccion();
        Involucrado involucrado = this.repositorio.buscar(Involucrado.class, idInvolucrado);
        if (involucrado != null ) {
            involucrado.setNombre(nombre);
            involucrado.setDni(dni);
            involucrado.setTelefono(telefono);
            involucrado.setEmail(email);
            involucrado.setRol(rol);
            involucrado.setEsIniciante(esIniciante);
            involucrado.setEsmiembroConsejo(esmiembroConsejo);
            if (! involucrado.getExpedientes().equals(expedientes)) {
                involucrado.getExpedientes().getInvolucrados().remove(involucrado);
                involucrado.setExpedientes(expedientes);
                expedientes.getInvolucrados().add(involucrado);
            }
            this.repositorio.modificar(involucrado);
            this.repositorio.confirmarTransaccion();
        } else {
            this.repositorio.descartarTransaccion();
        }
    }
    //Eliminar 
    public int eliminarInvolucrados(Long idInvolucrados) {
        this.repositorio.iniciarTransaccion();
        Involucrado involucrados = this.repositorio.buscar(Involucrado.class, idInvolucrados);
        if (involucrados != null && involucrados.getAsistencias().isEmpty()) {
            this.repositorio.eliminar(involucrados);
            this.repositorio.confirmarTransaccion();
            return 0;
        } else {
            this.repositorio.descartarTransaccion();
            return 1;
        }
    }

    //Reunion
    //Listar
    public List<Reunion> listarReuniones() {
        return this.repositorio.buscarTodos(Reunion.class);
    }
    
    //Buscar
    public Reunion buscarReunion(Long idReunion) {
        return this.repositorio.buscar(Reunion.class, idReunion);
    }
    //Agregar
    public void agregarReunion(LocalDate fecha, Boolean estadoReunion, LocalDateTime horaInicio, LocalDateTime horaFin, OrdenDelDia ordenDelDia) {
        if ( fecha == null || estadoReunion == null || horaInicio == null ||  horaFin==null || ordenDelDia == null){
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repositorio.iniciarTransaccion();
        Reunion reunion = new Reunion( fecha,  estadoReunion,  horaInicio, horaFin,ordenDelDia);
        this.repositorio.insertar(reunion);
        this.repositorio.confirmarTransaccion();
    }
    
    // Cambiar valor devuelto
    public void editarReunion(Long idReunion,LocalDate fecha, Boolean estadoReunion, LocalDateTime horaInicio, LocalDateTime horaFin, OrdenDelDia ordenDelDia) {
        if ( fecha == null || estadoReunion == null || horaInicio == null ||  horaFin==null || ordenDelDia == null){
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repositorio.iniciarTransaccion();
        Reunion reunion = this.repositorio.buscar(Reunion.class, idReunion);
        if (reunion != null ) {
            reunion.setFecha(fecha);
            reunion.setEstadoReunion(estadoReunion);
            reunion.setHoraInicio(horaInicio);
            reunion.setHoraFin(horaFin);
            reunion.setOrdenDelDia(ordenDelDia);
            this.repositorio.modificar(reunion);
            this.repositorio.confirmarTransaccion();
        } else {
            this.repositorio.descartarTransaccion();
        }
    }
    //Eliminar 
    public int eliminarReunion(Long idReunion) {
        this.repositorio.iniciarTransaccion();
        Reunion reunion = this.repositorio.buscar(Reunion.class, idReunion);
        if (reunion != null && reunion.getAsistencias().isEmpty()) {
            this.repositorio.eliminar(reunion);
            this.repositorio.confirmarTransaccion();
            return 0;
        } else {
            this.repositorio.descartarTransaccion();
            return 1;
        }
    }

    //Asistencia
    //Listar
    public List<Asistencia> listarAsistencias() {
        return this.repositorio.buscarTodos(Asistencia.class);
    }
    
    //Buscar
    public Asistencia buscarAsistencia(Long idAsistencia) {
        return this.repositorio.buscar(Asistencia.class, idAsistencia);
    }
    //Agregar
    public void agregarAsistencia(boolean presencia,LocalDate fecha,Involucrado involucrado) {
        if ( fecha==null||involucrado==null) {
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repositorio.iniciarTransaccion();
        Asistencia asistencia = new Asistencia(presencia, fecha, involucrado);
        this.repositorio.insertar(asistencia);
        this.repositorio.confirmarTransaccion();
    }
    
    // Cambiar valor devuelto
    public void editarAsistencia(Long idAsistencia, boolean presencia,LocalDate fecha,Involucrado involucrado) {
        if (fecha==null||involucrado==null) {
            throw new IllegalArgumentException("Faltan datos");
        }
        this.repositorio.iniciarTransaccion();
        Asistencia asistencia = this.repositorio.buscar(Asistencia.class, idAsistencia);
        if (asistencia != null ) {
            asistencia.setPresencia(presencia);
            asistencia.setFecha(fecha);
            if (! asistencia.getInvolucrados().equals(involucrado)) {
                asistencia.getInvolucrados().getAsistencias().remove(asistencia);
                asistencia.setInvolucrados(involucrado);
                involucrado.getAsistencias().add(asistencia);
            }
            this.repositorio.modificar(asistencia);
            this.repositorio.confirmarTransaccion();
        } else {
            this.repositorio.descartarTransaccion();
        }
    }
    //Eliminar 
    public int eliminarAsistencia(Long idAsistencia) {
        this.repositorio.iniciarTransaccion();
        Asistencia asistencia = this.repositorio.buscar(Asistencia.class, idAsistencia);
        if (asistencia != null && asistencia.getReuniones().isEmpty()) {
            this.repositorio.eliminar(asistencia);
            this.repositorio.confirmarTransaccion();
            return 0;
        } else {
            this.repositorio.descartarTransaccion();
            return 1;
        }
    }

}
