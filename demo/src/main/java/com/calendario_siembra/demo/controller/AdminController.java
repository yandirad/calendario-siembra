/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calendario_siembra.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.calendario_siembra.demo.entity.Planta;
import com.calendario_siembra.demo.entity.Usuario;
import com.calendario_siembra.demo.exceptions.WebException;
import com.calendario_siembra.demo.services.ParcelaService;
import com.calendario_siembra.demo.services.PlantaService;
import com.calendario_siembra.demo.services.UsuarioService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	ParcelaService parcelaService;

	@Autowired
	PlantaService plantaService;

	// metodo para mostrar tablas
	@GetMapping("/")
	public String verTablas(Model modelo, @RequestParam(required = false) String error) {
		Usuario usuario = usuarioService.buscarUsuario();
		if (usuario.getRol().equals("ADMIN")) {
			List<Usuario> usuarios = usuarioService.listarUsuarios();
			List<Planta> plantas = plantaService.listarPlantas();
			modelo.addAttribute("usuarios", usuarios);
			modelo.addAttribute("plantas", plantas);
			modelo.addAttribute("error", error);
			if (modelo.getAttribute("nav") == null) {
				modelo.addAttribute("nav", false);
			}
			return "admin.html";
		} else {
			return "redirect:/my-account/";
		}
	}

	// ruta para crear/modificar planta
	@GetMapping("/registrar-planta")
	public String registrar(Model modelo) {
		Planta planta = new Planta();
		modelo.addAttribute("planta", planta);
		return "form-planta.html"; // nombre HTML
	}

	@PostMapping("/registrar-planta")
	public String plantaGuardar(RedirectAttributes ra, @RequestParam(required = false) String id, String nombre,
			String tipoCultivo, String profundidadSiembra, Integer horasSol, String cantidadRiego, String cosecha,
			String heladas, String diasCosecha, String mesSiembra, String descripcion, MultipartFile archivo) {

		try {
			if ("".equals(id)) {
				plantaService.registrar(nombre, tipoCultivo, profundidadSiembra, horasSol, cantidadRiego, cosecha,
						heladas, diasCosecha, mesSiembra, descripcion, archivo);
				ra.addFlashAttribute("exitoso", "Se cargó nueva planta correctamente!");
			} else {
				plantaService.modificar(id, nombre, tipoCultivo, profundidadSiembra, horasSol, cantidadRiego, cosecha,
						heladas, diasCosecha, mesSiembra, descripcion, archivo);
				ra.addFlashAttribute("exitoso", "Se cargó la modificación correctamente!");
			}
			return "redirect:/admin/";// agregar ruta (no el nombre del html) para caso exitoso
		} catch (WebException e) {
			ra.addFlashAttribute("error", e.getMessage());
		}
		return "redirect:/admin/";// agregar ruta (no el nombre del html) para caso NO exitoso
	}

	// ruta para eliminar planta (baja)
	@PostMapping("/baja-planta")
	public String bajaPlanta(String id) throws Exception {
		plantaService.bajaPlanta(id);
		return "redirect:/admin/";// agregar ruta listado plantas
	}

	// ruta para eliminar usuario
	@PostMapping("/baja-usuario")
	public String cambiarEstadoUsuario(String id, RedirectAttributes ra) throws Exception {
		usuarioService.cambiarEstadoUsuario(id);
		ra.addFlashAttribute("nav", true);
		return "redirect:/admin/";// agregar ruta listado usuarios
	}

	// Metodo para cargar una foto de planta
	@GetMapping("/foto/{id}")
	public ResponseEntity<byte[]> fotoPlanta(@PathVariable String id) throws WebException {
		Optional<Planta> planta = plantaService.buscarPlantaID(id);

		if (planta.isPresent()) {
			if (planta.get().getFoto() == null) {
				throw new WebException("La planta no posee foto");
			}
			byte[] foto = planta.get().getFoto().getContenido();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);

			return new ResponseEntity<>(foto, headers, HttpStatus.OK);

		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	// ruta para cambiar el rol
	@PostMapping("/rol-cambiar")
	public String cambiarRolUsuario(String id, String rol, RedirectAttributes ra) throws Exception {
		usuarioService.cambiarRolUsuario(id, rol);
		ra.addFlashAttribute("nav", true);
		return "redirect:/admin/";// agregar ruta listado usuarios
	}
}