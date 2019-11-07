package br.com.sulamerica.desafio_sas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.sulamerica.desafio_sas.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
