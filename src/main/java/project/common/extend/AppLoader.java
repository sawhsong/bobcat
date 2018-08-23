package project.common.extend;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import project.common.module.commoncode.CommonCodeManager;
import project.common.module.menu.MenuManager;
import zebra.config.MemoryBean;
import zebra.util.CommonUtil;

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

		// Project
		MenuManager.loadMenu();
		CommonCodeManager.loadCommonCode();
	}
}