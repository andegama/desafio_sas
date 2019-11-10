package br.com.sulamerica.desafio_sas.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.sulamerica.desafio_sas.converter.DateToStringConverter;
import br.com.sulamerica.desafio_sas.converter.StringToDateConverter;
import br.com.sulamerica.desafio_sas.entity.Cargo;
import br.com.sulamerica.desafio_sas.entity.Perfil;
import br.com.sulamerica.desafio_sas.entity.Usuario;

public class UsuarioDTO {

	private Long id;
	private String nome;
	private String userName;
	private String cpf;
	private String sexo;
	private Perfil perfil;
	private Cargo cargo;
	private Boolean ativo;
	private String password;

	@JsonSerialize(converter = DateToStringConverter.class)
	@JsonDeserialize(using = StringToDateConverter.class)
	private Date dataNascimento;

	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.userName = usuario.getUsername();
		this.cpf = usuario.getCpf();
		this.sexo = usuario.getSexo();
		this.perfil = usuario.getPerfil();
		this.cargo = usuario.getCargo();
		this.dataNascimento = usuario.getDataNascimento();
		this.ativo = usuario.getAtivo();
	}

	public UsuarioDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date data) {
		this.dataNascimento = data;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Usuario toEntity() {

		Usuario usuario = new Usuario();

		usuario.setId(this.id);
		usuario.setNome(this.nome);
		usuario.setSexo(this.sexo);
		usuario.setCpf(this.cpf);
		usuario.setUserName(this.userName);
		usuario.setPassword(this.password);
		usuario.setPerfil(this.perfil);
		usuario.setCargo(this.cargo);
		usuario.setAtivo(this.ativo);

//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

//		try {
			usuario.setDataNascimento(this.dataNascimento);
//		} catch (ParseException e) {
//			usuario.setDataNascimento(null);
//		}

		return usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioDTO other = (UsuarioDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}