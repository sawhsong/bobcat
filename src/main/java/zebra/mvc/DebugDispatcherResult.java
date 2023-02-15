package zebra.mvc;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.result.StrutsResultSupport;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;

import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class DebugDispatcherResult extends StrutsResultSupport {
	private Logger logger = LogManager.getLogger(this.getClass());

	@SuppressWarnings("rawtypes")
	@Override
	protected void doExecute(String finalLocation, ActionInvocation invocation) throws Exception {
		ActionContext context = invocation.getInvocationContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		Map sessionMap = context.getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		RequestDispatcher dispatcher = request.getRequestDispatcher(finalLocation);
		String userId = (String)sessionMap.get("UserId"), userName = (String)sessionMap.get("UserName");

		if (CommonUtil.equalsIgnoreCase(ConfigUtil.getProperty("log.dispatcher.result"), "Y")) {
			logger.debug("[User : "+userName+" ("+userId+")] "+invocation.getAction().getClass().getSimpleName() + " => " + invocation.getResultCode() + " => " + finalLocation);
		}

		dispatcher.forward(request, response);
	}
}