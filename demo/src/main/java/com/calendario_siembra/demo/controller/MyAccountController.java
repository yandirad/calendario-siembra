/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.calendario_siembra.demo.controller;

import com.calendario_siembra.demo.entity.Parcela;
import com.calendario_siembra.demo.entity.Planta;
import com.calendario_siembra.demo.entity.Usuario;
import com.calendario_siembra.demo.exceptions.WebException;
import com.calendario_siembra.demo.services.ParcelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.calendario_siembra.demo.services.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/my-account")
public class MyAccountController {

	@Autowired
	UsuarioService usuarioService;
        
        @Autowired
        ParcelaService parcelaService;

        //vista inicial del perfil de cada usuario
	@GetMapping("/")
	public String verDatos(Model modelo) {
		modelo.addAttribute("usuario", usuarioService.buscarUsuario());

		return "myaccount.html";
	}
        
        //Metodo para modificar datos del usuario, mediante pop-up
        @PostMapping("/usuario-modificar")
        public String modificarUsuario(@RequestParam Usuario usuario, Model modelo) {
            try {
                usuarioService.guardarUsuario(usuario);

            } catch (WebException e) {
                modelo.addAttribute("error", e.getMessage());
            }
            
            return "redirect:/myaccount.html/"; 
        }
        
        //Método para crear/modificar una nueva parcela
        @PostMapping("/parcela-alta-modificar")
        public String crearParcela(@RequestParam(required=false) Parcela parcela, Model modelo) {
            try {
                if (parcela.getId() == null) {
                    parcelaService.crearParcela(parcela);
                } else {
                    parcelaService.modificarParcela(parcela);
                }
            } catch (WebException e) {
                modelo.addAttribute("error", e.getMessage());
            }
   
            return "redirect:/myaccount.html/";
        }
        
        //Método para dar la baja a una parcela
        @PostMapping("/parcela-baja")
        public String bajaParcela(@RequestParam Parcela parcela, Model modelo) {
            try {
                parcelaService.bajaParcela(parcela);   
            } catch (WebException e) {
                modelo.addAttribute("error", e.getMessage());
            }
   
            return "redirect:/myaccount.html/";
        }
        
      
      //Método para dar la alta a una planta
        @PostMapping("/planta-alta")
        public String altaPlanta(@RequestParam Planta planta,@RequestParam Parcela parcela, Model modelo) {
            try {
                parcelaService.agregarPlanta(parcela, planta);  
            } catch (WebException e) {
                modelo.addAttribute("error", e.getMessage());
            }
   
            return "redirect:/myaccount.html/";
        }
        
        //Método para dar la baja a una planta
        @PostMapping("/planta-baja")
        public String bajaPlanta(@RequestParam Planta planta,@RequestParam Parcela parcela, Model modelo) {
            try {
                parcelaService.bajaPlanta(parcela,planta);   
            } catch (WebException e) {
                modelo.addAttribute("error", e.getMessage());
            }
   
            return "redirect:/myaccount.html/";
        }
        
        
}
