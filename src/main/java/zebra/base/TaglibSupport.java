package zebra.base;

import java.util.Locale;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.MessageSourceAccessor;

import zebra.data.ParamEntity;
import zebra.util.BeanHelper;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class TaglibSupport extends TagSupport {
	protected Logger logger = LogManager.getLogger(this.getClass());
	protected MessageSourceAccessor messageSourceAccessor = (MessageSourceAccessor)BeanHelper.getBean("messageSourceAccessor");

	/*!
	 * getMessage() - being called by sub classes
	 */
	protected String getMessage(String messageCode) {
		return messageSourceAccessor.getMessage(messageCode, new Locale.Builder().setLanguage(CommonUtil.lowerCase(ConfigUtil.getProperty("etc.default.language"))).build());
	}

	protected String getMessage(String messageCode, Locale locale) {
		return messageSourceAccessor.getMessage(messageCode, locale);
	}

	protected String getMessage(String messageCode, String languageCode) {
		return messageSourceAccessor.getMessage(messageCode, new Locale.Builder().setLanguage(languageCode).build());
	}

	protected String getMessage(String messageCode, ParamEntity paramEntity) {
		String lang = (String)paramEntity.getSession().getAttribute("langCode");
		return messageSourceAccessor.getMessage(messageCode, new Locale.Builder().setLanguage(CommonUtil.nvl(lang, CommonUtil.lowerCase(ConfigUtil.getProperty("etc.default.language")))).build());
	}

	/*!
	 * getter / setter
	 */
	public MessageSourceAccessor getMessageSourceAccessor() {
		return messageSourceAccessor;
	}

	public void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
		this.messageSourceAccessor = messageSourceAccessor;
	}
}