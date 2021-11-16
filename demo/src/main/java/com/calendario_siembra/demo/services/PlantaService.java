/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.calendario_siembra.demo.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.calendario_siembra.demo.entity.Foto;
import com.calendario_siembra.demo.entity.Planta;
import com.calendario_siembra.demo.exceptions.WebException;
import com.calendario_siembra.demo.repository.PlantaRepository;

@Service
public class PlantaService {

	@Autowired
	private PlantaRepository plantaRepository;

	@Autowired
	private FotoService fotoService;

	@Transactional
	public Planta buscarPlanta(String nombre) {
		return plantaRepository.findByNombre(nombre);
	}

	@Transactional
	public Optional<Planta> buscarPlantaID(String id) {
		return plantaRepository.findById(id);
	}

	@Transactional
	public Planta altaPlanta(Planta planta) {
		return plantaRepository.save(planta);
	}

	@Transactional
	public Planta bajaPlanta(String id) throws WebException {
		Optional<Planta> rta = plantaRepository.findById(id);
		if (rta.isPresent()) {
			if (rta.get().getEstado())
				rta.get().setEstado(false);
			else
				rta.get().setEstado(true);
		} else {
			throw new WebException("No se encontró la planta solicitada");
		}
		return plantaRepository.save(rta.get());
	}

	public List<Planta> listarPlantas() {
		return plantaRepository.findAll();
	}

	// Metodo creado para el uso exclusivo de los administradores
	public void registrar(String nombre, String tipoCultivo, String profundidadSiembra, Integer horasSol,
			String cantidadRiego, String cosecha, String heladas, String diasCosecha, String mesSiembra,
			String descripcion, MultipartFile archivo) throws WebException {

		Planta planta = new Planta();
		planta.setNombre(nombre);
		planta.setTipoCultivo(tipoCultivo);
		planta.setProfundidadSiembra(profundidadSiembra);
		planta.setHorasSol(horasSol);
		planta.setCantidadRiego(cantidadRiego);
		planta.setCosecha(cosecha);
		planta.setHeladas(heladas);
		planta.setDiasCosecha(diasCosecha);
		planta.setMesSiembra(mesSiembra);
		planta.setDescripcion(descripcion);

		Foto foto = fotoService.guardarFoto(archivo);
		planta.setFoto(foto);

		plantaRepository.save(planta);
	}

	// Metodo creado para el uso exclusivo de los administradores
	public void modificar(String id, String nombre, String tipoCultivo, String profundidadSiembra, Integer horasSol,
			String cantidadRiego, String cosecha, String heladas, String diasCosecha, String mesSiembra,
			String descripcion, MultipartFile archivo) throws WebException {

		Optional<Planta> rta = plantaRepository.findById(id);
		if (rta.isPresent()) {
			Planta planta = rta.get();
			planta.setNombre(nombre);
			planta.setTipoCultivo(tipoCultivo);
			planta.setProfundidadSiembra(profundidadSiembra);
			planta.setHorasSol(horasSol);
			planta.setCantidadRiego(cantidadRiego);
			planta.setCosecha(cosecha);
			planta.setHeladas(heladas);
			planta.setDiasCosecha(diasCosecha);
			planta.setMesSiembra(mesSiembra);
			planta.setDescripcion(descripcion);

			String idFoto = null;
			if (planta.getFoto() != null) {
				idFoto = planta.getFoto().getId();
			}

			Foto foto = fotoService.actualizarFoto(idFoto, archivo);
			planta.setFoto(foto);

			plantaRepository.save(planta);
		} else {
			throw new WebException("No se encontró la planta solicitada");
		}

	}

}
