package project.app.sys.sys0404;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseBiz;
import project.common.module.datahelper.DataHelper;
import project.conf.resource.ormapper.dao.SysAuthGroup.SysAuthGroupDao;
import project.conf.resource.ormapper.dao.SysMenuAuthLink.SysMenuAuthLinkDao;
import project.conf.resource.ormapper.dao.SysUser.SysUserDao;
import project.conf.resource.ormapper.dto.oracle.SysAuthGroup;
import zebra.data.DataSet;
import zebra.data.ParamEntity;
import zebra.data.QueryAdvisor;
import zebra.exception.FrameworkException;
import zebra.export.ExportHelper;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;
import zebra.util.ExportUtil;

public class Sys0404BizImpl extends BaseBiz implements Sys0404Biz {
	@Autowired
	private SysAuthGroupDao sysAuthGroupDao;
	@Autowired
	private SysMenuAuthLinkDao sysMenuAuthLinkDao;
	@Autowired
	private SysUserDao sysUserDao;

	public ParamEntity getDefault(ParamEntity paramEntity) throws Exception {
		try {
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getList(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();

		try {
			queryAdvisor.setRequestDataSet(requestDataSet);
			queryAdvisor.setPagination(true);

			paramEntity.setAjaxResponseDataSet(sysAuthGroupDao.getAuthGroupDataSetBySearchCriteria(queryAdvisor));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getDetail(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		String groupId = requestDataSet.getValue("groupId");
		SysAuthGroup sysAuthGroup = new SysAuthGroup();

		try {
			sysAuthGroup = sysAuthGroupDao.getAuthGroupByGroupId(groupId);
			sysAuthGroup.setInsertUserName(DataHelper.getUserNameById(sysAuthGroup.getInsertUserId()));
			sysAuthGroup.setUpdateUserName(DataHelper.getUserNameById(sysAuthGroup.getUpdateUserId()));

			paramEntity.setObject("sysAuthGroup", sysAuthGroup);
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getInsert(ParamEntity paramEntity) throws Exception {
		try {
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getUpdate(ParamEntity paramEntity) throws Exception {
		try {
			paramEntity = getDetail(paramEntity);
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity exeInsert(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		HttpSession session = paramEntity.getSession();
		String groupId = CommonUtil.uid();
		int result = -1;
		SysAuthGroup sysAuthGroup = new SysAuthGroup();

		try {
			sysAuthGroup.setGroupId(groupId);
			sysAuthGroup.setGroupName(requestDataSet.getValue("groupName"));
			sysAuthGroup.setIsActive(requestDataSet.getValue("isActive"));
			sysAuthGroup.setDescription(requestDataSet.getValue("description"));
			sysAuthGroup.setInsertUserId((String)session.getAttribute("UserId"));
			sysAuthGroup.setInsertDate(CommonUtil.toDate(CommonUtil.getSysdate()));

			result = sysAuthGroupDao.insert(sysAuthGroup);
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

	public ParamEntity exeUpdate(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		HttpSession session = paramEntity.getSession();
		String groupId = requestDataSet.getValue("groupId");
		int result = -1;
		SysAuthGroup sysAuthGroup = new SysAuthGroup();

		try {
			sysAuthGroup = sysAuthGroupDao.getAuthGroupByGroupId(groupId);

			sysAuthGroup.setGroupName(requestDataSet.getValue("groupName"));
			sysAuthGroup.setDescription(requestDataSet.getValue("description"));
			sysAuthGroup.setIsActive(requestDataSet.getValue("isActive"));
			sysAuthGroup.setUpdateUserId((String)session.getAttribute("UserId"));
			sysAuthGroup.setUpdateDate(CommonUtil.toDate(CommonUtil.getSysdate()));

			result = sysAuthGroupDao.update(groupId, sysAuthGroup);
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

	public ParamEntity exeDelete(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		String groupId = requestDataSet.getValue("groupId");
		String chkForDel = requestDataSet.getValue("chkForDel");
		String groupIds[] = CommonUtil.splitWithTrim(chkForDel, ConfigUtil.getProperty("delimiter.record"));
		int result = -1;

		try {
			if (CommonUtil.isBlank(groupId)) {
				result = sysAuthGroupDao.delete(groupIds);
				result += sysMenuAuthLinkDao.deleteByAuthGroupIds(groupIds);
				result += sysUserDao.updateAuthGroupIdByAuthGroupIds(groupIds, "Z");
			} else {
				result = sysAuthGroupDao.delete(groupId);
				result += sysMenuAuthLinkDao.deleteByAuthGroupId(groupId);
				result += sysUserDao.updateAuthGroupIdByAuthGroupId(groupId, "Z");
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

		try {
			String pageTitle = "Authority Group List";
			String fileName = "AuthorityGroupList";

			exportHelper = ExportUtil.getExportHelper(requestDataSet.getValue("fileType"));
			exportHelper.setPageTitle(pageTitle);
			exportHelper.setFileName(fileName);
			exportHelper.setPdfWidth(1000);

			queryAdvisor.setRequestDataSet(requestDataSet);
			if (CommonUtil.containsIgnoreCase(dataRange, "all"))
				queryAdvisor.setPagination(false);
			else {
				queryAdvisor.setPagination(true);
			}

			exportHelper.setSourceDataSet(sysAuthGroupDao.getAuthGroupDataSetBySearchCriteria(queryAdvisor));

			paramEntity.setSuccess(true);
			paramEntity.setFileToExport(exportHelper.createFile());
			paramEntity.setFileNameToExport(exportHelper.getFileName());
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}

		return paramEntity;
	}
}