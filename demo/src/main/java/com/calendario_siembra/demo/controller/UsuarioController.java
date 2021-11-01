package com.calendario_siembra.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.calendario_siembra.demo.entity.Usuario;
import com.calendario_siembra.demo.exceptions.WebException;
import com.calendario_siembra.demo.services.UsuarioService;

@Controller
@RequestMapping("/registro-usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@GetMapping("/registrar")
	public String registrar(Model modelo) {
		Usuario usuario = new Usuario();
		modelo.addAttribute("usuario", usuario);
		return "register.html";
	}

	@PostMapping("/registrar")
	public String usuarioCreado(@RequestParam Usuario usuario, Model modelo) {
		try {
			usuarioService.guardarUsuario(usuario);
			modelo.addAttribute("exitoso", "Se registro correctamente");
		} catch (WebException e) {
			modelo.addAttribute("error", e.getMessage());
		}
		return "redirect:/registro-usuario/register.html";
	}

}
