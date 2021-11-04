package com.calendario_siembra.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.calendario_siembra.demo.entity.Usuario;
import com.calendario_siembra.demo.exceptions.WebException;
import com.calendario_siembra.demo.services.UsuarioService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuario")
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
	public String usuarioCreado(Usuario usuario, RedirectAttributes ra) {
		try {                 
                    usuarioService.guardarUsuario(usuario);
                    ra.addFlashAttribute("exitoso", "Se registro correctamente");
                    
		} catch (WebException e) {
                    ra.addFlashAttribute("error", e.getMessage());
		}
		return "redirect:/usuario/registrar";
	}

}
