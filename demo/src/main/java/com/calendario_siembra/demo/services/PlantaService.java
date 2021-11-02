/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.calendario_siembra.demo.services;

import com.calendario_siembra.demo.entity.Planta;
import com.calendario_siembra.demo.repository.PlantaRepository;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * 
 */
@Service
public class PlantaService {
    
    @Autowired
    private PlantaRepository plantaRepository;
    
    @Transactional
    public Planta buscarPlanta(String nombre) { 
        return plantaRepository.findByNombre(nombre); 
    }
    
    
    @Transactional
    public Planta altaPlanta(Planta planta) { 
        return plantaRepository.save(planta); 
    }
    
    @Transactional
    public Planta bajaPlanta(Planta planta) { 
    	planta.setEstado(false);
    	return plantaRepository.save(planta);
    }


}
