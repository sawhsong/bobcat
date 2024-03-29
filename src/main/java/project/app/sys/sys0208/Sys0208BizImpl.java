/**************************************************************************************************
 * project
 * Description - Sys0208 - User Management
 *	- Generated by Source Generator
 *************************************************************************************************/
package project.app.sys.sys0208;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseBiz;
import project.common.module.commoncode.CommonCodeManager;
import project.common.module.datahelper.DataHelper;
import project.conf.resource.ormapper.dao.SysAuthGroup.SysAuthGroupDao;
import project.conf.resource.ormapper.dao.SysUser.SysUserDao;
import project.conf.resource.ormapper.dao.UsrBankAccnt.UsrBankAccntDao;
import project.conf.resource.ormapper.dto.oracle.SysUser;
import zebra.config.MemoryBean;
import zebra.data.DataSet;
import zebra.data.ParamEntity;
import zebra.data.QueryAdvisor;
import zebra.exception.FrameworkException;
import zebra.export.ExportHelper;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;
import zebra.util.ExportUtil;
import zebra.util.FileUtil;

public class Sys0208BizImpl extends BaseBiz implements Sys0208Biz {
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysAuthGroupDao sysAuthGroupDao;
	@Autowired
	private UsrBankAccntDao usrBankAccntDao;

	public ParamEntity getDefault(ParamEntity paramEntity) throws Exception {
		try {
			setAuthorityGroup(paramEntity);
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getList(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		HttpSession session = paramEntity.getSession();
		String langCode = (String)session.getAttribute("langCode");

		try {
			queryAdvisor.setObject("langCode", langCode);
			queryAdvisor.setRequestDataSet(requestDataSet);
			queryAdvisor.setPagination(true);

			setAuthorityGroup(paramEntity);

			paramEntity.setAjaxResponseDataSet(sysUserDao.getUserDataSetBySearchCriteria(queryAdvisor));
			paramEntity.setTotalResultRows(queryAdvisor.getTotalResultRows());
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getActionContextMenu(ParamEntity paramEntity) throws Exception {
		try {
			setAuthorityGroup(paramEntity);
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getEdit(ParamEntity paramEntity) throws Exception {
		try {
			setAuthorityGroup(paramEntity);

			paramEntity.setObject("maxRowPerPage", CommonUtil.split(ConfigUtil.getProperty("view.data.maxRowsPerPage"), ConfigUtil.getProperty("delimiter.data")));
			paramEntity.setObject("pageNumPerPage", CommonUtil.split(ConfigUtil.getProperty("view.data.pageNumsPerPage"), ConfigUtil.getProperty("delimiter.data")));
			paramEntity.setObject("defaultPhotoPath", ConfigUtil.getProperty("path.image.photo")+"/"+"DefaultUser_128_Black.png");
			paramEntity.setObject("defaultAuthGroup", "Z");
			paramEntity.setObject("defaultUserStatus", CommonCodeManager.getCodeByConstants("USER_STATUS_NU"));
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

	public ParamEntity getBankAccounts(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		String userId = requestDataSet.getValue("userId");
		DataSet bankAccntDataSet = new DataSet();

		try {
			bankAccntDataSet = usrBankAccntDao.getDataSetByUserId(userId);

			paramEntity.setAjaxResponseDataSet(bankAccntDataSet);
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity exeActionContextMenu(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		String mode = requestDataSet.getValue("mode");
		String chkForDel = requestDataSet.getValue("chkForDel");
		String userIds[] = CommonUtil.splitWithTrim(chkForDel, ConfigUtil.getProperty("delimiter.record"));
		SysUser sysUser = new SysUser();
		int result = 0;

		try {
			if (CommonUtil.equals(mode, "UpdateAuthGroup")) {
				sysUser.addUpdateColumn("auth_group_id", requestDataSet.getValue("authGroup"));
			} else if (CommonUtil.equals(mode, "UpdateUserStatus")) {
				sysUser.addUpdateColumn("user_status", requestDataSet.getValue("userStatus"));
			} else if (CommonUtil.equals(mode, "UpdateActiveStatus")) {
				sysUser.addUpdateColumn("is_active", requestDataSet.getValue("activeStatus"));
			}

			sysUser.addUpdateColumn("update_date", CommonUtil.getSysdate(), "date");

			result = sysUserDao.updateByUserIds(userIds, sysUser);
			if (result <= 0) {
				throw new FrameworkException("E801", getMessage("E801", paramEntity));
			}

			paramEntity.setSuccess(true);
			paramEntity.setMessage("I801", getMessage("I801", paramEntity));
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity saveUserDetail(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		DataSet fileDataSet = paramEntity.getRequestFileDataSet();
		HttpSession session = paramEntity.getSession();
		String userId = "", saveType = "";
		String defaultFileName = "DefaultUser_128_Black.png";
		String defaultPhotoPath = ConfigUtil.getProperty("path.image.photo");
		String uploadPhotoPath = ConfigUtil.getProperty("path.dir.uploadedPhoto");
		String webRootPath = (String)MemoryBean.get("applicationRealPath");
		String pathToCopy = "";
		SysUser sysUser = new SysUser();
		DataSet userDataSet;
		int result = -1;

		try {
			userId = requestDataSet.getValue("userId");

			if (CommonUtil.isBlank(userId)) {
				saveType = "Insert";
				userId = CommonUtil.uid();
			}

			if (CommonUtil.equals(saveType, "Insert")) {
				sysUser.setInsertUserId((String)session.getAttribute("UserId"));
				sysUser.setInsertDate(CommonUtil.toDate(CommonUtil.getSysdate()));

				if (fileDataSet.getRowCnt() <= 0) {
					sysUser.setPhotoPath(defaultPhotoPath + "/" + defaultFileName);
				}
			} else {
				sysUser.setUpdateUserId((String)session.getAttribute("UserId"));
				sysUser.setUpdateDate(CommonUtil.toDate(CommonUtil.getSysdate()));
			}

			sysUser.setUserId(userId);
			sysUser.setUserName(requestDataSet.getValue("userName"));
			sysUser.setLoginId(requestDataSet.getValue("loginId"));
			sysUser.setLoginPassword(requestDataSet.getValue("password"));
			sysUser.setOrgId(requestDataSet.getValue("orgId"));
			sysUser.setAuthGroupId(requestDataSet.getValue("authGroup"));
			sysUser.setLanguage(requestDataSet.getValue("language"));
			sysUser.setThemeType(requestDataSet.getValue("themeType"));
			sysUser.setTelNumber(CommonUtil.remove(requestDataSet.getValue("telNumber"), " "));
			sysUser.setMobileNumber(CommonUtil.remove(requestDataSet.getValue("mobileNumber"), " "));
			sysUser.setEmail(requestDataSet.getValue("email"));
			sysUser.setMaxRowPerPage(CommonUtil.toDouble(requestDataSet.getValue("maxRowsPerPage")));
			sysUser.setPageNumPerPage(CommonUtil.toDouble(requestDataSet.getValue("pageNumsPerPage")));
			sysUser.setUserStatus(requestDataSet.getValue("userStatus"));
			sysUser.setIsActive(requestDataSet.getValue("isActive"));
			sysUser.setDefaultStartUrl(requestDataSet.getValue("defaultStartUrl"));
			sysUser.setAuthenticationSecretKey(requestDataSet.getValue("authenticationSecretKey"));

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

			if (CommonUtil.equals(saveType, "Insert")) {
				result = sysUserDao.insert(sysUser);
			} else {
				sysUser.addUpdateColumnFromField();
				result = sysUserDao.update(userId, sysUser);
			}

			if (result <= 0) {
				throw new FrameworkException("E801", getMessage("E801", paramEntity));
			}

			userDataSet = sysUserDao.getUserInfoDataSetByUserId(userId);
			userDataSet.addColumn("INSERT_USER_NAME", DataHelper.getUserNameById(userDataSet.getValue("INSERT_USER_ID")));
			userDataSet.addColumn("UPDATE_USER_NAME", DataHelper.getUserNameById(userDataSet.getValue("UPDATE_USER_ID")));
			userDataSet.addColumn("ORG_NAME", DataHelper.getOrgNameById(userDataSet.getValue("ORG_ID")));

			paramEntity.setAjaxResponseDataSet(userDataSet);
			paramEntity.setSuccess(true);
			paramEntity.setMessage("I801", getMessage("I801", paramEntity));
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity saveBankAccnts(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		HttpSession session = paramEntity.getSession();
		String userId = requestDataSet.getValue("userId");
		String delimiter = ConfigUtil.getProperty("delimiter.data");
		String loggedinUserId = (String)session.getAttribute("UserId");
		DataSet existingBankAccnt = new DataSet(), bankAccntFromReq = new DataSet();
		int detailLength = CommonUtil.toInt(requestDataSet.getValue("detailLength"));
		String header[] = new String[] {"BANK_ACCNT_ID", "USER_ID", "BANK_CODE", "BSB", "ACCNT_NUMBER", "ACCNT_NAME", "BALANCE", "DESCRIPTION"};
		int result = 0;

		try {
			existingBankAccnt = usrBankAccntDao.getDataSetByUserId(userId);

			if (detailLength > 0) {
				bankAccntFromReq.addName(header);
				for (int i=0; i<detailLength; i++) {
					bankAccntFromReq.addRow();
					bankAccntFromReq.setValue(bankAccntFromReq.getRowCnt()-1, "BANK_ACCNT_ID", requestDataSet.getValue("bankAccntId"+delimiter+i));
					bankAccntFromReq.setValue(bankAccntFromReq.getRowCnt()-1, "USER_ID", requestDataSet.getValue("userId"));
					bankAccntFromReq.setValue(bankAccntFromReq.getRowCnt()-1, "BANK_CODE", requestDataSet.getValue("bankCode"+delimiter+i));
					bankAccntFromReq.setValue(bankAccntFromReq.getRowCnt()-1, "BSB", CommonUtil.remove(requestDataSet.getValue("bsb"+delimiter+i), " "));
					bankAccntFromReq.setValue(bankAccntFromReq.getRowCnt()-1, "ACCNT_NUMBER", requestDataSet.getValue("accntNumber"+delimiter+i));
					bankAccntFromReq.setValue(bankAccntFromReq.getRowCnt()-1, "ACCNT_NAME", requestDataSet.getValue("accntName"+delimiter+i));
					bankAccntFromReq.setValue(bankAccntFromReq.getRowCnt()-1, "BALANCE", requestDataSet.getValue("balance"+delimiter+i));
					bankAccntFromReq.setValue(bankAccntFromReq.getRowCnt()-1, "DESCRIPTION", requestDataSet.getValue("description"+delimiter+i));
				}
				result = usrBankAccntDao.insertOrUpdate(bankAccntFromReq, loggedinUserId);
			}

			for (int i=0; i<existingBankAccnt.getRowCnt(); i++) {
				String bankAccntId = existingBankAccnt.getValue(i, "BANK_ACCNT_ID");
				int idx = bankAccntFromReq.getRowIndex("BANK_ACCNT_ID", bankAccntId);

				if (idx < 0) {
					result += usrBankAccntDao.delete(bankAccntId);
				}
			}

			if (result <= 0) {
				throw new FrameworkException("E801", getMessage("E801", paramEntity));
			}

			existingBankAccnt = usrBankAccntDao.getDataSetByUserId(userId);
			paramEntity.setAjaxResponseDataSet(existingBankAccnt);
			paramEntity.setSuccess(true);
			paramEntity.setMessage("I801", getMessage("I801", paramEntity));
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity exeDelete(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		String userId = requestDataSet.getValue("userId");
		String chkForDel = requestDataSet.getValue("chkForDel");
		String userIds[] = CommonUtil.splitWithTrim(chkForDel, ConfigUtil.getProperty("delimiter.record"));
		int result = -1;

		try {
			if (CommonUtil.isBlank(userId)) {
				result = sysUserDao.delete(userIds);
			} else {
				result = sysUserDao.delete(userId);
			}

			if (result <= 0) {
				throw new FrameworkException("E801", getMessage("E801", paramEntity));
			}

			paramEntity.setSuccess(true);
			paramEntity.setMessage("I801", getMessage("I801", paramEntity));
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}

		return paramEntity;
	}

	public ParamEntity exeExport(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		ExportHelper exportHelper;
		String dataRange = requestDataSet.getValue("dataRange");
		HttpSession session = paramEntity.getSession();
		String langCode = (String)session.getAttribute("langCode");

		try {
			String pageTitle = "User List";
			String fileName = "UserList";
			String columnHeader[] = {"user_id", "user_name", "login_id", "org_id", "auth_group_name", "user_type", "user_status", "email", "is_active", "update_date"};
			String fileHeader[] = {"User Id", "User Name", "Login Id", "Organisation Id", "Auth Group Name", "User Type", "User Status", "Email", "Is Active", "Update Date"};

			exportHelper = ExportUtil.getExportHelper(requestDataSet.getValue("fileType"));
			exportHelper.setPageTitle(pageTitle);
			exportHelper.setColumnHeader(columnHeader);
			exportHelper.setFileHeader(fileHeader);
			exportHelper.setFileName(fileName);
			exportHelper.setPdfWidth(2000);

			queryAdvisor.setObject("langCode", langCode);
			queryAdvisor.setRequestDataSet(requestDataSet);
			if (CommonUtil.containsIgnoreCase(dataRange, "all"))
				queryAdvisor.setPagination(false);
			else {
				queryAdvisor.setPagination(true);
			}

			exportHelper.setSourceDataSet(sysUserDao.getUserDataSetBySearchCriteria(queryAdvisor));

			paramEntity.setSuccess(true);
			paramEntity.setFileToExport(exportHelper.createFile());
			paramEntity.setFileNameToExport(exportHelper.getFileName());
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}

		return paramEntity;
	}

	private void setAuthorityGroup(ParamEntity paramEntity) throws Exception {
		QueryAdvisor qaAuth = paramEntity.getQueryAdvisor();
		qaAuth.addOrderByClause("group_id");
		paramEntity.setObject("authGroupDataSet", sysAuthGroupDao.getAllAuthGroupDataSet(qaAuth));
	}
}