package model.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	// Map é a coleção de chaves e valores, o primeiro valor é a chave(nome do campo), o segundo é o valor(mensagem de erro)
	private Map<String, String> errors = new HashMap<>();
	
	public ValidationException(String msg) {
		super(msg);
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}
	
	// Irá carregar todos os erros possíveis
	public void addError(String fieldName, String errorMesssage) {
		errors.put(fieldName, errorMesssage);
	}
}
