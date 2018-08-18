package project.app.sys.sys0402;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseBiz;
import project.common.module.datahelper.DataHelper;
import project.conf.resource.ormapper.dao.SysMenu.SysMenuDao;
import project.conf.resource.ormapper.dto.oracle.SysMenu;
import zebra.data.DataSet;
import zebra.data.ParamEntity;
import zebra.data.QueryAdvisor;
import zebra.exception.FrameworkException;
import zebra.export.ExportHelper;
import zebra.util.BeanHelper;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;
import zebra.util.ExportUtil;

public class Sys0402BizImpl extends BaseBiz implements Sys0402Biz {
	@Autowired
	private SysMenuDao sysMenuDao;

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
		DataSet resultDataSet;
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();

		try {
			queryAdvisor.setRequestDataSet(requestDataSet);
			queryAdvisor.setPagination(false);

			resultDataSet = sysMenuDao.getMenuDataSetBySearchCriteria(queryAdvisor);
			resultDataSet.addColumn("DELETABLE", "true");
			for (int i=0; i<resultDataSet.getRowCnt(); i++) {
				String menuId = resultDataSet.getValue(i, "MENU_ID");
				String parentMenuId = resultDataSet.getValue(i, "PARENT_MENU_ID");
				String root = resultDataSet.getValue(i, "ROOT");
				boolean hasAction = BeanHelper.containsBean(CommonUtil.toCamelCaseStartLowerCase(menuId) + "Action");

				if (hasAction) {
					for (int j=0; j<resultDataSet.getRowCnt(); j++) {
						String thisMenuId = resultDataSet.getValue(j, "MENU_ID");
						if ((CommonUtil.equals(thisMenuId, menuId)) || (CommonUtil.equals(thisMenuId, parentMenuId)) || (CommonUtil.equals(thisMenuId, root))) {
							resultDataSet.setValue(j, "DELETABLE", "false");
						}
					}
				}
			}

			paramEntity.setAjaxResponseDataSet(resultDataSet);
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getDetail(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		String paramValue = requestDataSet.getValue("paramValue");
		String delimiter = "_";
		String menuLevel = CommonUtil.split(paramValue, delimiter)[0];
		String menuPath = CommonUtil.split(paramValue, delimiter)[1];
		String menuId = CommonUtil.split(paramValue, delimiter)[2];
		String deletable = CommonUtil.split(paramValue, delimiter)[3];
		SysMenu sysMenu = new SysMenu();

		try {
			sysMenu = sysMenuDao.getMenuByMenuId(menuId);
			sysMenu.setInsertUserName(DataHelper.getUserNameById(sysMenu.getInsertUserId()));
			sysMenu.setUpdateUserName(DataHelper.getUserNameById(sysMenu.getUpdateUserId()));

			paramEntity.setObject("sysMenu", sysMenu);
			paramEntity.setObject("menuLevel", menuLevel);
			paramEntity.setObject("menuPath", menuPath);
			paramEntity.setObject("deletable", deletable);
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

	public ParamEntity getUpdateSortOrder(ParamEntity paramEntity) throws Exception {
		try {
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity exeInsert(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		HttpSession session = paramEntity.getSession();
		String menuId = requestDataSet.getValue("menuId");
		String menuLevel = requestDataSet.getValue("menuLevel");
		String level1MenuId = requestDataSet.getValue("level1");
		String level2MenuId = requestDataSet.getValue("level2");
		int result = -1;
		SysMenu sysMenu = new SysMenu();

		try {
			sysMenu = sysMenuDao.getMenuByMenuId(menuId);
			if (CommonUtil.isNotBlank(sysMenu.getMenuId())) {
				throw new FrameworkException("E910", getMessage("E910", paramEntity));
			}

			sysMenu = new SysMenu();
			sysMenu.setMenuId(CommonUtil.upperCase(requestDataSet.getValue("menuId")));
			if (CommonUtil.equalsIgnoreCase(menuLevel, "1")) {
				sysMenu.setParentMenuId("");
				sysMenu.setMenuIcon(CommonUtil.upperCase(requestDataSet.getValue("menuId")));
			} else if (CommonUtil.equalsIgnoreCase(menuLevel, "2")) {
				sysMenu.setParentMenuId(CommonUtil.upperCase(level1MenuId));
				sysMenu.setMenuIcon(CommonUtil.upperCase(requestDataSet.getValue("menuId")));
			} else if (CommonUtil.equalsIgnoreCase(menuLevel, "3")) {
				sysMenu.setParentMenuId(CommonUtil.upperCase(level2MenuId));
				sysMenu.setMenuIcon("");
			}
			sysMenu.setMenuNameEn(requestDataSet.getValue("menuNameEn"));
			sysMenu.setMenuNameKo(requestDataSet.getValue("menuNameKo"));
			sysMenu.setMenuUrl(requestDataSet.getValue("menuUrl"));
			sysMenu.setSortOrder(requestDataSet.getValue("sortOrder"));
			sysMenu.setDescription(requestDataSet.getValue("description"));
			sysMenu.setIsActive(requestDataSet.getValue("isActive"));
			sysMenu.setInsertUserId((String)session.getAttribute("UserId"));
			sysMenu.setInsertDate(CommonUtil.toDate(CommonUtil.getSysdate()));

			result = sysMenuDao.insert(sysMenu);
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
		String menuId = requestDataSet.getValue("menuId");
		int result = -1;
		SysMenu sysMenu = new SysMenu();

		try {
			sysMenu = sysMenuDao.getMenuByMenuId(menuId);

			sysMenu.setMenuUrl(CommonUtil.nvl(requestDataSet.getValue("menuUrl"), "#"));
			sysMenu.setSortOrder(requestDataSet.getValue("sortOrder"));
			sysMenu.setIsActive(requestDataSet.getValue("isActive"));
			sysMenu.setMenuNameEn(requestDataSet.getValue("menuNameEn"));
			sysMenu.setMenuNameKo(requestDataSet.getValue("menuNameKo"));
			sysMenu.setDescription(requestDataSet.getValue("description"));
			sysMenu.setUpdateUserId((String)session.getAttribute("UserId"));
			sysMenu.setUpdateDate(CommonUtil.toDate(CommonUtil.getSysdate()));

			result = sysMenuDao.update(menuId, sysMenu);
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
		String menuId = requestDataSet.getValue("menuId");
		String chkForDel = requestDataSet.getValue("chkForDel");
		String menuIds[] = CommonUtil.splitWithTrim(chkForDel, ConfigUtil.getProperty("delimiter.record"));
		int result = -1;

		try {
			if (CommonUtil.isBlank(menuId)) {
				result = sysMenuDao.delete(menuIds);
			} else {
				result = sysMenuDao.delete(menuId);
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

	public ParamEntity exeUpdateSortOrder(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		HttpSession session = paramEntity.getSession();
		String delimiter = ConfigUtil.getProperty("delimiter.data");
		int dataLength = CommonUtil.toInt(requestDataSet.getValue("dataLength"));
		int result = 0;

		try {
			for (int i=0; i<dataLength; i++) {
				SysMenu sysMenu = new SysMenu();
				String menuId = requestDataSet.getValue("menuId"+delimiter+i);

				queryAdvisor.resetAll();

				sysMenu.addUpdateColumn("sort_order", requestDataSet.getValue("sortOrder" + delimiter + i));
				sysMenu.addUpdateColumn("update_user_id", (String)session.getAttribute("UserId"));
				sysMenu.addUpdateColumn("update_date", CommonUtil.getSysdate(), "date");

				result += sysMenuDao.updateSortOrder(menuId, sysMenu);
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
			String pageTitle = "Menu List";
			String fileName = "MenuList";

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

			exportHelper.setSourceDataSet(sysMenuDao.getMenuDataSetBySearchCriteria(queryAdvisor));

			paramEntity.setSuccess(true);
			paramEntity.setFileToExport(exportHelper.createFile());
			paramEntity.setFileNameToExport(exportHelper.getFileName());
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}

		return paramEntity;
	}
}