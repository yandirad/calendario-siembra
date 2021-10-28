/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calendario_siembra.repository;

import com.calendario_siembra.entity.Planta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yandirad
 */
@Repository
public interface PlantaRepository extends JpaRepository<Planta, String>{
    
}
