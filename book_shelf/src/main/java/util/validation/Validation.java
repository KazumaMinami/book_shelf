package util.validation;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class Validation {
	
	protected HttpServletRequest request;
	
	protected Map<String, String> errors;
	
	
	public Validation(HttpServletRequest request) {
		this.request = request;
		this.errors = new HashMap<String, String>();
	}
	
	
	public boolean hasErrors() {
		if(this.errors.size() > 0) {
			return true;
		}
		return false;
	}

}
