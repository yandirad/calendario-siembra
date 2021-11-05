package com.calendario_siembra.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.calendario_siembra.demo.services.PlantaService;
import com.calendario_siembra.demo.services.UsuarioService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	PlantaService plantaService;
        
        @Autowired
	UsuarioService usuarioService;

	@GetMapping({ "/", "/index" })
	public String index(Model modelo) {
		modelo.addAttribute("listaPlantas", plantaService.listarPlantas());
		return "index.html";
	}

	@GetMapping("/login")
	public String login(Model model, @RequestParam(required=false) String error, 
                @RequestParam(required=false) String usuario) {
            if(error != null){
                model.addAttribute("error", "El usuario ingresado o la contraseña no son válidos.");
            }
            if(usuario != null){
                model.addAttribute("usuario", usuario);
            }
            return "login.html";
	}
        
        @GetMapping("/logout")
	public String logout(Model model) {
            model.addAttribute("success", "Ha finalizado su sesión exitosamente.");
   
            return "login";
	}
}
