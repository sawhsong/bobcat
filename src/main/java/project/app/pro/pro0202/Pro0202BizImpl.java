/**************************************************************************************************
 * project
 * Description - Pro0202 - Payroll Service
 *	- Generated by Source Generator
 *************************************************************************************************/
package project.app.pro.pro0202;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseBiz;
import project.conf.resource.ormapper.dao.UsrYearlyPayrollSummary.UsrYearlyPayrollSummaryDao;
import project.conf.resource.ormapper.dto.oracle.UsrYearlyPayrollSummary;
import zebra.data.DataSet;
import zebra.data.ParamEntity;
import zebra.data.QueryAdvisor;
import zebra.exception.FrameworkException;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class Pro0202BizImpl extends BaseBiz implements Pro0202Biz {
	@Autowired
	private UsrYearlyPayrollSummaryDao payrollSummaryDao;

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
		String orgId = CommonUtil.nvl((String)session.getAttribute("OrgIdForAdminTool"), (String)session.getAttribute("OrgId"));

		try {
			queryAdvisor.addVariable("orgId", orgId);
			queryAdvisor.addVariable("financialYear", requestDataSet.getValue("financialYear"));
			queryAdvisor.setPagination(false);

			paramEntity.setAjaxResponseDataSet(payrollSummaryDao.getDataSetBySearchCriteria(queryAdvisor));
			paramEntity.setTotalResultRows(queryAdvisor.getTotalResultRows());
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity doSave(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		DataSet dsExist;
		double totForCheck = 0;
		HttpSession session = paramEntity.getSession();
		String userId = CommonUtil.nvl((String)session.getAttribute("UserIdForAdminTool"), (String)session.getAttribute("UserId"));
		String orgId = CommonUtil.nvl((String)session.getAttribute("OrgIdForAdminTool"), (String)session.getAttribute("OrgId"));
		String financialYear = requestDataSet.getValue("financialYear");
		String dateTimeFormat = ConfigUtil.getProperty("format.default.dateTime");
		String insertedUserId = "";
		Date insertedDate = null;
		int result = -1;

		try {
			queryAdvisor.addVariable("orgId", orgId);
			queryAdvisor.addVariable("financialYear", financialYear);
			dsExist = payrollSummaryDao.getDataSetBySearchCriteria(queryAdvisor);

			totForCheck += CommonUtil.toDouble(dsExist.getValue(dsExist.getRowCnt()-1, "NUMBER_OF_EMPLOYEE"));
			totForCheck += CommonUtil.toDouble(dsExist.getValue(dsExist.getRowCnt()-1, "GROSS_PAY_AMT"));
			totForCheck += CommonUtil.toDouble(dsExist.getValue(dsExist.getRowCnt()-1, "TAX"));
			totForCheck += CommonUtil.toDouble(dsExist.getValue(dsExist.getRowCnt()-1, "NET_PAY_AMT"));
			totForCheck += CommonUtil.toDouble(dsExist.getValue(dsExist.getRowCnt()-1, "SUPER_AMT"));

			if (totForCheck > 0) {
				insertedDate = CommonUtil.toDate(dsExist.getValue(dsExist.getRowCnt()-1, "INSERT_DATE"), dateTimeFormat);
				insertedUserId = dsExist.getValue(dsExist.getRowCnt()-1, "INSERT_USER_ID");
			}

			payrollSummaryDao.deleteByKeys(orgId, financialYear);

			for (int i=0; i<12; i++) {
				UsrYearlyPayrollSummary payrollSummary = new UsrYearlyPayrollSummary();

				payrollSummary.setOrgId(orgId);
				payrollSummary.setFinancialYear(financialYear);
				payrollSummary.setPayrollMonth(requestDataSet.getValue("txtMonthNum_"+i));
				payrollSummary.setNumberOfEmployee(CommonUtil.toDouble(requestDataSet.getValue("txtNoOfEmployee_"+i)));
				payrollSummary.setGrossPayAmt(CommonUtil.toDouble(requestDataSet.getValue("txtGrossPayAmt_"+i)));
				payrollSummary.setTax(CommonUtil.toDouble(requestDataSet.getValue("txtTax_"+i)));
				payrollSummary.setNetPayAmt(CommonUtil.toDouble(requestDataSet.getValue("txtNetPayAmt_"+i)));
				payrollSummary.setSuperAmt(CommonUtil.toDouble(requestDataSet.getValue("txtSuperAmt_"+i)));
				if (insertedDate == null) {
					payrollSummary.setInsertUserId(userId);
					payrollSummary.setInsertDate(CommonUtil.getSysdateAsDate());
				} else {
					payrollSummary.setInsertUserId(insertedUserId);
					payrollSummary.setInsertDate(insertedDate);
					payrollSummary.setUpdateUserId(userId);
					payrollSummary.setUpdateDate(CommonUtil.getSysdateAsDate());
				}
				result += payrollSummaryDao.insert(payrollSummary);
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