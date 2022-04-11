package util.validation;

import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.apache.commons.validator.routines.RegexValidator;

public class ValidationUtil {
	
	public static final boolean isMaxLength(String str, int length) {
		return str.length() <= length;
	}
	
	
	public static final boolean isMinLength(String str, int length) {
		return str.length() >= length;
	}
	
	
	public static final boolean isEmail(String email) {
		EmailValidator validator = EmailValidator.getInstance();
		
		return validator.isValid(email);
	}
	
	
	public static final boolean isPassword(String password) {
		RegexValidator regex = new RegexValidator("^[0-9a-zA-Z]{8,20}$");
		
		return regex.isValid(password);
	}
	
	
	public static final boolean isDate(String value) {
		DateValidator date = DateValidator.getInstance();
		
		return date.isValid(value, "yyyy-MM-dd");
	}
	
	
	public static final boolean isInteger(String value) {
		IntegerValidator integer = IntegerValidator.getInstance();
		
		return integer.isValid(value);
	}
	

}
