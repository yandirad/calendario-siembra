
package com.calendario_siembra.demo.repository;

import com.calendario_siembra.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * Si hya fallas, probar de reemplazar 'JpaRepository<Usuario, String>' 
 * por 'CrudRepository<Usuario, String>'
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    
    
    public Usuario findByUsername(String username);
        
    
}
