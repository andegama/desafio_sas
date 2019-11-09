package br.com.sulamerica.desafio_sas.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import br.com.sulamerica.desafio_sas.entity.Cargo;

public class CargoRowMapper implements RowMapper<Cargo>{

	private Logger logger = LogManager.getLogger(CargoRowMapper.class);

	@Override
	public Cargo mapRow(ResultSet rs, int rowNum) throws SQLException {

		logger.info("[CARGO-ROW-MAPPER] - Iniciando processo de extração de dados de um cargo.");

		Long id = Long.valueOf(rs.getString("id"));
		logger.trace("[CARGO-ROW-MAPPER] - ID Recebido: " + id);

		String nome = rs.getString("nome");
		logger.trace("[CARGO-ROW-MAPPER] - NOME Recebido: " + nome);

		return new Cargo(id, nome);
	}
}