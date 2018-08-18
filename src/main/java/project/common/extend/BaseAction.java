package project.common.extend;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import zebra.base.Action;

public class BaseAction extends Action {
	protected Logger logger = LogManager.getLogger(this.getClass());

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setSession(Map session) {
		try {
			session.put("headerMenuId", requestDataSet.getValue("hdnHeaderMenuId"));
			session.put("headerMenuName", requestDataSet.getValue("hdnHeaderMenuName"));
			session.put("headerMenuUrl", requestDataSet.getValue("hdnHeaderMenuUrl"));
			session.put("leftMenuId", requestDataSet.getValue("hdnLeftMenuId"));
			session.put("leftMenuName", requestDataSet.getValue("hdnLeftMenuName"));
			session.put("leftMenuUrl", requestDataSet.getValue("hdnLeftMenuUrl"));
		} catch (Exception e) {
			logger.error(e);
		}

		super.setSession(session);
	}
}