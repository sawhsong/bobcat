package zebra.taglib;

import javax.servlet.jsp.JspWriter;

import zebra.base.TaglibSupport;
import zebra.util.CommonUtil;

public class SelectboxOption extends TaglibSupport {
	private String value;
	private String text;
	private String isSelected;
	private String isDisabled;

	public int doStartTag() {
		try {
			JspWriter jspWriter = pageContext.getOut();
			StringBuffer html = new StringBuffer();

			html.append("<option value=\""+value+"\"");

			if (CommonUtil.equalsIgnoreCase(isSelected, "true") || CommonUtil.equalsIgnoreCase(isSelected, "yes")) {html.append(" selected");}
			if (CommonUtil.equalsIgnoreCase(isDisabled, "true") || CommonUtil.equalsIgnoreCase(isDisabled, "yes")) {html.append(" disabled");}

			html.append(">"+text+"");
			html.append("</option>\n");

			jspWriter.print(html.toString());
		} catch (Exception ex) {
			logger.error(ex);
		}

		return SKIP_BODY;
	}
	/*!
	 * getter / setter
	 */
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}

	public String getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(String isDisabled) {
		this.isDisabled = isDisabled;
	}
}