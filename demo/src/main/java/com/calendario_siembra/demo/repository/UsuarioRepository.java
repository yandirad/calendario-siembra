
package com.calendario_siembra.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calendario_siembra.demo.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * Si hya fallas, probar de reemplazar 'JpaRepository<Usuario, String>' por
 * 'CrudRepository<Usuario, String>'
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

	public Usuario findByUsuario(String username);

	public String findByMail(String mail);
        
        
        @Query("SELECT u FROM Usuario u WHERE u.usuario = :username")
        public String buscarUsuario(@Param("username") String username);


}
