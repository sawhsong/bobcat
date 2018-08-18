/**************************************************************************************************
 * Project
 * Description
 * - Login
 *************************************************************************************************/
package project.app.login;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseAction;
import project.conf.resource.ormapper.dto.oracle.SysOrg;
import project.conf.resource.ormapper.dto.oracle.SysUser;
import zebra.config.MemoryBean;
import zebra.data.DataSet;
import zebra.util.CommonUtil;

public class LoginAction extends BaseAction {
	@Autowired
	private LoginBiz biz;

	public String index() throws Exception {
		biz.index(paramEntity);
		return "loginPage";
	}

	public String resetPassword() throws Exception {
		biz.index(paramEntity);
		return "resetPassword";
	}

	public String requestRegister() throws Exception {
		biz.index(paramEntity);
		return "requestRegister";
	}

	public String login() throws Exception {
		try {
			biz.exeLogin(paramEntity);

			if (paramEntity.isSuccess()) {
				SysUser sysUser = (SysUser)paramEntity.getObject("sysUser");
				SysOrg sysOrg = (SysOrg)paramEntity.getObject("sysOrg");

				session.setAttribute("UserId", sysUser.getUserId());
				session.setAttribute("UserName", sysUser.getUserName());
				session.setAttribute("LoginId", sysUser.getLoginId());
				session.setAttribute("OrgId", sysUser.getOrgId());
				session.setAttribute("langCode", sysUser.getLanguage());
				session.setAttribute("themeId", sysUser.getThemeType());
				session.setAttribute("maxRowsPerPage", CommonUtil.toString(sysUser.getMaxRowPerPage(), "###"));
				session.setAttribute("pageNumsPerPage", CommonUtil.toString(sysUser.getPageNumPerPage(), "###"));
				session.setAttribute("SysUser", sysUser);
				session.setAttribute("SysOrg", sysOrg);
				session.setAttribute("OrgLegalName", sysOrg.getLegalName());
				session.setAttribute("OrgCategory", sysOrg.getOrgCategory());
				session.setAttribute("DefaultPeriodYear", paramEntity.getObject("defaultPeriodYear"));
				session.setAttribute("DefaultFinancialYear", paramEntity.getObject("defaultFinancialYear"));
				session.setAttribute("DefaultQuarterCode", paramEntity.getObject("defaultQuarterCode"));
				session.setAttribute("DefaultQuarterName", paramEntity.getObject("defaultQuarterName"));

				paramEntity.setAjaxResponseDataSet(sysUser.getDataSet());
			}
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String exeResetPassword() throws Exception {
		try {
			biz.exeResetPassword(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String exeRequestRegister() throws Exception {
		try {
			biz.exeRequestRegister(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getUserProfile() throws Exception {
		biz.getUserProfile(paramEntity);
		return "userProfile";
	}

	public String getUpdateUserProfile() throws Exception {
		biz.getUserProfile(paramEntity);
		return "updateUserProfile";
	}

	public String exeUpdate() throws Exception {
//		try {
//			biz.exeUpdate(paramEntity);
//		} catch (Exception ex) {
//			return "ajaxResponse";
//		} finally {
//			setRequestAttribute("paramEntity", paramEntity);
//		}
//		return "ajaxResponse";

		try {
			biz.exeUpdate(paramEntity);

			paramEntity.setObject("messageCode", paramEntity.getMessageCode());
			paramEntity.setObject("message", paramEntity.getMessage());
			if (paramEntity.isSuccess()) {
				paramEntity.setObject("action", "/login/logout.do");
				paramEntity.setObject("message", paramEntity.getMessage()+"<br/>"+getMessage("login.message.restart", paramEntity));
			} else {
				paramEntity.setObject("script", "history.go(-1);");
			}
		} catch (Exception ex) {
			paramEntity.setObject("script", "history.go(-1);");
		}

		return "pageHandler";
	}

	public String controlAdminTool() throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		String flag = requestDataSet.getValue("flag");

		try {
			session.setAttribute("isVisibleAdminTool", flag);
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String setSessionValuesForAdminTool() throws Exception {
		try {
			biz.setSessionValuesForAdminTool(paramEntity);

			if (paramEntity.isSuccess()) {
				SysUser sysUserForAdminTool = (SysUser)paramEntity.getObject("sysUserForAdminTool");
				SysOrg sysOrgForAdminTool = (SysOrg)paramEntity.getObject("sysOrgForAdminTool");

				session.setAttribute("UserIdForAdminTool", sysUserForAdminTool.getUserId());
				session.setAttribute("UserNameForAdminTool", sysUserForAdminTool.getUserName());
				session.setAttribute("LoginIdForAdminTool", sysUserForAdminTool.getLoginId());
				session.setAttribute("OrgIdForAdminTool", sysUserForAdminTool.getOrgId());
				session.setAttribute("SysUserForAdminTool", sysUserForAdminTool);
				session.setAttribute("SysOrgForAdminTool", sysOrgForAdminTool);
				session.setAttribute("OrgLegalNameForAdminTool", paramEntity.getObject("orgLegalNameForAdminTool"));
				session.setAttribute("OrgCategoryForAdminTool", paramEntity.getObject("orgCategoryForAdminTool"));
				session.setAttribute("OrgCategoryDescForAdminTool", paramEntity.getObject("orgCategoryDescForAdminTool"));
			}
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String removeSessionValuesForAdminTool() throws Exception {
		try {
			session.removeAttribute("UserIdForAdminTool");
			session.removeAttribute("UserNameForAdminTool");
			session.removeAttribute("LoginIdForAdminTool");
			session.removeAttribute("OrgIdForAdminTool");
			session.removeAttribute("SysUserForAdminTool");
			session.removeAttribute("SysOrgForAdminTool");
			session.removeAttribute("OrgLegalNameForAdminTool");
			session.removeAttribute("OrgCategoryForAdminTool");
			session.removeAttribute("OrgCategoryDescForAdminTool");
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String logout() throws Exception {
		MemoryBean.remove(session.getId());
		session.invalidate();
		System.gc();
		System.runFinalization();
		return "index";
	}
}