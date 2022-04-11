package validation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import settings.MessageSettings;
import util.validation.Validation;
import util.validation.ValidationUtil;

public class UserValidation extends Validation{
	
	public UserValidation(HttpServletRequest request) {
		super(request);
	}
	
	
	public Map<String, String> validate(){
		if(!ValidationUtil.isEmail(this.request.getParameter("email"))) {
			this.errors.put("email", MessageSettings.MSG_EMAIL_FAILURE);
		}
		
		if(!ValidationUtil.isPassword(this.request.getParameter("password"))) {
			this.errors.put("password", MessageSettings.MSG_PASSWORD_FAILURE);
		}
		
		if(!ValidationUtil.isMaxLength(this.request.getParameter("name"), 50)) {
			this.errors.put("name", String.format(MessageSettings.MSG_LENGTH_LONG, "ニックネーム", 50));
		}
		
		if(!ValidationUtil.isMinLength(this.request.getParameter("name"), 1)) {
			this.errors.put("name", String.format(MessageSettings.MSG_REQUIRED, "ニックネーム"));
		}
		
		return errors;
	}
	

}
