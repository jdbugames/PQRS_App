package com.example.pqrs.Models;

public class PqrsModel {
    private String id;
    private String  tipo;
    private String  titulo;
    private String  clase;
    private String  profesor;
    private String  contenido;

    @Override
    public String toString() {
        return "PqrsModel{" +
                "id='" + id + '\'' +
                ", tipo='" + tipo + '\'' +
                ", titulo='" + titulo + '\'' +
                ", clase='" + clase + '\'' +
                ", profesor='" + profesor + '\'' +
                ", contenido='" + contenido + '\'' +
                '}';
    }

    public PqrsModel() {
    }

    public PqrsModel(String tipo, String titulo, String clase, String profesor, String contenido) {
        this.setTipo(tipo);
        this.setTitulo(titulo);
        this.setClase(clase);
        this.setProfesor(profesor);
        this.setContenido(contenido);
    }

    public PqrsModel(String id, String tipo, String titulo, String clase, String profesor, String contenido) {
        this.setId(id);
        this.setTipo(tipo);
        this.setTitulo(titulo);
        this.setClase(clase);
        this.setProfesor(profesor);
        this.setContenido(contenido);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}