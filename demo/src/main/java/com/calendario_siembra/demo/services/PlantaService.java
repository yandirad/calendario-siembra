/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.calendario_siembra.demo.services;

import com.calendario_siembra.demo.repository.PlantaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author yandirad
 */
@Service
public class PlantaService {
    
    @Autowired
    private PlantaRepository plantaRepository;


}
