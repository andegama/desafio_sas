package br.com.sulamerica.desafio_sas.exceptions;

public class InvalidJwtAuthenticationException extends RuntimeException{

	private static final long serialVersionUID = -2354693191500265174L;

	public InvalidJwtAuthenticationException() {
	}

	public InvalidJwtAuthenticationException(final String msg) {
		super(msg);
	}

	public InvalidJwtAuthenticationException(final Throwable erro) {
		super(erro);
	}

	public InvalidJwtAuthenticationException(final String msg, final Throwable erro) {
		super(msg, erro);
	}
}