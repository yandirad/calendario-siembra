/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calendario_siembra.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.calendario_siembra.demo.entity.Parcela;
import com.calendario_siembra.demo.entity.Planta;
import com.calendario_siembra.demo.entity.Usuario;
import com.calendario_siembra.demo.exceptions.WebException;
import com.calendario_siembra.demo.services.ParcelaService;
import com.calendario_siembra.demo.services.PlantaService;
import com.calendario_siembra.demo.services.UsuarioService;

@Controller
@RequestMapping("/my-account")
public class MyAccountController {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	ParcelaService parcelaService;

	@Autowired
	PlantaService plantaService;

	// vista inicial del perfil de cada usuario
	@GetMapping("/")
	public String verDatos(Model modelo, @RequestParam(required = false) String error) {
		Usuario usuario = usuarioService.buscarUsuario();
		if (usuario == null) {
			return "redirect:/login";
		}
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("nuevaParcela", new Parcela(usuario));
		modelo.addAttribute("listaCultivos", plantaService.listarPlantas());
		modelo.addAttribute("nuevaPlanta", new Planta());
		modelo.addAttribute("error", error);
		return "myaccount.html";
	}

	// Metodo para modificar datos del usuario, mediante pop-up
	@PostMapping("/usuario-modificar")
	public String modificarUsuario(Usuario usuario, RedirectAttributes ra) {
		try {
			usuarioService.guardarUsuario(usuario);
		} catch (WebException e) {
			ra.addAttribute("error", e.getMessage());
		}

		return "redirect:/my-account/";
	}

	// Método para crear/modificar una nueva parcela
	@PostMapping("/parcela-alta-modificar")
	public String crearParcela(Parcela parcela, Model modelo) {
		try {
			if (parcela.getId() == null) {
				parcelaService.crearParcela(parcela);
			} else {
				parcelaService.modificarParcela(parcela);
			}
		} catch (WebException e) {
			modelo.addAttribute("error", e.getMessage());
		}

		return "redirect:/my-account/";
	}

	// Método para dar la baja a una parcela
	@PostMapping("/parcela-baja")
	public String bajaParcela(String parcelaID, Model modelo) {
		try {
			parcelaService.bajaParcela(parcelaID);
		} catch (WebException e) {
			modelo.addAttribute("error", e.getMessage());
		}

		return "redirect:/my-account/";
	}

	// Método para dar la alta a una planta
	@PostMapping("/planta-alta")
	public String altaPlanta(String parcelaID, String plantaID, Model modelo) {
		try {
			parcelaService.agregarPlanta(parcelaID, plantaID);
		} catch (WebException e) {
			modelo.addAttribute("error", e.getMessage());
		}

		return "redirect:/my-account/";
	}

	// Método para dar la baja a una planta
	@PostMapping("/planta-baja")
	public String bajaPlanta(Planta planta, Parcela parcela, Model modelo) {
		try {
			parcelaService.bajaPlanta(parcela, planta);
		} catch (WebException e) {
			modelo.addAttribute("error", e.getMessage());
		}

		return "redirect:/my-account/";
	}

}
