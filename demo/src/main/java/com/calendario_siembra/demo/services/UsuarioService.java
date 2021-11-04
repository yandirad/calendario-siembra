/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.calendario_siembra.demo.services;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.calendario_siembra.demo.entity.Usuario;
import com.calendario_siembra.demo.exceptions.WebException;
import com.calendario_siembra.demo.repository.ParcelaRepository;
import com.calendario_siembra.demo.repository.UsuarioRepository;

/**
 *
 * @author yandirad
 */
@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ParcelaRepository parcelaRepository;

	// asigna al usuario el rol de USUARIO sin permisos especiales
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = usuarioRepository.findByUsuario(username);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

		// use list if you wish
		grantedAuthorities.add(new SimpleGrantedAuthority(user.getRol()));
		return new org.springframework.security.core.userdetails.User(user.getUsuario(), user.getPass(),
				grantedAuthorities);
	}

	@Transactional
	public Usuario buscarUsuario() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String nickName = auth.getName();
		Usuario usuario = usuarioRepository.findByUsuario(nickName);
		usuario.setParcelas(parcelaRepository.obtenerListaParcelas(usuario));
		return usuario;
	}

	@Transactional
	public void guardarUsuario(Usuario usuario) throws WebException {

		validar(usuario);
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		usuario.setPass(bCryptPasswordEncoder.encode(usuario.getPass()));
		usuarioRepository.save(usuario);
	}

	public void validar(Usuario usuario) throws WebException {

		if (usuario.getNombre().isEmpty() || usuario.getNombre().equals("") || usuario.getNombre() == null) {
			throw new WebException("El nombre no puede estar vacío");
		}
		if (usuario.getMail().isEmpty() || usuario.getMail().equals("") || usuario.getMail() == null) {
			throw new WebException("El mail no puede estar vacío");
		}
		if (!validarMail(usuario.getMail())) {
			throw new WebException("El mail no es válido.");
		}
		if (usuario.getMail().equals(usuarioRepository.findByMail(usuario.getMail()))) {
			throw new WebException("El mail introducido ya fue registrado.");
		}
		if (usuario.getUsuario().isEmpty() || usuario.getUsuario().equals("") || usuario.getUsuario() == null) {
			throw new WebException("El usuario no puede estar vacío");
		} else if (usuario.getUsuario().equals(usuarioRepository.buscarUsuario(usuario.getUsuario()))) {
			throw new WebException("El usuario ya existe.");
		}
		if (usuario.getPass().isEmpty() || usuario.getPass().equals("") || usuario.getPass() == null
				|| usuario.getPass().length() < 8) {
			throw new WebException("La contraseña no puede estar vacía o tener menos de 8 dígitos");
		}

	}

	public Boolean validarMail(String mail) {
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		Matcher matcher = pattern.matcher(mail);
		return matcher.find();
	}
}
