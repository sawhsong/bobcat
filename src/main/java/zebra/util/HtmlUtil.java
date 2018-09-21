package zebra.util;

public class HtmlUtil {
	public static String stringToHtml(String text) {
		if (CommonUtil.isBlank(text)) {
			text = "";
		} else {
			text = text.trim();
//			text = CommonUtil.replace(text, "&", "&amp;");
//			text = CommonUtil.replace(text, "#", "&#35;");
//			text = CommonUtil.replace(text, "<", "&lt;");
//			text = CommonUtil.replace(text, ">", "&gt;");
//			text = CommonUtil.replace(text, "%", "&#37;");
			text = CommonUtil.replace(text, "\"", "&quot;");
			text = CommonUtil.replace(text, "'", "&#39;");
//			text = CommonUtil.replace(text, " ", "&nbsp;");
			text = CommonUtil.replace(text, "\r\n", "<br/>");
		}
		return text;
	}

	public static String htmlToString(String text) {
		if (CommonUtil.isBlank(text)) {
			text = "";
		} else {
//			text = CommonUtil.replace(text, "&lt;", "<");
//			text = CommonUtil.replace(text, "&gt;", ">");
//			text = CommonUtil.replace(text, "&amp;", "&");
//			text = CommonUtil.replace(text, "&#37;", (char)37 + "");
			text = CommonUtil.replace(text, "&quot;", (char)34 + "");
			text = CommonUtil.replace(text, "&#39;", (char)39 + "");
//			text = CommonUtil.replace(text, "&#35;", "#");
			text = CommonUtil.replace(text, "<br/>", "\n");
//			text = CommonUtil.replace(text, "&nbsp;", " ");
		}
		return text;
	}

	public static String special2htm(String text, String sSpecial) {
		String sRtn = "";
		if (CommonUtil.isBlank(text)) {
			sRtn = "";
		} else {
			text = text.trim();
			if (sSpecial.equals("'")) {sRtn = CommonUtil.replace(text, sSpecial, "&#39;");}
			if (sSpecial.equals("\"")) {sRtn = CommonUtil.replace(text, sSpecial, "&quot;");}
			if (sSpecial.equals("\n")) {sRtn = CommonUtil.replace(text, sSpecial, "<br/>");}
		}
		return sRtn;
	}

	public static String htmlToHexForXml(String text) {
		if (CommonUtil.isBlank(text)) {
			text = "";
		} else {
			text = CommonUtil.replace(text, "&nbsp;", "&#160;");
		}
		return text;
	}

	public static String stringToHtmlForJson(String text) {
		if (CommonUtil.isBlank(text)) {
			text = "";
		} else {
			text = CommonUtil.replace(text, "\r", "");
			text = CommonUtil.replace(text, "\n", "<br/>");
		}
		return text;
	}
}