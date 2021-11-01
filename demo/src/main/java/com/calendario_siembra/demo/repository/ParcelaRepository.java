/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calendario_siembra.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.calendario_siembra.demo.entity.Parcela;

/**
 *
 * @author yandirad
 */
@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, String> {

	public List<Parcela> obtenerListaParcelas(@Param("id_usuario") String id_usuario);

}
