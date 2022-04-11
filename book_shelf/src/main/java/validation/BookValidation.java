package validation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import settings.MessageSettings;
import util.validation.Validation;
import util.validation.ValidationUtil;

public class BookValidation extends Validation{

	public BookValidation(HttpServletRequest request) {
		// TODO 自動生成されたコンストラクター・スタブ
		super(request);
	}
	
	
	public Map<String, String> validate(){
		//登録日
		if(!ValidationUtil.isDate(this.request.getParameter("registrationDate"))) {
			this.errors.put("registrationDate", String.format(MessageSettings.MSG_INVALID_VALUE, "登録日"));
		}
		
		//タイトル
		if(!ValidationUtil.isMinLength(this.request.getParameter("bookTitle"), 1)) {
			this.errors.put("title", String.format(MessageSettings.MSG_REQUIRED, "タイトル"));
		}
		if(!ValidationUtil.isMaxLength(this.request.getParameter("bookTitle"), 50)) {
			this.errors.put("title", String.format(MessageSettings.MSG_LENGTH_LONG, "タイトル", 50));
		}
		
		//著者
		if(!ValidationUtil.isMinLength(this.request.getParameter("author"), 1)) {
			this.errors.put("author", String.format(MessageSettings.MSG_REQUIRED, "著者"));
		}
		if(!ValidationUtil.isMaxLength(this.request.getParameter("author"), 50)) {
			this.errors.put("author", String.format(MessageSettings.MSG_LENGTH_LONG, "著者", 50));
		}
		
		return errors;
	}
	

}
