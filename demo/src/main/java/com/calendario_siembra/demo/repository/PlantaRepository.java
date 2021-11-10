/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calendario_siembra.demo.repository;

import com.calendario_siembra.demo.entity.Planta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yandirad
 */
@Repository
public interface PlantaRepository extends JpaRepository<Planta, String>{
	
	public Planta findByNombre(String nombre);
                   
}
