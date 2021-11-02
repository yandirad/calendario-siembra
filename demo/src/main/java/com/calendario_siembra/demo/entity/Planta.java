package com.calendario_siembra.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Planta implements Serializable {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column
	private String nombre;

	@Column
	private Integer horasSol;

	@Column
	private String tipoRiego;

	@Column
	private String formaSiembra;

	@Temporal(TemporalType.DATE)
	private Date fechaSiembra;

	@Temporal(TemporalType.DATE)
	private Date fechaCosecha;

	@Column
	private Integer tiempoCosecha;

	@Column
	private String profundidadSiembra;

	@Column
	private Integer tiempoGerminacion;

	@ManyToOne
	private Parcela parcela;
	
	@Column
	private Boolean estado=true;

	public Planta() {
		super();
	}

	public Planta(String id, String nombre, Integer horasSol, String tipoRiego, String formaSiembra, Date fechaSiembra,
			Date fechaCosecha, Integer tiempoCosecha, String profundidadSiembra, Integer tiempoGerminacion,
			Parcela parcela) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.horasSol = horasSol;
		this.tipoRiego = tipoRiego;
		this.formaSiembra = formaSiembra;
		this.fechaCosecha = fechaCosecha;
		this.tiempoCosecha = tiempoCosecha;
		this.profundidadSiembra = profundidadSiembra;
		this.tiempoGerminacion = tiempoGerminacion;
		this.parcela = parcela;
	}

	public Parcela getParcela() {
		return parcela;
	}

	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
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

	public Integer getHorasSol() {
		return horasSol;
	}

	public void setHorasSol(Integer horasSol) {
		this.horasSol = horasSol;
	}

	public String getTipoRiego() {
		return tipoRiego;
	}

	public void setTipoRiego(String tipoRiego) {
		this.tipoRiego = tipoRiego;
	}

	public String getFormaSiembra() {
		return formaSiembra;
	}

	public void setFormaSiembra(String formaSiembra) {
		this.formaSiembra = formaSiembra;
	}

	public Date getFechaSiembra() {
		return fechaSiembra;
	}

	public void setFechaSiembra(Date fechaSiembra) {
		this.fechaSiembra = fechaSiembra;
	}

	public Date getFechaCosecha() {
		return fechaCosecha;
	}

	public void setFechaCosecha(Date fechaCosecha) {
		this.fechaCosecha = fechaCosecha;
	}

	public Integer getTiempoCosecha() {
		return tiempoCosecha;
	}

	public void setTiempoCosecha(Integer tiempoCosecha) {
		this.tiempoCosecha = tiempoCosecha;
	}

	public String getProfundidadSiembra() {
		return profundidadSiembra;
	}

	public void setProfundidadSiembra(String profundidadSiembra) {
		this.profundidadSiembra = profundidadSiembra;
	}

	public Integer getTiempoGerminacion() {
		return tiempoGerminacion;
	}

	public void setTiempoGerminacion(Integer tiempoGerminacion) {
		this.tiempoGerminacion = tiempoGerminacion;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	

}
