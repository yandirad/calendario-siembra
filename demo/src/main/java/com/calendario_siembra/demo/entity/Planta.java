package com.calendario_siembra.demo.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

	@Column
	private String[] mesesSiembra;

	@Column
	private Integer tiempoCosecha;

	@Column
	private String profundidadSiembra;

	@Column
	private Integer tiempoGerminacion;

	public Planta() {
		super();
	}

	public Planta(String id, String nombre, Integer horasSol, String tipoRiego, String formaSiembra,
			String[] mesesSiembra, Integer tiempoCosecha, String profundidadSiembra, Integer tiempoGerminacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.horasSol = horasSol;
		this.tipoRiego = tipoRiego;
		this.formaSiembra = formaSiembra;
		this.mesesSiembra = mesesSiembra;
		this.tiempoCosecha = tiempoCosecha;
		this.profundidadSiembra = profundidadSiembra;
		this.tiempoGerminacion = tiempoGerminacion;
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

	public String[] getMesesSiembra() {
		return mesesSiembra;
	}

	public void setMesesSiembra(String[] mesesSiembra) {
		this.mesesSiembra = mesesSiembra;
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

}
