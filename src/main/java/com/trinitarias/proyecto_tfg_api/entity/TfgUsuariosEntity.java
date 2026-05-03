package com.trinitarias.proyecto_tfg_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name="usuarios")
public class TfgUsuariosEntity {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Long id_usuario;

    @Column(name="nombre_usuario")
    private String nombre_usuario;

    @Column(name="email")
    private String email;

    @Column(name="contrasena")
    private String contrasena;

	@Column(name="veri_token")
	private String veri_token;

	@Column(name="activo")
	private Boolean activo;

	public TfgUsuariosEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TfgUsuariosEntity(Long id_usuario, String nombre_usuario, String email, String contrasena, String veri_token, Boolean activo) {
		this.id_usuario = id_usuario;
		this.nombre_usuario = nombre_usuario;
		this.email = email;
		this.contrasena = contrasena;
		this.veri_token = veri_token;
		this.activo = activo;
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
}
