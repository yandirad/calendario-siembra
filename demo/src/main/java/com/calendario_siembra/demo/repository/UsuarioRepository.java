
package com.calendario_siembra.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calendario_siembra.demo.entity.Usuario;

/**
 *
 * Si hya fallas, probar de reemplazar 'JpaRepository<Usuario, String>' por
 * 'CrudRepository<Usuario, String>'
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

	public Usuario findByUsuario(String username);

}
