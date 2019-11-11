package br.com.sulamerica.desafio_sas.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import br.com.sulamerica.desafio_sas.entity.Cargo;
import br.com.sulamerica.desafio_sas.entity.Perfil;
import br.com.sulamerica.desafio_sas.entity.Usuario;

public class UsuarioRowMapper implements RowMapper<Usuario>{

	private Logger logger = LogManager.getLogger(UsuarioRowMapper.class);

	@Override
	public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {

		logger.info("[CARGO-ROW-MAPPER] - Iniciando processo de extração de dados de um cargo.");

		Long id = Long.valueOf(rs.getString("id"));
		logger.trace("[CARGO-ROW-MAPPER] - ID Recebido: " + id);

		String nome = rs.getString("nome");
		logger.trace("[CARGO-ROW-MAPPER] - NOME Recebido: " + nome);

		String cpf = rs.getString("cpf");
		logger.trace("[CARGO-ROW-MAPPER] - CPF Recebido: " + cpf);

		String sexo = rs.getString("sexo");
		logger.trace("[CARGO-ROW-MAPPER] - SEXO Recebido: " + sexo);

		Date dataNascimento = rs.getDate("data_nascimento");
		logger.trace("[CARGO-ROW-MAPPER] - DATA NASCIMENTO Recebida: " + dataNascimento);

		boolean ativo = rs.getBoolean("ativo");
		logger.trace("[CARGO-ROW-MAPPER] - ATIVO Recebido: " + ativo);

		Long idCargo = Long.valueOf(rs.getString("id_cargo"));
		logger.trace("[CARGO-ROW-MAPPER] - ID CARGO Recebido: " + idCargo);

		String nomeCargo = rs.getString("nome_cargo");
		logger.trace("[CARGO-ROW-MAPPER] - NOME CARGO Recebido: " + nomeCargo);

		Long idPerfil = Long.valueOf(rs.getString("id_perfil"));
		logger.trace("[CARGO-ROW-MAPPER] - ID PERFIL Recebido: " + idPerfil);

		String nomePerfil = rs.getString("nome_perfil");
		logger.trace("[CARGO-ROW-MAPPER] - NOME PERFIL Recebido: " + nomePerfil);

		String userName = rs.getString("user_name");
		logger.trace("[CARGO-ROW-MAPPER] - NOME PERFIL Recebido: " + userName);

		String password = rs.getString("password");
		logger.trace("[CARGO-ROW-MAPPER] - NOME PERFIL Recebido: " + password);

		logger.info("[CARGO-ROW-MAPPER] - Finalizado processo de extração de dados de um cargo.");
		return new Usuario(id, nome, cpf, sexo, dataNascimento, ativo, new Cargo(idCargo, nomeCargo), new Perfil(idPerfil, nomePerfil), userName, password);
	}
}