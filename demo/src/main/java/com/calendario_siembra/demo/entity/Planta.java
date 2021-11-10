package com.calendario_siembra.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
public class Planta implements Serializable {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column
	private String nombre;

	// frutal, hortaliza, etc
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

	// tiempo que lleva hasta la cosecha.. ejemplo:90 dias
	@Column
	private String diasCosecha;

	@Column
	private String mesSiembra;

	@Column
	@Type(type = "text")
	private String descripcion;

	@Column
	private Boolean estado = true;

	@OneToOne
	private Foto foto;

	public Planta() {
		super();
	}

	public Planta(String nombre, String tipoCultivo, String profundidadSiembra, Integer horasSol, String cantidadRiego,
			String cosecha, String heladas, String diasCosecha, String mesSiembra, String descripcion, Foto foto) {
		super();
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
		this.foto = foto;
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

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

}
