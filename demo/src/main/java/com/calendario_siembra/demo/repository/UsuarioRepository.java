
package com.calendario_siembra.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

	@Query("SELECT usuario.mail FROM Usuario WHERE usuario.mail = :mail")
	public String findByMail(@Param("mail") String mail);

	@Query("SELECT usuario.usuario FROM Usuario WHERE usuario.usuario = :username")
	public String buscarUsuario(@Param("username") String username);

}
