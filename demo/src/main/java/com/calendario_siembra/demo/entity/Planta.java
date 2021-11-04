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
	private String tipoCultivo;

	@Column
	private String profundidadSiembra;

	@Column
	private Integer horasSol;

	@Column
	private String cantidadRiego;

	@Column
	private String cosecha;

	@Column
	private String heladas;

	@Column
	private String diasCosecha;

	@Column
	private String mesSiembra;

	@Column
	private String descripcion;

	@Column
	private Boolean estado = true;

	public Planta() {
		super();
	}

	public Planta(String id, String nombre, String tipoCultivo, String profundidadSiembra, Integer horasSol,
			String cantidadRiego, String cosecha, String heladas, String diasCosecha, String mesSiembra,
			String descripcion, Parcela parcela) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipoCultivo = tipoCultivo;
		this.profundidadSiembra = profundidadSiembra;
		this.horasSol = horasSol;
		this.cantidadRiego = cantidadRiego;
		this.cosecha = cosecha;
		this.heladas = heladas;
		this.diasCosecha = diasCosecha;
		this.mesSiembra = mesSiembra;
		this.descripcion = descripcion;
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

	public String getTipoCultivo() {
		return tipoCultivo;
	}

	public void setTipoCultivo(String tipoCultivo) {
		this.tipoCultivo = tipoCultivo;
	}

	public String getProfundidadSiembra() {
		return profundidadSiembra;
	}

	public void setProfundidadSiembra(String profundidadSiembra) {
		this.profundidadSiembra = profundidadSiembra;
	}

	public Integer getHorasSol() {
		return horasSol;
	}

	public void setHorasSol(Integer horasSol) {
		this.horasSol = horasSol;
	}

	public String getCantidadRiego() {
		return cantidadRiego;
	}

	public void setCantidadRiego(String cantidadRiego) {
		this.cantidadRiego = cantidadRiego;
	}

	public String getCosecha() {
		return cosecha;
	}

	public void setCosecha(String cosecha) {
		this.cosecha = cosecha;
	}

	public String getHeladas() {
		return heladas;
	}

	public void setHeladas(String heladas) {
		this.heladas = heladas;
	}

	public String getDiasCosecha() {
		return diasCosecha;
	}

	public void setDiasCosecha(String diasCosecha) {
		this.diasCosecha = diasCosecha;
	}

	public String getMesSiembra() {
		return mesSiembra;
	}

	public void setMesSiembra(String mesSiembra) {
		this.mesSiembra = mesSiembra;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

}
