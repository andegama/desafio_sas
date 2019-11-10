package br.com.sulamerica.desafio_sas.exceptions;

public class InactiveUserException extends RuntimeException {

	private static final long serialVersionUID = 1571762660969926228L;

	public InactiveUserException() {
	}

	public InactiveUserException(final String msg) {
		super(msg);
	}

	public InactiveUserException(final Throwable erro) {
		super(erro);
	}

	public InactiveUserException(final String msg, final Throwable erro) {
		super(msg, erro);
	}
}