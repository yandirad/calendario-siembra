/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.calendario_siembra.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.calendario_siembra.demo.entity.Parcela;
import com.calendario_siembra.demo.entity.Planta;
import com.calendario_siembra.demo.entity.Usuario;
import com.calendario_siembra.demo.exceptions.WebException;
import com.calendario_siembra.demo.repository.ParcelaRepository;

/**
 *
 * @author yandirad
 */
@Service
public class ParcelaService {

	@Autowired
	private ParcelaRepository parcelaRepository;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class })
	public Parcela crearParcela(Parcela parcela) throws WebException {
		validar(parcela);
		List<Planta> listaPlantas = new ArrayList();
		parcela.setListaPlantas(listaPlantas);
		return parcelaRepository.save(parcela);
	}

	@Transactional
	public List<Parcela> obtenerListaParcelas(Usuario usuario) throws WebException {
		return parcelaRepository.obtenerListaParcelas(usuario);
	}

	public Parcela agregarPlanta(Parcela parcela, Planta planta) {
		parcela.getListaPlantas().add(planta);
		return parcelaRepository.save(parcela);
	}

	public void validar(Parcela parcela) throws WebException {

		if (parcela.getNombre().isEmpty() || parcela.getNombre().equals("") || parcela.getNombre() == null) {
			throw new WebException("El nombre no puede estar vacío");
		}
		if (parcela.getTamanioParcela() == 0 || parcela.getTamanioParcela() == null) {
			throw new WebException("El tamaño de la parcela no puede estar vacío ni ser 0");
		}

	}

}
