package project.common.module.admintoolentrysummary;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseBiz;
import project.conf.resource.ormapper.dao.UsrAsset.UsrAssetDao;
import project.conf.resource.ormapper.dao.UsrEmployee.UsrEmployeeDao;
import project.conf.resource.ormapper.dao.UsrExpense.UsrExpenseDao;
import project.conf.resource.ormapper.dao.UsrFinance.UsrFinanceDao;
import project.conf.resource.ormapper.dao.UsrIncome.UsrIncomeDao;
import zebra.data.DataSet;
import zebra.data.ParamEntity;
import zebra.data.QueryAdvisor;
import zebra.exception.FrameworkException;
import zebra.util.CommonUtil;

public class EntrySummaryBizImpl extends BaseBiz implements EntrySummaryBiz {
	@Autowired
	private UsrIncomeDao usrIncomeDao;
	@Autowired
	private UsrExpenseDao usrExpenseDao;
	@Autowired
	private UsrAssetDao usrAssetDao;
	@Autowired
	private UsrFinanceDao usrFinanceDao;
	@Autowired
	private UsrEmployeeDao usrEmployeeDao;

	public ParamEntity getIncomeSummary(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		HttpSession session = paramEntity.getSession();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		String orgId = CommonUtil.nvl((String)session.getAttribute("OrgIdForAdminTool"), (String)session.getAttribute("OrgId"));
		String orgCategory = CommonUtil.nvl((String)session.getAttribute("OrgCategoryForAdminTool"), (String)session.getAttribute("OrgCategory"));
		String financialYear = requestDataSet.getValue("financialYear");
		String quarterName = requestDataSet.getValue("quarterName");

		try {
			queryAdvisor.setObject("orgCategory", orgCategory);
			queryAdvisor.addVariable("orgId", orgId);
			queryAdvisor.addVariable("financialYear", financialYear);
			queryAdvisor.addVariable("quarterName", quarterName);
			queryAdvisor.addVariable("orgCategory", orgCategory);
			queryAdvisor.addVariable("langCode", (String)session.getAttribute("langCode"));

			paramEntity.setAjaxResponseDataSet(usrIncomeDao.getIncomeSummaryDataSet(queryAdvisor));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getExpenseSummary(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		HttpSession session = paramEntity.getSession();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		String orgId = CommonUtil.nvl((String)session.getAttribute("OrgIdForAdminTool"), (String)session.getAttribute("OrgId"));
		String orgCategory = CommonUtil.nvl((String)session.getAttribute("OrgCategoryForAdminTool"), (String)session.getAttribute("OrgCategory"));
		String financialYear = requestDataSet.getValue("financialYear");
		String quarterName = requestDataSet.getValue("quarterName");

		try {
			queryAdvisor.addVariable("orgId", orgId);
			queryAdvisor.addVariable("financialYear", financialYear);
			queryAdvisor.addVariable("quarterName", quarterName);
			queryAdvisor.addVariable("orgCategory", orgCategory);
			queryAdvisor.addVariable("langCode", (String)session.getAttribute("langCode"));

			paramEntity.setAjaxResponseDataSet(usrExpenseDao.getExpenseSummaryDataSet(queryAdvisor));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getAssetSummary(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		HttpSession session = paramEntity.getSession();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		String orgId = CommonUtil.nvl((String)session.getAttribute("OrgIdForAdminTool"), (String)session.getAttribute("OrgId"));
		String orgCategory = CommonUtil.nvl((String)session.getAttribute("OrgCategoryForAdminTool"), (String)session.getAttribute("OrgCategory"));
		String financialYear = requestDataSet.getValue("financialYear");
		String quarterName = requestDataSet.getValue("quarterName");

		try {
			queryAdvisor.addVariable("orgId", orgId);
			queryAdvisor.addVariable("financialYear", financialYear);
			queryAdvisor.addVariable("quarterName", quarterName);
			queryAdvisor.addVariable("orgCategory", orgCategory);
			queryAdvisor.addVariable("langCode", (String)session.getAttribute("langCode"));

			paramEntity.setAjaxResponseDataSet(usrAssetDao.getAssetSummaryDataSet(queryAdvisor));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getRepaymentSummary(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		HttpSession session = paramEntity.getSession();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		String orgId = CommonUtil.nvl((String)session.getAttribute("OrgIdForAdminTool"), (String)session.getAttribute("OrgId"));
		String orgCategory = CommonUtil.nvl((String)session.getAttribute("OrgCategoryForAdminTool"), (String)session.getAttribute("OrgCategory"));
		String financialYear = requestDataSet.getValue("financialYear");
		String quarterName = requestDataSet.getValue("quarterName");

		try {
			queryAdvisor.addVariable("orgId", orgId);
			queryAdvisor.addVariable("financialYear", financialYear);
			queryAdvisor.addVariable("quarterName", quarterName);
			queryAdvisor.addVariable("orgCategory", orgCategory);
			queryAdvisor.addVariable("langCode", (String)session.getAttribute("langCode"));

			paramEntity.setAjaxResponseDataSet(usrFinanceDao.getRepaymentSummaryDataSet(queryAdvisor));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getBorrowingSummary(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		HttpSession session = paramEntity.getSession();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		String orgId = CommonUtil.nvl((String)session.getAttribute("OrgIdForAdminTool"), (String)session.getAttribute("OrgId"));
		String orgCategory = CommonUtil.nvl((String)session.getAttribute("OrgCategoryForAdminTool"), (String)session.getAttribute("OrgCategory"));
		String financialYear = requestDataSet.getValue("financialYear");
		String quarterName = requestDataSet.getValue("quarterName");

		try {
			queryAdvisor.addVariable("orgId", orgId);
			queryAdvisor.addVariable("financialYear", financialYear);
			queryAdvisor.addVariable("quarterName", quarterName);
			queryAdvisor.addVariable("orgCategory", orgCategory);
			queryAdvisor.addVariable("langCode", (String)session.getAttribute("langCode"));

			paramEntity.setAjaxResponseDataSet(usrFinanceDao.getBorrowingSummaryDataSet(queryAdvisor));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getLendingSummary(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		HttpSession session = paramEntity.getSession();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		String orgId = CommonUtil.nvl((String)session.getAttribute("OrgIdForAdminTool"), (String)session.getAttribute("OrgId"));
		String orgCategory = CommonUtil.nvl((String)session.getAttribute("OrgCategoryForAdminTool"), (String)session.getAttribute("OrgCategory"));
		String financialYear = requestDataSet.getValue("financialYear");
		String quarterName = requestDataSet.getValue("quarterName");

		try {
			queryAdvisor.addVariable("orgId", orgId);
			queryAdvisor.addVariable("financialYear", financialYear);
			queryAdvisor.addVariable("quarterName", quarterName);
			queryAdvisor.addVariable("orgCategory", orgCategory);
			queryAdvisor.addVariable("langCode", (String)session.getAttribute("langCode"));

			paramEntity.setAjaxResponseDataSet(usrFinanceDao.getLendingSummaryDataSet(queryAdvisor));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getEmployeeSummary(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		HttpSession session = paramEntity.getSession();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		String orgId = CommonUtil.nvl((String)session.getAttribute("OrgIdForAdminTool"), (String)session.getAttribute("OrgId"));
		String orgCategory = CommonUtil.nvl((String)session.getAttribute("OrgCategoryForAdminTool"), (String)session.getAttribute("OrgCategory"));
		String financialYear = requestDataSet.getValue("financialYear");
		String quarterName = requestDataSet.getValue("quarterName");

		try {
			queryAdvisor.addVariable("orgId", orgId);
			queryAdvisor.addVariable("financialYear", financialYear);
			queryAdvisor.addVariable("quarterName", quarterName);
			queryAdvisor.addVariable("orgCategory", orgCategory);
			queryAdvisor.addVariable("langCode", (String)session.getAttribute("langCode"));

			paramEntity.setAjaxResponseDataSet(usrEmployeeDao.getEmployeeSummaryDataSet(queryAdvisor));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}
}