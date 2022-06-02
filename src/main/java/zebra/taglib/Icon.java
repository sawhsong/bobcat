package zebra.taglib;

import java.lang.reflect.Field;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import zebra.base.TaglibSupport;
import zebra.util.CommonUtil;

public class Icon extends TaglibSupport {
	private String id = "";
	private String title = "";
	private String className = "";
	private String style = "";
	private String script = "";
	private String status = "";
	private String attribute = "";
	private String useFor = "";

	public int doStartTag() {
		try {
			JspWriter jspWriter = pageContext.getOut();
			HttpSession httpSession = pageContext.getSession();
			String langCode = (String)httpSession.getAttribute("langCode");
			StringBuffer html = new StringBuffer();
			String classNamePrefix = "", attrStr = "", attrs[], attr[];

			title = CommonUtil.containsIgnoreCase(title, ".") ? getMessage(title, langCode) : title;

			if (CommonUtil.containsIgnoreCase(status, "display")) {
			} else if (CommonUtil.containsIgnoreCase(status, "disabled")) {
				classNamePrefix = "icnDis";
			} else {
				classNamePrefix = "icnEn";
			}

			// Auto set
			if (CommonUtil.isNotBlank(useFor)) {
				className += " "+getAutoIconClass(useFor);
			}

			if (CommonUtil.containsIgnoreCase(className, "fa-")) {
				classNamePrefix += " fa";
			} else if (CommonUtil.containsIgnoreCase(className, "glyphicon-")) {
				classNamePrefix += " glyphicon";
			}
			className = (CommonUtil.isBlank(className)) ? classNamePrefix : classNamePrefix+" "+className;

			if (CommonUtil.isNotBlank(attribute)) {
				attrs = CommonUtil.split(attribute, ";");
				for (int i=0; i<attrs.length; i++) {
					attr = CommonUtil.split(attrs[i], ":");
					attrStr += " "+attr[0]+"=\""+attr[1]+"\"";
				}
			}

			html.append("<i");

			if (CommonUtil.isNotBlank(id)) {html.append(" id=\""+id+"\"");}
			if (CommonUtil.isNotBlank(title)) {html.append(" title=\""+title+"\"");}
			if (CommonUtil.isNotBlank(className)) {html.append(" class=\""+className+"\"");}
			if (CommonUtil.isNotBlank(style)) {html.append(" style=\""+style+"\"");}
			if (CommonUtil.isNotBlank(script)) {html.append(" onclick=\""+script+"\"");}
			if (CommonUtil.isNotBlank(attrStr)) {html.append(" "+attrStr+"");}

			html.append("></i>");

			jspWriter.print(html.toString());
			initialise();
		} catch (Exception ex) {
			logger.error(ex);
		}

		return SKIP_BODY;
	}

	/*!
	 * getter / setter
	 */
	@SuppressWarnings("rawtypes")
	private void initialise() throws Exception {
		Class cls = getClass();
		Field fields[] = cls.getDeclaredFields();
		for (int i=0; i<fields.length; i++) {
			fields[i].set(this, "");
		}
	}

	private String getAutoIconClass(String useFor) {
		switch (CommonUtil.lowerCase(useFor)) {
			case "actionheadergrid":
				return "fa-magic fa-lg";
			case "checkgrid":
				return "fa-check-square-o fa-lg";
			case "radiogrid":
				return "fa-dot-circle-o fa-lg";
			case "action":
				return "fa-ellipsis-h fa-lg";
			case "calendar":
				return "fa-calendar";
			case "refresh":
				return "fa-refresh fa-lg";
			case "delete":
				return "fa-times fa-lg";
			case "lookup":
				return "fa-search";
			case "sort":
				return "fa-sort fa-lg";
			default:
				return "";
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
}