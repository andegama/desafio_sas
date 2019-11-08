package br.com.sulamerica.desafio_sas.exceptions;

public class NegocioException extends Exception{

	private static final long serialVersionUID = 8531271408485925155L;

	public NegocioException() {
	}

	public NegocioException(final String msg) {
		super(msg);
	}

	public NegocioException(final Throwable erro) {
		super(erro);
	}

	public NegocioException(final String msg, final Throwable erro) {
		super(msg, erro);
	}
}