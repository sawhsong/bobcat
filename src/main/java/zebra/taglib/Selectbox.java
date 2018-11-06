package zebra.taglib;

import javax.servlet.jsp.JspWriter;

import zebra.base.TaglibBodySupport;
import zebra.util.CommonUtil;

public class Selectbox extends TaglibBodySupport {
	private String name;
	private String id;
	private String className;
	private String status;
	private String script;
	private String style;
	private String isMultiple;
	private String isBootstrap;
	private String options;	// for data validator

	public int doAfterBody() {
		try {
			bodyContent = getBodyContent();
			JspWriter jspWriter = bodyContent.getEnclosingWriter();
			StringBuffer html = new StringBuffer();
			String classString = "";

			html.append("<select");
			html.append(" id=\""+CommonUtil.nvl(id, name)+"\"");
			html.append(" name=\""+name+"\"");

			if (CommonUtil.isNotEmpty(style)) {html.append(" style=\""+style+"\"");}
			if (CommonUtil.isNotEmpty(options)) {html.append(" "+options);}
			if (CommonUtil.isNotEmpty(script)) {html.append(" onchange=\""+script+"\"");}
			if (CommonUtil.toBoolean(isMultiple)) {html.append(" multiple=\"multiple\"");}
			if (CommonUtil.equalsIgnoreCase(status, "disabled")) {html.append(" "+status);}
			// css class
			if (!CommonUtil.toBoolean(isBootstrap, true)) {
				if (CommonUtil.toBoolean(isMultiple)) {classString = "selMulti";}
				else {classString = "selSingle";}

				if (CommonUtil.equalsIgnoreCase(status, "disabled")) {classString += "Dis";}
				else {classString += "En";}
			} else {
				classString = "bootstrapSelect";
			}

			html.append(" class=\""+classString+" "+CommonUtil.nvl(className)+"\"");
			html.append(">\n");

			html.append(bodyContent.getString());

			html.append("</select>\n");

			jspWriter.print(html.toString());
		} catch (Exception ex) {
			logger.error(ex);
		}

		return SKIP_BODY;
	}
	/*!
	 * getter / setter
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getIsMultiple() {
		return isMultiple;
	}

	public void setIsMultiple(String isMultiple) {
		this.isMultiple = isMultiple;
	}

	public String getIsBootstrap() {
		return isBootstrap;
	}

	public void setIsBootstrap(String isBootstrap) {
		this.isBootstrap = isBootstrap;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}
}