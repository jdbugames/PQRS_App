package com.example.pqrs.Models;
import java.io.Serializable;

public class UsuarioModel implements Serializable {


    private String id;
    private String nombre;
    private String cedula;
    private String usuario;
    private String contrasena;

    public UsuarioModel() {

    }

    public UsuarioModel(String nombre, String cedula, String usuario, String contrasena) {
        this.setNombre(nombre);
        this.setCedula(cedula);
        this.setUsuario(usuario);
        this.setContrasena(contrasena);
    }

    @Override
    public String toString() {
        return "UsuarioModel{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", cedula='" + cedula + '\'' +
                ", usuario='" + usuario + '\'' +
                ", contrasena='" + contrasena + '\'' +
                '}';
    }

    public UsuarioModel(String id, String nombre, String cedula, String usuario, String contrasena) {
        this.setId(id);
        this.setNombre(nombre);
        this.setCedula(cedula);
        this.setUsuario(usuario);
       this.setContrasena(contrasena);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String clave) {
        this.contrasena = contrasena;
    }
}