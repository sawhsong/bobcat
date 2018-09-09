package zebra.taglib;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import zebra.base.TaglibSupport;
import zebra.util.CommonUtil;

public class Anchor extends TaglibSupport {
	private String id;
	private String caption;
	private String className;
	private String style;
	private String script;
	private String title;
	private String status;

	public int doStartTag() {
		try {
			String defaultClassName = "defClass"; // Special Keyword for className
			JspWriter jspWriter = pageContext.getOut();
			HttpSession httpSession = pageContext.getSession();
			String langCode = (String)httpSession.getAttribute("langCode");
			StringBuffer html = new StringBuffer();
			String classNamePrefix = "";

			if (CommonUtil.containsIgnoreCase(className, defaultClassName)) {
				if (CommonUtil.containsIgnoreCase(status, "disabled")) {
					classNamePrefix = "aDis";
				} else {
					classNamePrefix = "aEn";
				}
				className = CommonUtil.replace(className, defaultClassName, classNamePrefix);
			}
			caption = CommonUtil.containsIgnoreCase(caption, ".") ? getMessage(caption, langCode) : caption;
			title = CommonUtil.containsIgnoreCase(title, ".") ? getMessage(title, langCode) : title;

			html.append("<a");

			if (CommonUtil.isNotBlank(id)) {html.append(" id=\""+id+"\"");}
			if (CommonUtil.isNotBlank(className)) {html.append(" class=\""+className+"\"");}
			if (CommonUtil.isNotBlank(style)) {html.append(" style=\""+style+"\"");}
			if (CommonUtil.isNotBlank(script)) {
				if (!CommonUtil.containsIgnoreCase(status, "disabled")) {html.append(" onclick=\""+script+"\"");}
			}
			if (CommonUtil.isNotBlank(title)) {html.append(" title=\""+title+"\"");}
			if (CommonUtil.isNotBlank(status)) {html.append(" "+status+"");}

			html.append(">");
			html.append(caption);
			html.append("</a>");

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

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}