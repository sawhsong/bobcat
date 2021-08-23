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
				session.setAttribute("langCode", CommonUtil.lowerCase(sysUser.getLanguage()));
				session.setAttribute("themeId", CommonUtil.lowerCase(sysUser.getThemeType()));
				session.setAttribute("maxRowsPerPage", CommonUtil.toString(sysUser.getMaxRowPerPage(), "###"));
				session.setAttribute("pageNumsPerPage", CommonUtil.toString(sysUser.getPageNumPerPage(), "###"));
				session.setAttribute("SysUser", sysUser);
				session.setAttribute("SysOrg", sysOrg);
				session.setAttribute("OrgLegalName", sysOrg.getLegalName());
				session.setAttribute("DefaultPeriodYear", paramEntity.getObject("defaultPeriodYear"));
				session.setAttribute("DefaultFinancialYear", paramEntity.getObject("defaultFinancialYear"));
				session.setAttribute("DefaultQuarterCode", paramEntity.getObject("defaultQuarterCode"));
				session.setAttribute("DefaultQuarterName", paramEntity.getObject("defaultQuarterName"));
				session.setAttribute("AuthenticationKey", (String)paramEntity.getObject("authenticationKey"));

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

	public String getUserDetail() throws Exception {
		try {
			biz.getUserDetail(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String saveUserDetail() throws Exception {
		try {
			biz.saveUserDetail(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getUsers() throws Exception {
		biz.getUsers(paramEntity);
		return "getUsers";
	}

	public String getUserList() throws Exception {
		try {
			biz.getUserList(paramEntity);
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
				session.setAttribute("OrgNameForAdminTool", sysOrgForAdminTool.getLegalName());
				session.setAttribute("OrgAbnForAdminTool", CommonUtil.getFormatString(sysOrgForAdminTool.getAbn(), "?? ??? ??? ???"));
				session.setAttribute("SysUserForAdminTool", sysUserForAdminTool);
				session.setAttribute("SysOrgForAdminTool", sysOrgForAdminTool);

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
			session.removeAttribute("OrgNameForAdminTool");
			session.removeAttribute("OrgAbnForAdminTool");
			session.removeAttribute("SysUserForAdminTool");
			session.removeAttribute("SysOrgForAdminTool");
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getAuthentication() throws Exception {
		biz.index(paramEntity);
		return "authenticate";
	}

	public String doAuthentication() throws Exception {
		try {
			biz.doAuthentication(paramEntity);

			if (paramEntity.isSuccess()) {
				session.setAttribute("AuthenticationKey", (String)paramEntity.getObject("authenticationKey"));
				session.setAttribute("IsAuthenticated", (String)paramEntity.getObject("isAuthenticated"));
			}
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String hasAuthKey() throws Exception {
		try {
			biz.hasAuthKey(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getAuthenticationSecretKey() throws Exception {
		try {
			biz.getAuthenticationSecretKey(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String logout() throws Exception {
		MemoryBean.remove(session.getId());
		session.invalidate();
		return "index";
	}
}