package project.app.login;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;

import de.taimos.totp.TOTP;
import project.common.extend.BaseBiz;
import project.common.module.commoncode.CommonCodeManager;
import project.common.module.datahelper.DataHelper;
import project.conf.resource.ormapper.dao.SysOrg.SysOrgDao;
import project.conf.resource.ormapper.dao.SysUser.SysUserDao;
import project.conf.resource.ormapper.dto.oracle.SysOrg;
import project.conf.resource.ormapper.dto.oracle.SysUser;
import zebra.config.MemoryBean;
import zebra.data.DataSet;
import zebra.data.ParamEntity;
import zebra.exception.FrameworkException;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;
import zebra.util.FileUtil;

public class LoginBizImpl extends BaseBiz implements LoginBiz {
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysOrgDao sysOrgDao;
	@Autowired
	private LoginMessageSender loginMessageSender;

	public ParamEntity index(ParamEntity paramEntity) throws Exception {
		HttpServletRequest request = paramEntity.getRequest();
		HttpSession session = paramEntity.getSession();
		String language = request.getLocale().getLanguage();

		try {
			session.setAttribute("langCode", language);
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity exeResetPassword(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		DataSet userDataSet = new DataSet();
		SysUser sysUser = new SysUser();
		String randomString = CommonUtil.getRandomAlphanumeric(12);
		String loginId = requestDataSet.getValue("loginId");
		String email = requestDataSet.getValue("email");
		int result = -1;

		try {
			// Check if the user exists
			userDataSet = sysUserDao.getUserInfoDataSetByLoginId(loginId);
			if (userDataSet.getRowCnt() <= 0) {
				throw new FrameworkException("E907", getMessage("E907", paramEntity));
			}

			// Check if the email is matching
			userDataSet = sysUserDao.getUserInfoDataSetByLoginIdAndEmail(loginId, email);
			if (userDataSet.getRowCnt() <= 0) {
				throw new FrameworkException("E914", getMessage("E914", paramEntity));
			}

			// Initailise the password
			sysUser.addUpdateColumn("login_password", randomString);
			result = sysUserDao.initialisePassword(paramEntity, sysUser);
			if (result <= 0) {
				throw new FrameworkException("E904", getMessage("E904", paramEntity));
			}

			// Select SysUser
			sysUser = sysUserDao.getUserByLoginId(loginId);

			loginMessageSender.sendResetPasswordMessage(sysUser);

			paramEntity.setSuccess(true);
			paramEntity.setMessage("I801", getMessage("I801", paramEntity));
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity exeRequestRegister(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		SysUser sysUser = new SysUser();
		String uid = CommonUtil.uid();
		String loginId = requestDataSet.getValue("loginId");
		String password = requestDataSet.getValue("password");
		String email = requestDataSet.getValue("email");
		String isSendEmail = CommonUtil.nvl(requestDataSet.getValue("sendEmail"), "N");
		String photoPathName = ConfigUtil.getProperty("path.image.photo")+"/"+"DefaultUser_128_Black.png";
		int result = -1;

		try {
			// Check if the user already exists with the login id and password
			sysUser = sysUserDao.getUserByLoginIdAndPassword(loginId, password);
			if (!(sysUser == null || CommonUtil.isEmpty(sysUser.getUserId()))) {
				throw new FrameworkException("E912", getMessage("E912", paramEntity));
			}

			// Sets SysUser object and save it
			sysUser = new SysUser();
			sysUser.setUserId(uid);
			sysUser.setUserName(requestDataSet.getValue("userName"));
			sysUser.setLoginId(requestDataSet.getValue("loginId"));
			sysUser.setLoginPassword(requestDataSet.getValue("password"));
			sysUser.setOrgId(null);
			sysUser.setAuthGroupId("Z"); //SysAuthGroup.GroupId(Not Selected)
			sysUser.setLanguage(CommonUtil.lowerCase(ConfigUtil.getProperty("etc.default.language")));
			sysUser.setThemeType(CommonUtil.lowerCase(ConfigUtil.getProperty("view.theme.default")));
			sysUser.setMaxRowPerPage(CommonUtil.toDouble(CommonUtil.split(ConfigUtil.getProperty("view.data.maxRowsPerPage"), ConfigUtil.getProperty("delimiter.data"))[2]));
			sysUser.setPageNumPerPage(CommonUtil.toDouble(CommonUtil.split(ConfigUtil.getProperty("view.data.pageNumsPerPage"), ConfigUtil.getProperty("delimiter.data"))[0]));
			sysUser.setEmail(email);
			sysUser.setUserStatus(CommonCodeManager.getCodeByConstants("USER_STATUS_RR"));
			sysUser.setPhotoPath(photoPathName);
			sysUser.setIsActive(CommonCodeManager.getCodeByConstants("IS_ACTIVE_N"));
			sysUser.setInsertUserId("0");

			result = sysUserDao.insert(sysUser);
			if (result <= 0) {
				throw new FrameworkException("E801", getMessage("E801", paramEntity));
			}

			// Sends email to the user
			if (CommonUtil.equals(isSendEmail, "Y")) {
				loginMessageSender.sendRequestRegisterMessage(sysUser, email);
			}

			paramEntity.setSuccess(true);
			paramEntity.setMessage("I801", getMessage("I801", paramEntity));
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity exeLogin(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		SysUser sysUser = new SysUser();
		String loginId = requestDataSet.getValue("loginId");
		String password = requestDataSet.getValue("password");
		String loginAuthEmailKey = ConfigUtil.getProperty("login.auth.emailKey");
		String email = "", authKey = "";

		try {
			// Check with LoginID
			sysUser = sysUserDao.getUserByLoginId(loginId);
			if (sysUser == null || CommonUtil.isBlank(sysUser.getUserId()) || !CommonUtil.toBoolean(sysUser.getIsActive())) {
				throw new FrameworkException("E907", getMessage("E907", paramEntity));
			}

			// Check with LoginID and Password
			sysUser = sysUserDao.getUserByLoginIdAndPassword(loginId, password);
			if (sysUser == null || CommonUtil.isBlank(sysUser.getUserId()) || !CommonUtil.toBoolean(sysUser.getIsActive())) {
				throw new FrameworkException("E908", getMessage("E908", paramEntity));
			}

			if (CommonUtil.toBoolean(loginAuthEmailKey)) {
				Random random = new Random();

				email = sysUser.getEmail();
				authKey = CommonUtil.leftPad(CommonUtil.toString(random.nextInt(999999)), 6, "0");
				paramEntity.setObject("authenticationKey", authKey);
				loginMessageSender.sendAuthKey(sysUser, email, authKey);
			}

			paramEntity.setObject("sysUser", sysUser);
			paramEntity.setObject("sysOrg", sysOrgDao.getOrgByOrgId(sysUser.getOrgId()));

			paramEntity.setSuccess(true);
			paramEntity.setMessage("I903", getMessage("I903", paramEntity));
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getUserProfile(ParamEntity paramEntity) throws Exception {
		try {
			paramEntity.setObject("defaultPhotoPath", ConfigUtil.getProperty("path.image.photo")+"/"+"DefaultUser_128_Black.png");
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getUserDetail(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		String userId = requestDataSet.getValue("userId");
		DataSet userDataSet = new DataSet();

		try {
			userDataSet = sysUserDao.getUserInfoDataSetByUserId(userId);
			userDataSet.addColumn("INSERT_USER_NAME", DataHelper.getUserNameById(userDataSet.getValue("INSERT_USER_ID")));
			userDataSet.addColumn("UPDATE_USER_NAME", DataHelper.getUserNameById(userDataSet.getValue("UPDATE_USER_ID")));
			userDataSet.addColumn("ORG_NAME", DataHelper.getOrgNameById(userDataSet.getValue("ORG_ID")));

			paramEntity.setAjaxResponseDataSet(userDataSet);
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity saveUserDetail(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		DataSet fileDataSet = paramEntity.getRequestFileDataSet();
		HttpSession session = paramEntity.getSession();
		String userId = "", isLoginIdPasswordChanged = "";
		String defaultPhotoPath = ConfigUtil.getProperty("path.image.photo");
		String uploadPhotoPath = ConfigUtil.getProperty("path.dir.uploadedPhoto");
		String webRootPath = (String)MemoryBean.get("applicationRealPath");
		String pathToCopy = "";
		SysUser sysUser = new SysUser(), sysUserToCompare = new SysUser();
		DataSet userDataSet;
		int result = -1;

		try {
			userId = requestDataSet.getValue("userId");

			sysUser = sysUserDao.getUserByUserId(userId);
			sysUserToCompare = sysUserDao.getUserByUserId(userId);

			sysUser.setUserName(requestDataSet.getValue("userName"));
			sysUser.setLoginId(requestDataSet.getValue("loginId"));
			sysUser.setLoginPassword(requestDataSet.getValue("password"));
			sysUser.setOrgId(requestDataSet.getValue("orgId"));
			sysUser.setEmail(requestDataSet.getValue("email"));
			sysUser.setAuthenticationSecretKey(requestDataSet.getValue("authenticationSecretKey"));
			sysUser.setUpdateUserId((String)session.getAttribute("UserId"));
			sysUser.setUpdateDate(CommonUtil.toDate(CommonUtil.getSysdate()));

			if (fileDataSet.getRowCnt() > 0) {
				String fileName = fileDataSet.getValue("NEW_NAME");
				String userFileName = userId + "_" + fileName;

				// Copy the file to web source
				pathToCopy = webRootPath + defaultPhotoPath + "/" + userFileName;
				FileUtil.copyFile(fileDataSet, pathToCopy);

				// Move the file to repository
				pathToCopy = uploadPhotoPath + "/" + userFileName;
				FileUtil.moveFile(fileDataSet, pathToCopy);

				sysUser.setPhotoPath(defaultPhotoPath + "/" + userFileName);
			}

			if (!CommonUtil.equals(sysUserToCompare.getLoginId(), sysUser.getLoginId()) || !CommonUtil.equals(sysUserToCompare.getLoginPassword(), sysUser.getLoginPassword())) {
				isLoginIdPasswordChanged = "Y";
			}

			sysUser.addUpdateColumnFromField();
			result = sysUserDao.update(userId, sysUser);

			if (result <= 0) {
				throw new FrameworkException("E801", getMessage("E801", paramEntity));
			}

			userDataSet = sysUserDao.getUserInfoDataSetByUserId(userId);
			userDataSet.addColumn("ORG_NAME", DataHelper.getOrgNameById(userDataSet.getValue("ORG_ID")));
			userDataSet.addColumn("isLoginIdPasswordChanged", isLoginIdPasswordChanged);

			paramEntity.setAjaxResponseDataSet(userDataSet);
			paramEntity.setSuccess(true);
			paramEntity.setMessage("I801", getMessage("I801", paramEntity));
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getUserStatusBoard(ParamEntity paramEntity) throws Exception {
		try {
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity setSessionValuesForAdminTool(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		SysUser sysUser = new SysUser();
		SysOrg sysOrg = new SysOrg();
		String userId = requestDataSet.getValue("userId");
		DataSet resultDataSet = new DataSet();

		try {
			sysUser = sysUserDao.getUserByUserId(userId);
			sysOrg = sysOrgDao.getOrgByOrgId(sysUser.getOrgId());

			paramEntity.setObject("sysUserForAdminTool", sysUser);
			paramEntity.setObject("sysOrgForAdminTool", sysOrg);
			paramEntity.setObject("orgLegalNameForAdminTool", sysOrg.getLegalName());

			resultDataSet.addName(new String[] {"user_id", "user_name", "login_id", "org_id", "org_name", "org_category_desc"});
			resultDataSet.addRow();
			resultDataSet.setValue("user_id", sysUser.getUserId());
			resultDataSet.setValue("user_name", sysUser.getUserName());
			resultDataSet.setValue("login_id", sysUser.getLoginId());
			resultDataSet.setValue("org_id", sysUser.getOrgId());
			resultDataSet.setValue("org_name", sysOrg.getLegalName());

			paramEntity.setAjaxResponseDataSet(resultDataSet);
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity hasAuthKey(ParamEntity paramEntity) throws Exception {
		HttpSession session = paramEntity.getSession();
		DataSet resultDataSet = new DataSet();

		try {
			SysUser sysUser = (SysUser)session.getAttribute("SysUser");

			resultDataSet.addColumn("hasAuthKey", CommonUtil.isNotBlank(sysUser.getAuthenticationSecretKey()) ? "true" : "false");

			paramEntity.setAjaxResponseDataSet(resultDataSet);
			paramEntity.setSuccess(true);

			return paramEntity;
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
	}

	public ParamEntity getAuthenticationSecretKey(ParamEntity paramEntity) throws Exception {
		DataSet resultDataSet = new DataSet();

		try {
			resultDataSet.addColumn("authenticationSecretKey", CommonUtil.getAuthenticationSecretKey());

			paramEntity.setAjaxResponseDataSet(resultDataSet);
			paramEntity.setSuccess(true);

			return paramEntity;
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
	}

	public ParamEntity doAuthentication(ParamEntity paramEntity) throws Exception {
		String secretKey = "";
		HttpSession session = paramEntity.getSession();
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		DataSet resultDataSet = new DataSet();
		Base32 base32 = new Base32();
		byte[] bytes;
		String mode = requestDataSet.getValue("mode");
		String inputCode = requestDataSet.getValue("inputCode");
		String isAuthenticated = "", hexKey = "", authCode = "";

		try {
			if (CommonUtil.equalsIgnoreCase(mode, "google2fa")) {
				SysUser sysUser = (SysUser)session.getAttribute("SysUser");
				secretKey = sysUser.getAuthenticationSecretKey();

				if (CommonUtil.isBlank(secretKey)) {
					throw new FrameworkException("E913", getMessage("E913", paramEntity));
				}

				bytes = base32.decode(secretKey);
				hexKey = Hex.encodeHexString(bytes);
				authCode = TOTP.getOTP(hexKey);
				isAuthenticated = CommonUtil.equals(inputCode, authCode) ? "true" : "false";
				paramEntity.setObject("authenticationKey", authCode);
			} else if (CommonUtil.equalsIgnoreCase(mode, "emailKey")) {
				authCode = (String)session.getAttribute("AuthenticationKey");
				isAuthenticated = CommonUtil.equals(inputCode, authCode) ? "true" : "false";
			} else {
				isAuthenticated = "true";
			}

			resultDataSet.addColumn("isAuthenticated", isAuthenticated);

			paramEntity.setObject("isAuthenticated", isAuthenticated);
			paramEntity.setAjaxResponseDataSet(resultDataSet);
			paramEntity.setSuccess(true);

			return paramEntity;
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
	}
}