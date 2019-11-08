package br.com.sulamerica.desafio_sas.response;

/**
 * Objeto utilizado para padronizar as respostas REST
 * 
 * @author ander
 */
public class ObjectResponse {

	private String message;
	private Object response;
	private Boolean temErro = Boolean.FALSE;

	public ObjectResponse() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public boolean isTemErro() {
		return temErro;
	}

	public void setTemErro(boolean temErro) {
		this.temErro = temErro;
	}
}