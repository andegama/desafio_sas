package br.com.sulamerica.desafio_sas.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sulamerica.desafio_sas.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Query("SELECT u FROM Usuario u JOIN FETCH u.perfil p JOIN FETCH u.cargo c")
	public List<Usuario> listAllFetch();

	@Query("SELECT u FROM Usuario u JOIN FETCH u.perfil p JOIN FETCH u.cargo c WHERE u.id = :id")
	public Usuario findByIdFetch(@Param(value = "id")Long id);

	@Query("SELECT u FROM Usuario u JOIN FETCH u.perfil p JOIN FETCH u.cargo c WHERE u.sexo = 'F' AND u.dataNascimento <= :dataNascimento")
	public List<Usuario> findFeminoMaiorIdade(Date dataNascimento);

	@Query("SELECT u FROM Usuario u JOIN FETCH u.perfil p JOIN FETCH u.cargo c WHERE u.cpf LIKE '0%'")
	public List<Usuario> findCpfStartsWithZero();

	public Usuario findByCpfAndNome(String cpf, String nome);

	public Usuario findByNome(String nome);

	public Usuario findByCpf(String cpf);

	@Query("SELECT u.ativo FROM Usuario u WHERE u.userName = :userName")
	public Boolean isActive(@Param(value = "userName")String userName);
}