package com.trinitarias.proyecto_tfg_api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDate;

@Entity
@Table(name="historial")
public class TfgHistorialEntity {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_historial")
    private Long id_historial;

    @Column(name="nombre_capitulo")
    private String nombre_capitulo;

	@Column(name="numero_capitulo")
	private Long numero_capitulo;

    @Column(name="dia_visto")
    private LocalDate dia_visto;

    @Column(name="hora")
    private Time hora;

	@Column(name="id_usuario")
	private Long id_usuario;

	public TfgHistorialEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TfgHistorialEntity(Long id_historial, Long numero_capitulo, String nombre_capitulo, LocalDate dia_visto, Time hora, Long id_usuario) {
		this.id_historial = id_historial;
		this.numero_capitulo = numero_capitulo;
		this.nombre_capitulo = nombre_capitulo;
		this.dia_visto = dia_visto;
		this.hora = hora;
		this.id_usuario = id_usuario;
	}

	public Long getId_historial() {
		return id_historial;
	}

	public void setId_historial(Long id_historial) {
		this.id_historial = id_historial;
	}

	public Long getNumero_capitulo() {
		return numero_capitulo;
	}

	public void setNumero_capitulo(Long numero_capitulo) {
		this.numero_capitulo = numero_capitulo;
	}

	public String getNombre_capitulo() {
		return nombre_capitulo;
	}

	public void setNombre_capitulo(String nombre_capitulo) {
		this.nombre_capitulo = nombre_capitulo;
	}

	public LocalDate getDia_visto() {
		return dia_visto;
	}

	public void setDia_visto(LocalDate dia_visto) {
		this.dia_visto = dia_visto;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}
}
