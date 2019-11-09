package br.com.sulamerica.desafio_sas.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import br.com.sulamerica.desafio_sas.entity.Cargo;
import br.com.sulamerica.desafio_sas.rowmapper.CargoRowMapper;

@Repository
public class CargoRepositoryImpl {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public Cargo save(Cargo cargo) {

		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getJdbcTemplate())
				.withTableName("cargo").usingGeneratedKeyColumns("id");

		StringBuilder q = new StringBuilder();
		q.append("INSERT INTO cargo (id,nome) VALUES (SQ_CARGO.NEXTVAL, :nome)");

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("nome", cargo.getNome());

		Number result = simpleJdbcInsert.executeAndReturnKey(paramMap);
//		int result = jdbcTemplate.update(q.toString(), paramMap);

		return jdbcTemplate.queryForObject("SELECT * FROM cargo WHERE id = :id"
				,new MapSqlParameterSource().addValue("id", result)
				,new CargoRowMapper());
	}
}