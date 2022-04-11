package validation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import settings.MessageSettings;
import util.validation.Validation;
import util.validation.ValidationUtil;

public class QuoteUpdateValidation extends Validation{
	
	public QuoteUpdateValidation(HttpServletRequest request) {
		// TODO 自動生成されたコンストラクター・スタブ
		super(request);
	}
	
	
	public Map<String, String> validate(){
		
		
		//引用
		if(!ValidationUtil.isMinLength(this.request.getParameter("quote"), 1)) {
			this.errors.put("quote", String.format(MessageSettings.MSG_REQUIRED, "引用"));
		}
		
		//pp
		if(!ValidationUtil.isInteger(this.request.getParameter("page"))) {
			this.errors.put("pp", String.format(MessageSettings.MSG_INVALID_VALUE, "ページ番号"));
		}
		
		return errors;
	}
	

}
