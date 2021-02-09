package project.common.module.autocompletion;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseAction;

public class AutoCompletionAction extends BaseAction {
	@Autowired
	private AutoCompletionBiz biz;

	public String getUserId() throws Exception {
		try {
			biz.getUserId(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getLoginId() throws Exception {
		try {
			biz.getLoginId(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getUserName() throws Exception {
		try {
			biz.getUserName(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getOrgName() throws Exception {
		try {
			biz.getOrgName(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getOrgId() throws Exception {
		try {
			biz.getOrgId(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getOrgByIdOrName() throws Exception {
		try {
			biz.getOrgByIdOrName(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getAbn() throws Exception {
		try {
			biz.getAbn(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}
}