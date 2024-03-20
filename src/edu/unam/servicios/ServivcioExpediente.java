package edu.unam.servicios;

import edu.unam.repositorios.*;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import edu.unam.modelo.Expediente;

public class ServivcioExpediente {
    private Repositorio repositorio;

    public ServivcioExpediente(Repositorio p) {
        this.repositorio = p;
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
    //Listas Verificacion de un Atributo ya existente
    public boolean expedienteExiste(Long numeroExpediente) {
        List<Expediente> expedientes = listarExpedientes();
        for (Expediente expediente : expedientes) {
            if (expediente.getNroExpediente().equals(numeroExpediente)) {
                return true; // Si el número de expediente coincide, devuelve true
            }
        }
        return false; // Si no se encuentra ningún expediente con el número dado, devuelve false
    }
    //Listas buscapor numero de un Atributo ya existente
    public List<Expediente> buscarExpedientePorNumero(Long numeroExpediente) {
        List<Expediente> expedientesEncontrados = new ArrayList<>();
        List<Expediente> expedientes = listarExpedientes();
        for (Expediente expediente : expedientes) {
            if (expediente.getNroExpediente().equals(numeroExpediente)) {
                expedientesEncontrados.add(expediente);
            }
        }
        return expedientesEncontrados;
    }
    //Buscar
    public Expediente buscarExpediente(Long idExpediente) {
        return this.repositorio.buscar(Expediente.class, idExpediente);
    }
    //BuscarTodos
    public List<Expediente> buscarTodos() {
        return this.repositorio.buscarTodos(Expediente.class);
    }

    public int eliminarExpediente(Long nroExpediente) {
        this.repositorio.iniciarTransaccion();
        Expediente expediente = this.repositorio.buscar(Expediente.class, nroExpediente);
        if (expediente != null) {
            this.repositorio.eliminar(expediente);
            this.repositorio.confirmarTransaccion();
            return 0;
        } else {
            this.repositorio.descartarTransaccion();
            return 1;
        }
    }

    public void agregarExpediente(Long nroExpediente, String iniciante , String tema, String textoNota , LocalDate fechaingresoFacultad, Boolean estadoExpediente) {
        if (nroExpediente == null ||iniciante.trim().length() == 0 ||tema.trim().length() == 0 ||textoNota.trim().length() == 0 || fechaingresoFacultad  == null || estadoExpediente == null) {
            throw new IllegalArgumentException("Faltan datos para agregar el Expediente");
        }
        this.repositorio.iniciarTransaccion();
        Expediente expediente = new Expediente(nroExpediente, iniciante.toUpperCase().trim(), tema.toUpperCase().trim(),textoNota.toUpperCase().trim(),fechaingresoFacultad,estadoExpediente);
        this.repositorio.insertar(expediente);
        this.repositorio.confirmarTransaccion();
    }



    public void editarExpediente(Long idExpediente, Long nroExpediente, String iniciante, String tema, String nota, LocalDate fechaIngreso, Boolean estadoExpediente) {
        this.repositorio.iniciarTransaccion();
        Expediente expediente = this.repositorio.buscar(Expediente.class, nroExpediente);
        if (expediente != null) {
            expediente.getIdExpediente();
            expediente.setNroExpediente(nroExpediente);
            expediente.setIniciante(iniciante.toUpperCase().trim());
            expediente.setTema(tema.toUpperCase().trim());
            expediente.setTextoNota(nota.toUpperCase().trim());
            expediente.setFechaingresoFacultad(fechaIngreso);
            expediente.setEstadoExpediente(estadoExpediente);
            this.repositorio.modificar(expediente);
            this.repositorio.confirmarTransaccion();
        } else {
            this.repositorio.descartarTransaccion();
        }
    }
}
