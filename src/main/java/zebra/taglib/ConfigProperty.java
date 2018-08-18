package zebra.taglib;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import zebra.base.TaglibSupport;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class ConfigProperty extends TaglibSupport {
	private String key;

	public int doStartTag() {
		try {
			JspWriter jspWriter = pageContext.getOut();
			HttpSession httpSession = pageContext.getSession();
			String langCode = (String)httpSession.getAttribute("langCode");
			String frameworkName = ConfigUtil.getProperty("name.framework");
			String projectName = ConfigUtil.getProperty("name.project");
			String themeId = (String)httpSession.getAttribute("themeId");
			String rtnString = "";

			rtnString = ConfigUtil.getProperty(getKey());

			// Replace variable names
			if (CommonUtil.isNotBlank(rtnString)) {
				rtnString = CommonUtil.replace(rtnString, "#DB_VENDOR#", ConfigUtil.getProperty("db.vendor"));
				rtnString = CommonUtil.replace(rtnString, "#THEME_ID#", themeId);
				rtnString = CommonUtil.replace(rtnString, "#LANG_CODE#", langCode);
				rtnString = CommonUtil.replace(rtnString, "#FRAMEWORK_NAME#", frameworkName);
				rtnString = CommonUtil.replace(rtnString, "#PROJECT_NAME#", projectName);
			}

			if (CommonUtil.equalsIgnoreCase(getKey(), "hearMainMenuIconColor")) {
				if (CommonUtil.equalsIgnoreCase(themeId, "theme001")) {
					rtnString = "Black";
				} else {
					rtnString = "White";
				}
			}

			jspWriter.print(rtnString);
		} catch (Exception ex) {
			logger.error(ex);
		}

		return SKIP_BODY;
	}
	/*!
	 * getter / setter
	 */
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}