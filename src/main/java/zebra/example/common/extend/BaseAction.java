package zebra.example.common.extend;

import java.util.Map;

import zebra.base.Action;

public class BaseAction extends Action {
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