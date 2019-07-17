/**************************************************************************************************
 * project
 * Description - Sba0406 - Expenditure Type
 *	- Generated by Source Generator
 *************************************************************************************************/
package project.app.sba.sba0406;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import zebra.data.DataSet;
import zebra.data.ParamEntity;
import zebra.data.QueryAdvisor;
import zebra.exception.FrameworkException;
import zebra.export.ExportHelper;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;
import zebra.util.ExportUtil;

import project.common.extend.BaseBiz;
import project.common.module.commoncode.CommonCodeManager;
import project.conf.resource.ormapper.dao.SysBoard.SysBoardDao;
import project.conf.resource.ormapper.dao.SysExpenseType.SysExpenseTypeDao;
import project.conf.resource.ormapper.dto.oracle.SysBoard;
import project.conf.resource.ormapper.dto.oracle.SysExpenseType;

public class Sba0406BizImpl extends BaseBiz implements Sba0406Biz {
	@Autowired
	private SysBoardDao sysBoardDao;
	@Autowired
	private SysExpenseTypeDao sysExpenseTypeDao;

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

			paramEntity.setAjaxResponseDataSet(sysExpenseTypeDao.getExpenseTypeDataSetByCriteria(queryAdvisor));
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
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		String path = requestDataSet.getValue("path");
		String keyValues[] = CommonUtil.split(path, "/");
		String expenseTypeId = "", orgCategory = "", expenseTypeCode = "";

		try {
			if (keyValues.length == 3) {
				expenseTypeId = keyValues[0];
				orgCategory = keyValues[1];
				expenseTypeCode = keyValues[2];
			} else {
				expenseTypeId = keyValues[0];
				orgCategory = keyValues[1];
				expenseTypeCode = keyValues[3];
			}
			paramEntity.setObject("sysExpenseType", sysExpenseTypeDao.getExpenseTypeByKeys(expenseTypeId, orgCategory, expenseTypeCode));

			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity exeInsert(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		HttpSession session = paramEntity.getSession();
		String uid = CommonUtil.uid();
		String loggedInUserId = (String)session.getAttribute("UserId");
		String orgCategory = requestDataSet.getValue("orgCategory");
		String parentExpenseType = requestDataSet.getValue("mainExpenseType");
		String expenseType = requestDataSet.getValue("expenseType");
		SysExpenseType sysExpenseType = new SysExpenseType();
		int result = -1;

		try {
			sysExpenseType.setExpenseTypeId(uid);
			sysExpenseType.setOrgCategory(orgCategory);
			if (CommonUtil.isBlank(expenseType)) {
				sysExpenseType.setExpenseType(parentExpenseType);
			} else {
				sysExpenseType.setParentExpenseType(parentExpenseType);
				sysExpenseType.setExpenseType(expenseType);
			}
			sysExpenseType.setDescription(requestDataSet.getValue("description"));
			sysExpenseType.setIsApplyGst(requestDataSet.getValue("isApplyGst"));
			sysExpenseType.setGstPercentage(CommonUtil.toDouble(requestDataSet.getValue("gstPercentage")));
			sysExpenseType.setAccountCode(requestDataSet.getValue("accountCode"));
			sysExpenseType.setSortOrder(getSortOrder(orgCategory, parentExpenseType, expenseType));
			sysExpenseType.setInsertUserId(loggedInUserId);
			sysExpenseType.setInsertDate(CommonUtil.toDate(CommonUtil.getSysdate()));

			result = sysExpenseTypeDao.insert(sysExpenseType);
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
		String expenseTypeId = requestDataSet.getValue("expenseTypeId");
		String orgCategory = requestDataSet.getValue("orgCategory");
		String expenseTypeCode = requestDataSet.getValue("expenseType");
		String loggedInUserId = (String)session.getAttribute("UserId");
		SysExpenseType sysExpenseType;
		int result = 0;

		try {
			sysExpenseType = sysExpenseTypeDao.getExpenseTypeByKeys(expenseTypeId, orgCategory, expenseTypeCode);

			sysExpenseType.setParentExpenseType(requestDataSet.getValue("mainExpenseType"));
			sysExpenseType.setDescription(requestDataSet.getValue("description"));
			sysExpenseType.setAccountCode(requestDataSet.getValue("accountCode"));
			sysExpenseType.setIsApplyGst(requestDataSet.getValue("isApplyGst"));
			sysExpenseType.setGstPercentage(CommonUtil.toDouble(requestDataSet.getValue("gstPercentage")));
			sysExpenseType.setUpdateUserId(loggedInUserId);
			sysExpenseType.setUpdateDate(CommonUtil.toDate(CommonUtil.getSysdate()));

			result = sysExpenseTypeDao.updateWithKey(sysExpenseType, expenseTypeId, expenseTypeCode);
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
		String articleId = requestDataSet.getValue("articleId");
		String chkForDel = requestDataSet.getValue("chkForDel");
		String articleIds[] = CommonUtil.splitWithTrim(chkForDel, ConfigUtil.getProperty("delimiter.record"));
		int result = 0;

		try {
			if (CommonUtil.isBlank(articleId)) {
				result = sysBoardDao.delete(articleIds);
			} else {
				result = sysBoardDao.delete(articleId);
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
		String columnHeader[];
		String pageTitle, fileName;
		String fileType = requestDataSet.getValue("fileType");
		String dataRange = requestDataSet.getValue("dataRange");

		try {
			pageTitle = "Board List";
			fileName = "BoardList";
			columnHeader = new String[]{"article_id", "writer_name", "writer_email", "article_subject", "created_date"};

			exportHelper = ExportUtil.getExportHelper(fileType);
			exportHelper.setPageTitle(pageTitle);
			exportHelper.setColumnHeader(columnHeader);
			exportHelper.setFileName(fileName);
			exportHelper.setPdfWidth(1000);

			queryAdvisor.setRequestDataSet(requestDataSet);
			if (CommonUtil.containsIgnoreCase(dataRange, "all"))
				queryAdvisor.setPagination(false);
			else {
				queryAdvisor.setPagination(true);
			}

			exportHelper.setSourceDataSet(sysBoardDao.getNoticeBoardDataSetByCriteria(queryAdvisor));

			paramEntity.setSuccess(true);
			paramEntity.setFileToExport(exportHelper.createFile());
			paramEntity.setFileNameToExport(exportHelper.getFileName());
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	private String getSortOrder(String orgCategory, String parentExpenseType, String expenseType) throws Exception {
		String ord1 = "", ord2 = "", ord3 = "";
		DataSet ds = new DataSet();

		if (CommonUtil.equals(orgCategory, "A")) {
			ord1 = "01";
		} else if (CommonUtil.equals(orgCategory, "B")) {
			ord1 = "02";
		} else if (CommonUtil.equals(orgCategory, "C")) {
			ord1 = "03";
		} else if (CommonUtil.equals(orgCategory, "D")) {
			ord1 = "04";
		}

//		ds = sysExpenseTypeDao.getMaxSortOrderDataSetByOrgCategoryAndType(orgCategory);
		ord2 = CommonUtil.toString(CommonUtil.toInt(CommonUtil.substring(ds.getValue(0, 0), 2, 4))+1);
		ord2 = CommonUtil.leftPad(ord2, 2, "0");

		return ord1+ord2+"00";
	}
}