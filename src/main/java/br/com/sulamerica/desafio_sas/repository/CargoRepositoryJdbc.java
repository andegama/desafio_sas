package br.com.sulamerica.desafio_sas.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import br.com.sulamerica.desafio_sas.entity.Cargo;
import br.com.sulamerica.desafio_sas.rowmapper.CargoRowMapper;

@Repository
public class CargoRepositoryJdbc {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	/**
	 * @author ander
	 * @param cargo
	 * @return
	 */
	public Cargo save(Cargo cargo) {

		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getJdbcTemplate())
				.withTableName("cargo").usingGeneratedKeyColumns("id");

		StringBuilder q = new StringBuilder();
		q.append("INSERT INTO cargo (id,nome) VALUES (SQ_CARGO.NEXTVAL, :nome)");

		Number result = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource().addValue("nome", cargo.getNome()));

		return this.findById(result.longValue());
	}

	/**
	 * @author ander
	 * @param id
	 * @return
	 */
	public Cargo findById(Long id) {

		StringBuilder q = new StringBuilder();

		q.append("SELECT *")
		.append(" FROM cargo")
		.append(" WHERE id = :id");

		return jdbcTemplate.queryForObject(q.toString(), new MapSqlParameterSource().addValue("id", id), new CargoRowMapper());
	}
}