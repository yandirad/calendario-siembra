/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.calendario_siembra.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.calendario_siembra.demo.entity.Parcela;
import com.calendario_siembra.demo.entity.Planta;
import com.calendario_siembra.demo.entity.Usuario;
import com.calendario_siembra.demo.exceptions.WebException;
import com.calendario_siembra.demo.repository.ParcelaRepository;
import com.calendario_siembra.demo.repository.PlantaRepository;
import com.calendario_siembra.demo.repository.UsuarioRepository;
import java.util.Iterator;

/**
 *
 * @author yandirad
 */
@Service
public class ParcelaService {

	@Autowired
	private ParcelaRepository parcelaRepository;

	@Autowired
	private PlantaRepository plantaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class })
	public Parcela crearParcela(Parcela parcela) throws WebException {
		validar(parcela);
		List<Planta> listaPlantas = new ArrayList();
		parcela.setListaPlantas(listaPlantas);
		return parcelaRepository.save(parcela);
	}

	@Transactional
	public Parcela modificarParcela(Parcela parcela) throws WebException {
		validar(parcela);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String nickName = auth.getName();
		Usuario usuario = usuarioRepository.findByUsuario(nickName);
		parcela.setUsuario(usuario);
		return parcelaRepository.save(parcela);
	}

	@Transactional
	public List<Parcela> obtenerListaParcelas(Usuario usuario) throws WebException {
		return parcelaRepository.obtenerListaParcelas(usuario);
	}

	public Parcela agregarPlanta(String parcelaID, String plantaID) throws WebException {
		Optional<Planta> planta = plantaRepository.findById(plantaID);
		Optional<Parcela> parcela = parcelaRepository.findById(parcelaID);
		parcela.get().getListaPlantas().add(planta.get());
		return parcelaRepository.save(parcela.get());
	}

	public void validar(Parcela parcela) throws WebException {

		if (parcela.getNombre().isEmpty() || parcela.getNombre().equals("") || parcela.getNombre() == null) {
			throw new WebException("El nombre no puede estar vacío");
		}
		if (parcela.getTamanioParcela() == 0 || parcela.getTamanioParcela() == null) {
			throw new WebException("El tamaño de la parcela no puede estar vacío ni ser 0");
		}

	}

	public void bajaParcela(String parcelaID) throws WebException {
		Optional<Parcela> parcela = parcelaRepository.findById(parcelaID);
		parcela.get().setEstado(false);
		parcelaRepository.save(parcela.get());
	}

	public Parcela bajaPlanta(String parcelaId, String plantaId) throws WebException {
                Optional<Parcela> parcela = parcelaRepository.findById(parcelaId);
                List<Planta> plantas = parcela.get().getListaPlantas();
                
                Iterator<Planta> it = plantas.iterator();
                while(it.hasNext()){
                    Planta planta = (Planta) it.next();
                    if(planta.getId().equals(plantaId)){
                        plantas.remove(planta);
                        break;
                    }
                    
                }
		return parcelaRepository.save(parcela.get());
	}

}
