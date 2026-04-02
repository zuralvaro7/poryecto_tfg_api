package com.trinitarias.proyecto_tfg_api.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name="historial")
public class TfgHistorialEntity {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_historial")
    private Long id_historial;

    @Column(name="numero_capitulo")
    private String numero_capitulo;

    @Column(name="nombre_capitulo")
    private String nombre_capitulo;

    @Column(name="dia_visto")
    private Date dia_visto;

    @Column(name="hora")
    private Time hora;

	public TfgHistorialEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TfgHistorialEntity(Long id_historial, String numero_capitulo, String nombre_capitulo, Date dia_visto,
			Time hora) {
		super();
		this.id_historial = id_historial;
		this.numero_capitulo = numero_capitulo;
		this.nombre_capitulo = nombre_capitulo;
		this.dia_visto = dia_visto;
		this.hora = hora;
	}

	public Long getId_historial() {
		return id_historial;
	}

	public void setId_historial(Long id_historial) {
		this.id_historial = id_historial;
	}

	public String getNumero_capitulo() {
		return numero_capitulo;
	}

	public void setNumero_capitulo(String numero_capitulo) {
		this.numero_capitulo = numero_capitulo;
	}

	public String getNombre_capitulo() {
		return nombre_capitulo;
	}

	public void setNombre_capitulo(String nombre_capitulo) {
		this.nombre_capitulo = nombre_capitulo;
	}

	public Date getDia_visto() {
		return dia_visto;
	}

	public void setDia_visto(Date dia_visto) {
		this.dia_visto = dia_visto;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

    
}
