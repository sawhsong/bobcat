package zebra.taglib;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import zebra.base.TaglibSupport;
import zebra.util.CommonUtil;

public class Icon extends TaglibSupport {
	private String id;
	private String name;
	private String title;
	private String className;
	private String style;
	private String script;

	public int doStartTag() {
		try {
			JspWriter jspWriter = pageContext.getOut();
			HttpSession httpSession = pageContext.getSession();
			String langCode = (String)httpSession.getAttribute("langCode");
			StringBuffer html = new StringBuffer();
			String classNamePrefix = "";

			html.append("<i");

			if (CommonUtil.isNotBlank(id)) {html.append(" id=\""+id+"\"");}
			if (CommonUtil.isNotBlank(name)) {html.append(" name=\""+name+"\"");}
			if (CommonUtil.isNotBlank(title)) {
				title = CommonUtil.containsIgnoreCase(title, ".") ? getMessage(title, langCode) : title;
				html.append(" title=\""+title+"\"");
			}
			if (CommonUtil.isNotBlank(className)) {
				if (CommonUtil.startsWithIgnoreCase(className, "fa-")) {
					classNamePrefix = "fa fa-lg";
				} else if (CommonUtil.startsWithIgnoreCase(className, "glyphicon-")) {
					classNamePrefix = "glyphicon";
				}
				html.append(" class=\""+classNamePrefix+" "+className+"\"");
			}
			if (CommonUtil.isNotBlank(style)) {html.append(" style=\""+style+"\"");}
			if (CommonUtil.isNotBlank(script)) {html.append(" onclick=\""+script+"\"");}

			html.append("></i>");

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}
}