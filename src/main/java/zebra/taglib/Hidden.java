package zebra.taglib;

import javax.servlet.jsp.JspWriter;

import zebra.base.TaglibSupport;
import zebra.util.CommonUtil;

public class Hidden extends TaglibSupport {
	private String id;
	private String name;
	private String className;
	private String value;
	private String style;

	public int doStartTag() {
		try {
			JspWriter jspWriter = pageContext.getOut();
			StringBuffer html = new StringBuffer();

			html.append("<input type=\"hidden\" id=\""+id+"\" name=\""+name+"\"");

			if (CommonUtil.isNotBlank(className)) {html.append(" class=\""+className+"\"");}
			if (CommonUtil.isNotBlank(value)) {html.append(" value=\""+value+"\"");}
			if (CommonUtil.isNotBlank(style)) {html.append(" style=\""+style+"\"");}

			html.append("/>");

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
}