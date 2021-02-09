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
				session.setAttribute("langCode", CommonUtil.lowerCase(sysUser.getLanguage()));
				session.setAttribute("themeId", CommonUtil.lowerCase(sysUser.getThemeType()));
				session.setAttribute("maxRowsPerPage", CommonUtil.toString(sysUser.getMaxRowPerPage(), "###"));
				session.setAttribute("pageNumsPerPage", CommonUtil.toString(sysUser.getPageNumPerPage(), "###"));
				session.setAttribute("SysUser", sysUser);
				session.setAttribute("SysOrg", sysOrg);
				session.setAttribute("OrgLegalName", sysOrg.getLegalName());
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
		System.gc();
		System.runFinalization();
		return "index";
	}
}