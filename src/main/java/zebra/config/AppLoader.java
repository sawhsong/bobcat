package zebra.config;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import zebra.example.common.module.commoncode.ZebraCommonCodeManager;
import zebra.example.common.module.menu.ZebraMenuManager;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class AppLoader extends HttpServlet {
	private Logger logger = LogManager.getLogger(this.getClass());
	private ServletContext context;

	public void init(ServletConfig config) throws ServletException {
		try {
			this.context = config.getServletContext();
			executeWorks();
		} catch (Exception ex) {
			logger.error("Application loading failed.");
			logger.error(ex);
		}
	}

	/*!
	 * important : execution order
	 * MemonyBean
	 */
	private void executeWorks() throws Exception {
		MemoryBean.set("applicationRealPath", CommonUtil.replace(context.getRealPath(""), File.separator, "/"));
		MemoryBean.set("applicationSrcPathRoot", CommonUtil.remove((String)MemoryBean.get("applicationRealPath"), "/target/alpaca/"));
		MemoryBean.set("applicationSrcPathSrc", MemoryBean.get("applicationSrcPathRoot")+"/src");
		MemoryBean.set("applicationSrcPathJava", MemoryBean.get("applicationSrcPathSrc")+"/main/java");
		MemoryBean.set("applicationSrcPathWeb", MemoryBean.get("applicationSrcPathSrc")+"/main/webapp");

		if (CommonUtil.toBoolean(ConfigUtil.getProperty("log.debug.config"))) {
//			for (Enumeration attr = System.getProperties().propertyNames(); attr.hasMoreElements();) {
//				String key = (String) attr.nextElement();
//				logger.debug("System.PropertyName [" + key + "] : " + System.getProperty(key));
//			}

//			logger.debug("MemoryBean.applicationRealPath : "+MemoryBean.get("applicationRealPath"));
//			logger.debug("MemoryBean.applicationSrcPathRoot : "+MemoryBean.get("applicationSrcPathRoot"));
//			logger.debug("MemoryBean.applicationSrcPathSrc : "+MemoryBean.get("applicationSrcPathSrc"));
//			logger.debug("MemoryBean.applicationSrcPathJava : "+MemoryBean.get("applicationSrcPathJava"));
//			logger.debug("MemoryBean.applicationSrcPathWeb : "+MemoryBean.get("applicationSrcPathWeb"));
		}

		// Framework Menu & CommonCode
		ZebraMenuManager.loadMenu();
		ZebraCommonCodeManager.loadCommonCode();

		MessageManager.loadMessageBundles();
	}
}