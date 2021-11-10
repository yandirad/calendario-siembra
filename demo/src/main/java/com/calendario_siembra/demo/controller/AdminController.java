/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.calendario_siembra.demo.controller;

import com.calendario_siembra.demo.entity.Planta;
import com.calendario_siembra.demo.entity.Usuario;
import com.calendario_siembra.demo.exceptions.WebException;
import com.calendario_siembra.demo.services.ParcelaService;
import com.calendario_siembra.demo.services.PlantaService;
import com.calendario_siembra.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    	@Autowired
	UsuarioService usuarioService;

	@Autowired
	ParcelaService parcelaService;

	@Autowired
	PlantaService plantaService;
        
        //ruta para crear/modificar planta
        @GetMapping("/registrar-planta")
	public String registrar(Model modelo) {
            Planta planta = new Planta();
            modelo.addAttribute("planta", planta);
            return "xx"; //nombre HTML
	}

	@PostMapping("/registrar-planta")
	public String plantaGuardar(RedirectAttributes ra, @RequestParam(required=false) String id, String nombre, String tipoCultivo, String profundidadSiembra, 
                Integer horasSol, String cantidadRiego, String cosecha, String heladas, String diasCosecha, 
                String mesSiembra, String descripcion, MultipartFile archivo) {
            
            try {
                if(id == null){
                    plantaService.registrar(nombre, tipoCultivo, profundidadSiembra, horasSol, cantidadRiego, cosecha, 
                        heladas, diasCosecha, mesSiembra, descripcion, archivo);
                    ra.addFlashAttribute("exitoso", "Se cargó nueva planta correctamente!");
                } else{
                    plantaService.modificar(heladas, nombre, tipoCultivo, profundidadSiembra, horasSol, 
                            cantidadRiego, cosecha, heladas, diasCosecha, mesSiembra, descripcion, archivo);
                    ra.addFlashAttribute("exitoso", "Se cargó la modificación correctamente!");
                }
		return "redirect:/xx";//agregar ruta para caso exitoso
		} catch (WebException e) {
			ra.addFlashAttribute("error", e.getMessage());
		}
		return "redirect:/xx";//agregar ruta para caso NO exitoso
	}
        
        //ruta para eliminar planta (baja)
        @GetMapping("/baja-planta/{id}")
        public RedirectView bajaPlanta(Planta planta) throws Exception{        
        plantaService.bajaPlanta(planta);
        return new RedirectView("/xx");//agregar ruta listado plantas
        }
        
        //ruta para eliminar usuario
        @GetMapping("/baja-usuario/{id}")
        public RedirectView bajaUsuario(Usuario usuario) throws Exception{        
        usuarioService.bajaUsuario(usuario);
        return new RedirectView("/xx");//agregar ruta listado usuarios
        }

}
