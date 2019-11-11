package br.com.sulamerica.desafio_sas.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.sulamerica.desafio_sas.entity.Usuario;
import br.com.sulamerica.desafio_sas.rowmapper.UsuarioRowMapper;

@Repository
public class UsuarioRepositoryJdbc {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	/**
	 * @author ander
	 * @param id
	 * @return
	 */
	public List<Usuario> findByFilter(Usuario usuario) {

		StringBuilder q = new StringBuilder();
		MapSqlParameterSource mapper = new MapSqlParameterSource();

		q.append("SELECT ")
		.append(" u.id")
		.append(" ,u.nome")
		.append(" ,u.cpf")
		.append(" ,u.sexo")
		.append(" ,u.data_nascimento")
		.append(" ,u.ativo")
		.append(" ,c.id AS id_cargo")
		.append(" ,c.nome AS nome_cargo")
		.append(" ,p.id AS id_perfil")
		.append(" ,p.nome AS nome_perfil")
		.append(" ,u.user_name")
		.append(" ,u.password")
		.append(" FROM usuario u")
		.append(" JOIN cargo c ON (c.id = u.fk_cargo)")
		.append(" JOIN perfil p ON (p.id = u.fk_perfil)")
		.append(" WHERE 1=1");

		if (usuario.getNome() != null) {
			q.append(" AND UPPER(u.nome) like UPPER(%:nome%)");
			mapper.addValue("nome", usuario.getNome());
		}

		if (usuario.getAtivo() != null) {
			q.append(" AND u.ativo = :ativo");
			mapper.addValue("ativo", usuario.getAtivo());
		}

		if (usuario.getDataNascimento() != null) {
			q.append(" AND u.data_nascimento = :dataNascimento");
			mapper.addValue("dataNascimento", usuario.getDataNascimento());
		}

		if (usuario.getCargo() != null) {
			q.append(" AND c.id = :cargo");
			mapper.addValue("cargo", usuario.getCargo().getId());
		}

		if (usuario.getPerfil() != null) {
			q.append(" AND p.id = :perfil");
			mapper.addValue("cargo", usuario.getPerfil().getId());
		}

		if (usuario.getSexo() != null) {
			q.append(" AND u.sexo = :sexo");
			mapper.addValue("cargo", usuario.getSexo());
		}

		if (usuario.getCpf() != null) {
			q.append(" AND u.cpf = :cpf");
			mapper.addValue("cpf", usuario.getCpf());
		}

		return jdbcTemplate.query(q.toString(), mapper, new UsuarioRowMapper());
	}
}