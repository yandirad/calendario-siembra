/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.calendario_siembra.demo.controller;

import com.calendario_siembra.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my-account")
public class MyAccountController {

    @Autowired
    UsuarioService usuarioService;
    
    @GetMapping("/")
    public String verDatos(Model modelo){
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nombre = auth.getName();
        System.out.println(nombre);
        
        return "myaccount.html";
    }
    
}
