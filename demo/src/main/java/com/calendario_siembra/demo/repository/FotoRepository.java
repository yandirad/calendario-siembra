
package com.calendario_siembra.demo.repository;

import com.calendario_siembra.demo.entity.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepository extends JpaRepository<Foto, String>{


}
