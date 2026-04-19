package com.trinitarias.proyecto_tfg_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TfgUsuariosDto {


    private Long id_usuario;
    private String nombre_usuario;
    private String email;
    private String contrasena;


    private Boolean esLogin = false;

    public TfgUsuariosDto() {
        super();
    }

    public TfgUsuariosDto(Long id_usuario, String nombre_usuario, String email, String contrasena, Boolean esLogin) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.email = email;
        this.contrasena = contrasena;
        this.esLogin = esLogin;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getEsLogin() {
        return esLogin;
    }

    public void setEsLogin(Boolean esLogin) {
        this.esLogin = esLogin;
    }
}
