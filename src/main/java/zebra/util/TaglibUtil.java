package zebra.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import zebra.config.MessageManager;
import zebra.data.DataSet;

public class TaglibUtil extends CommonUtil {
	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(TaglibUtil.class);

	public static String getMccpValue(String value) {
		String str = "", key = "", confPropValue = "";

		if (CommonUtil.startsWith(value, "<mc:cp key=")) {
			key = CommonUtil.substringAfter(value, "=");
			key = CommonUtil.substringBefore(key, "/>");

			confPropValue = ConfigUtil.getProperty(key);
			str = CommonUtil.replacePattern(value, "<(.*)>", confPropValue);
		} else {
			str = value;
		}

		return str;
	}

	public static String getMcmsgValue(String value, String langCode) throws Exception {
		String str = "", key = "", msgValue = "";
		DataSet ds = MessageManager.getMessageDataSet(langCode);

		if (CommonUtil.startsWith(value, "<mc:msg key=")) {
			key = CommonUtil.substringAfter(value, "=");
			key = CommonUtil.substringBefore(key, "/>");

			msgValue = ds.getValue(key);
			str = CommonUtil.replacePattern(value, "<(.*)>", msgValue);
		} else {
			str = value;
		}

		return str;
	}
}