package zebra.taglib;

import javax.servlet.jsp.JspWriter;

import zebra.base.TaglibBodySupport;
import zebra.util.CommonUtil;

public class Tab extends TaglibBodySupport {
	private String id;
	private String style;

	public int doAfterBody() {
		try {
			bodyContent = getBodyContent();
			JspWriter jspWriter = bodyContent.getEnclosingWriter();
			StringBuffer html = new StringBuffer();

			html.append("<div id=\""+getId()+"\" class=\"tabHolder\"");
			html.append((CommonUtil.isNotBlank(getStyle())) ? "style=\""+getStyle()+"\"" : "").append(">");
			html.append("<ul id=\"ul"+getId()+"\" class=\"nav nav-tabs tabListHolder\">");
			html.append(bodyContent.getString());
			html.append("</ul>").append("</div>");

			jspWriter.print(html.toString());
		} catch (Exception ex) {
			logger.error(ex);
		}

		return SKIP_BODY;
	}
	/*!
	 * getter / setter
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
}