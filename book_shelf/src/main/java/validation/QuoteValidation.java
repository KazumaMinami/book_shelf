package validation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import settings.MessageSettings;
import util.validation.Validation;
import util.validation.ValidationUtil;

public class QuoteValidation extends Validation{

	public QuoteValidation(HttpServletRequest request) {
		// TODO 自動生成されたコンストラクター・スタブ
		super(request);
	}
	
	
	public Map<String, String> validate(){
		
		//タイトル
		if(!ValidationUtil.isMinLength(this.request.getParameter("title"), 1)) {
			this.errors.put("title", String.format(MessageSettings.MSG_REQUIRED, "タイトル"));
		}
		if(!ValidationUtil.isMaxLength(this.request.getParameter("title"), 50)) {
			this.errors.put("title", String.format(MessageSettings.MSG_LENGTH_LONG, "タイトル", 50));
		}
		
		//著者
		if(!ValidationUtil.isMinLength(this.request.getParameter("author"), 1)) {
			this.errors.put("author", String.format(MessageSettings.MSG_REQUIRED, "著者"));
		}
		if(!ValidationUtil.isMaxLength(this.request.getParameter("author"), 50)) {
			this.errors.put("author", String.format(MessageSettings.MSG_LENGTH_LONG, "著者", 50));
		}
		
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
