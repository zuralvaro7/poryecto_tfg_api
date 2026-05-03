package com.trinitarias.proyecto_tfg_api.dto;

public class TfgUsuariosDto {


    private Long id_usuario;
    private String nombre_usuario;
    private String email;
    private String contrasena;
    private String veri_token;
    private Boolean activo;


    private Boolean esLogin = false;

    public TfgUsuariosDto() {
        super();
    }

    public TfgUsuariosDto(Long id_usuario, String nombre_usuario, String email, String contrasena, String veri_token, Boolean activo, Boolean esLogin) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.email = email;
        this.contrasena = contrasena;
        this.veri_token = veri_token;
        this.activo = activo;
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

    public String getVeri_token() {
        return veri_token;
    }

    public void setVeri_token(String veri_token) {
        this.veri_token = veri_token;
    }

    public Boolean isActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getEsLogin() {
        return esLogin;
    }

    public void setEsLogin(Boolean esLogin) {
        this.esLogin = esLogin;
    }
}

