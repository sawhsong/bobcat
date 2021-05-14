/**************************************************************************************************
 * project
 * Description - Cce0208 - Cash Expenses
 *	- Generated by Source Generator
 *************************************************************************************************/
package project.app.cce.cce0208;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseBiz;
import project.conf.resource.ormapper.dao.UsrCashExpense.UsrCashExpenseDao;
import project.conf.resource.ormapper.dto.oracle.UsrCashExpense;
import zebra.data.DataSet;
import zebra.data.ParamEntity;
import zebra.data.QueryAdvisor;
import zebra.exception.FrameworkException;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class Cce0208BizImpl extends BaseBiz implements Cce0208Biz {
	@Autowired
	private UsrCashExpenseDao usrCashExpenseDao;

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
		HttpSession session = paramEntity.getSession();
		String userId = CommonUtil.nvl((String)session.getAttribute("UserIdForAdminTool"), (String)session.getAttribute("UserId"));
		String financialYear = requestDataSet.getValue("financialYear");
		String quarterName = requestDataSet.getValue("quarterName");
		String fromDate = requestDataSet.getValue("fromDate");
		String toDate = requestDataSet.getValue("toDate");

		try {
			queryAdvisor.setObject("userId", userId);
			queryAdvisor.setObject("financialYear", financialYear);
			queryAdvisor.setObject("quarterName", quarterName);
			queryAdvisor.setObject("fromDate", fromDate);
			queryAdvisor.setObject("toDate", toDate);
			queryAdvisor.setPagination(true);

			paramEntity.setAjaxResponseDataSet(usrCashExpenseDao.getDataSetBySearchCriteria(queryAdvisor));
			paramEntity.setTotalResultRows(queryAdvisor.getTotalResultRows());
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getEdit(ParamEntity paramEntity) throws Exception {
		try {
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity doSave(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		String cashExpenseId = requestDataSet.getValue("cashExpenseId");
		HttpSession session = paramEntity.getSession();
		String userId = CommonUtil.nvl((String)session.getAttribute("UserIdForAdminTool"), (String)session.getAttribute("UserId"));
		UsrCashExpense usrCashExpense = new UsrCashExpense();
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String mainCategory = "0200";
		int result = -1;

		try {
			usrCashExpense.setUserId(userId);
			usrCashExpense.setProcDate(CommonUtil.toDate(requestDataSet.getValue("procDate"), dateFormat));
			usrCashExpense.setMainCategory(mainCategory);
			usrCashExpense.setSubCategory(requestDataSet.getValue("category"));
			usrCashExpense.setProcAmt(CommonUtil.toDouble(requestDataSet.getValue("grossAmt")));
			usrCashExpense.setGstAmt(CommonUtil.toDouble(requestDataSet.getValue("gstAmt")));
			usrCashExpense.setNetAmt(CommonUtil.toDouble(requestDataSet.getValue("netAmt")));
			usrCashExpense.setProcDescription(requestDataSet.getValue("procDescription"));

			if (CommonUtil.isBlank(cashExpenseId)) {
				usrCashExpense.setCashExpenseId(CommonUtil.uid());
				usrCashExpense.setInsertUserId(userId);
				usrCashExpense.setInsertDate(CommonUtil.toDate(CommonUtil.getSysdate()));

				result = usrCashExpenseDao.insert(usrCashExpense);
			} else {
				usrCashExpense.setCashExpenseId(cashExpenseId);
				usrCashExpense.setUpdateUserId(userId);
				usrCashExpense.setUpdateDate(CommonUtil.toDate(CommonUtil.getSysdate()));
				usrCashExpense.addUpdateColumnFromField();

				result = usrCashExpenseDao.update(cashExpenseId, usrCashExpense);
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

	public ParamEntity getDataForEdit(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		String cashExpenseId = requestDataSet.getValue("cashExpenseId");

		try {
			paramEntity.setAjaxResponseDataSet(usrCashExpenseDao.getDataSetByCashExpenseId(cashExpenseId));
			paramEntity.setTotalResultRows(queryAdvisor.getTotalResultRows());
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity doDelete(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		String chkForDel = requestDataSet.getValue("chkForDel");
		String cashExpenseId = requestDataSet.getValue("cashExpenseId");
		String cashExpenseIds[] = CommonUtil.splitWithTrim(chkForDel, ConfigUtil.getProperty("delimiter.record"));
		int result = 0;

		try {
			if (CommonUtil.isBlank(cashExpenseId)) {
				result = usrCashExpenseDao.delete(cashExpenseIds);
			} else {
				result = usrCashExpenseDao.delete(cashExpenseId);
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
}