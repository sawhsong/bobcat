/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_FINANCE - Financial Loan Related Data Entry (Repayment / Borrowing / Lending)
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrFinance;

import project.common.extend.BaseHDao;
import project.common.module.commoncode.CommonCodeManager;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.ConfigUtil;

public class UsrFinanceHDaoImpl extends BaseHDao implements UsrFinanceDao {
	public DataSet getRepaymentSummaryDataSet(QueryAdvisor queryAdvisor) throws Exception {
		return selectAsDataSet(queryAdvisor, "query.UsrFinance.getRepaymentSummaryDataSet");
	}

	public DataSet getBorrowingSummaryDataSet(QueryAdvisor queryAdvisor) throws Exception {
		return selectAsDataSet(queryAdvisor, "query.UsrFinance.getBorrowingSummaryDataSet");
	}

	public DataSet getLendingSummaryDataSet(QueryAdvisor queryAdvisor) throws Exception {
		return selectAsDataSet(queryAdvisor, "query.UsrFinance.getLendingSummaryDataSet");
	}

	public DataSet getRepaymentDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String orgId = (String)queryAdvisor.getObject("orgId");
		String financialYear = requestDataSet.getValue("financialYear");
		String quarterName = requestDataSet.getValue("quarterName");
		String repaymentType = requestDataSet.getValue("repaymentType");
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String langCode = (String)queryAdvisor.getObject("langCode");

		queryAdvisor.addAutoFillCriteria(repaymentType, "srt.repayment_type = '"+repaymentType+"'");
		queryAdvisor.addVariable("orgId", orgId);
		queryAdvisor.addVariable("financialYear", financialYear);
		queryAdvisor.addVariable("quarterName", quarterName);
		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addVariable("financeEntryType", CommonCodeManager.getCodeByConstants("FINANCE_ENTRY_TYPE_REP"));
		queryAdvisor.addOrderByClause("ufn.finance_date desc, srt.sort_order");

		return selectAsDataSet(queryAdvisor, "query.UsrFinance.getRepaymentDataSetByCriteria");
	}

	public DataSet getBorrowingDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String orgId = (String)queryAdvisor.getObject("orgId");
		String financialYear = requestDataSet.getValue("financialYear");
		String quarterName = requestDataSet.getValue("quarterName");
		String borrowingType = requestDataSet.getValue("borrowingType");
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String langCode = (String)queryAdvisor.getObject("langCode");

		queryAdvisor.addAutoFillCriteria(borrowingType, "sbt.borrowing_type = '"+borrowingType+"'");
		queryAdvisor.addVariable("orgId", orgId);
		queryAdvisor.addVariable("financialYear", financialYear);
		queryAdvisor.addVariable("quarterName", quarterName);
		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addVariable("financeEntryType", CommonCodeManager.getCodeByConstants("FINANCE_ENTRY_TYPE_BOR"));
		queryAdvisor.addOrderByClause("ufn.finance_date desc, sbt.sort_order");

		return selectAsDataSet(queryAdvisor, "query.UsrFinance.getBorrowingDataSetByCriteria");
	}

	public DataSet getLendingDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String orgId = (String)queryAdvisor.getObject("orgId");
		String financialYear = requestDataSet.getValue("financialYear");
		String quarterName = requestDataSet.getValue("quarterName");
		String lendingType = requestDataSet.getValue("lendingType");
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String langCode = (String)queryAdvisor.getObject("langCode");

		queryAdvisor.addAutoFillCriteria(lendingType, "slt.lending_type = '"+lendingType+"'");
		queryAdvisor.addVariable("orgId", orgId);
		queryAdvisor.addVariable("financialYear", financialYear);
		queryAdvisor.addVariable("quarterName", quarterName);
		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addVariable("financeEntryType", CommonCodeManager.getCodeByConstants("FINANCE_ENTRY_TYPE_LEN"));
		queryAdvisor.addOrderByClause("ufn.finance_date desc, slt.sort_order");

		return selectAsDataSet(queryAdvisor, "query.UsrFinance.getLendingDataSetByCriteria");
	}
}