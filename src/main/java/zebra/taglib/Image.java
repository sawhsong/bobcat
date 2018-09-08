package zebra.taglib;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import zebra.base.TaglibSupport;
import zebra.util.CommonUtil;
import zebra.util.TaglibUtil;

public class Image extends TaglibSupport {
	private String id;
	private String src;
	private String name;
	private String title;
	private String className;
	private String style;
	private String script;
	private String status;

	public int doStartTag() {
		try {
			JspWriter jspWriter = pageContext.getOut();
			HttpSession httpSession = pageContext.getSession();
			String langCode = (String)httpSession.getAttribute("langCode");
			StringBuffer html = new StringBuffer();

			title = CommonUtil.containsIgnoreCase(title, ".") ? getMessage(title, langCode) : title;

			html.append("<img id=\""+id+"\" src=\""+TaglibUtil.getMccpValue(src)+"\"");

			if (CommonUtil.isNotBlank(name)) {html.append(" name=\""+name+"\"");}
			if (CommonUtil.isNotBlank(title)) {html.append(" title=\""+title+"\"");}
			if (CommonUtil.isNotBlank(className)) {html.append(" class=\""+className+"\"");}
			if (CommonUtil.isNotBlank(style)) {html.append(" style=\""+style+"\"");}
			if (CommonUtil.isNotBlank(script)) {
				if (!CommonUtil.containsIgnoreCase(status, "disabled")) {html.append(" onclick=\""+script+"\"");}
			}
			if (CommonUtil.isNotBlank(status)) {html.append(" "+status+"");}

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

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}