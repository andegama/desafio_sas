package br.com.sulamerica.desafio_sas.util;

import java.util.List;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;

public class Util {

	public static boolean isCpfValido(String cpf){ 
	    CPFValidator cpfValidator = new CPFValidator(); 
	    List<ValidationMessage> erros = cpfValidator.invalidMessagesFor(cpf);

	    if(erros.size() > 0){
	    	return false;
	    }
	    return true;
	}
}