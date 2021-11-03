package com.calendario_siembra.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.calendario_siembra.demo.services.PlantaService;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	PlantaService plantaService;

	@GetMapping({ "/", "/index" })
	public String index(Model modelo) {
		modelo.addAttribute("listaPlantas", plantaService.listarPlantas());
		return "index.html";
	}

	@GetMapping("/login")
	public String login() {
		return "login.html";
	}

}
