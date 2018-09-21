package zebra.taglib;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import zebra.base.TaglibSupport;
import zebra.util.CommonUtil;

public class Textarea extends TaglibSupport {
	private String id;
	private String name;
	private String className;
	private String value;
	private String style;
	private String script;
	private String title;
	private String placeHolder;
	private String checkName;
	private String maxbyte;
	private String minbyte;
	private String checkFlag;
	private String option;
	private String options;
	private String status;

	public int doStartTag() {
		try {
			String defaultClassName = "defClass"; // Special Keyword for className
			JspWriter jspWriter = pageContext.getOut();
			HttpSession httpSession = pageContext.getSession();
			String langCode = (String)httpSession.getAttribute("langCode");
			StringBuffer html = new StringBuffer();
			String classNamePrefix = "", scriptStr = "";
			String scripts[], eventFunc[];

			if (CommonUtil.containsIgnoreCase(className, defaultClassName)) {
				if (CommonUtil.containsIgnoreCase(status, "disabled")) {
					options += " readonly";
					classNamePrefix = "txaDis";
				} else if (CommonUtil.containsIgnoreCase(status, "display")) {
					options += " readonly";
					classNamePrefix = "txaDpl";
				} else {
					classNamePrefix = "txaEn";
				}
				className = CommonUtil.replace(className, defaultClassName, classNamePrefix);
			}
			title = CommonUtil.containsIgnoreCase(title, ".") ? getMessage(title, langCode) : title;
			placeHolder = CommonUtil.containsIgnoreCase(placeHolder, ".") ? getMessage(placeHolder, langCode) : placeHolder;
			checkName = CommonUtil.containsIgnoreCase(checkName, ".") ? getMessage(checkName, langCode) : checkName;

			if (CommonUtil.isNotBlank(script)) {
				scripts = CommonUtil.split(script, ";");
				for (int i=0; i<scripts.length; i++) {
					eventFunc = CommonUtil.split(script, ":");
					scriptStr += " "+eventFunc[0]+"=\""+eventFunc[1]+"\"";
				}
			}

			html.append("<textarea id=\""+id+"\" name=\""+name+"\"");

			if (CommonUtil.isNotBlank(className)) {html.append(" class=\""+className+"\"");}
			if (CommonUtil.isNotBlank(style)) {html.append(" style=\""+style+"\"");}
			if (CommonUtil.isNotBlank(scriptStr)) {html.append(" "+scriptStr+"");}
			if (CommonUtil.isNotBlank(title)) {html.append(" title=\""+title+"\"");}
			if (CommonUtil.isNotBlank(placeHolder)) {html.append(" placeholder=\""+placeHolder+"\"");}
			if (CommonUtil.isNotBlank(checkName)) {html.append(" checkName=\""+checkName+"\"");}
			if (CommonUtil.isNotBlank(maxbyte)) {html.append(" maxbyte=\""+maxbyte+"\"");}
			if (CommonUtil.isNotBlank(minbyte)) {html.append(" minbyte=\""+minbyte+"\"");}
			if (CommonUtil.isNotBlank(checkFlag)) {html.append(" checkFlag=\""+checkFlag+"\"");}
			if (CommonUtil.isNotBlank(option)) {html.append(" option=\""+option+"\"");}
			if (CommonUtil.isNotBlank(options)) {html.append(" "+options);}
			if (CommonUtil.equalsIgnoreCase(status, "disabled")) {html.append(" "+status);}
			html.append(">");
			if (CommonUtil.isNotBlank(value)) {html.append(value);}
			html.append("</textarea>");

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

	public String getPlaceHolder() {
		return placeHolder;
	}

	public void setPlaceHolder(String placeHolder) {
		this.placeHolder = placeHolder;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public String getMaxbyte() {
		return maxbyte;
	}

	public void setMaxbyte(String maxbyte) {
		this.maxbyte = maxbyte;
	}

	public String getMinbyte() {
		return minbyte;
	}

	public void setMinbyte(String minbyte) {
		this.minbyte = minbyte;
	}

	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}