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
import org.springframework.web.bind.annotation.RequestMapping;

import com.calendario_siembra.demo.services.UsuarioService;

@Controller
@RequestMapping("/my-account")
public class MyAccountController {

	@Autowired
	UsuarioService usuarioService;

	@GetMapping("/")
	public String verDatos(Model modelo) {
		modelo.addAttribute("usuario", usuarioService.buscarUsuario());

		return "myaccount.html";
	}

}
