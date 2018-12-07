/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_EXPENSE - Expense Entry (Expenditure data entry for all organisation category)
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrExpense;

import project.common.extend.BaseHDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.ConfigUtil;

public class UsrExpenseHDaoImpl extends BaseHDao implements UsrExpenseDao {
	public DataSet getExpenseSummaryDataSet(QueryAdvisor queryAdvisor) throws Exception {
		return selectAsDataSet(queryAdvisor, "query.UsrExpense.getExpenseSummaryDataSet");
	}

	public DataSet getExpenseDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String orgId = (String)queryAdvisor.getObject("orgId");
		String financialYear = requestDataSet.getValue("financialYear");
		String quarterName = requestDataSet.getValue("quarterName");
		String expenseMainType = requestDataSet.getValue("expenseMainType");
		String expenseSubType = requestDataSet.getValue("expenseSubType");
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String langCode = (String)queryAdvisor.getObject("langCode");

		queryAdvisor.addAutoFillCriteria(expenseMainType, "ext.parent_expense_type = '"+expenseMainType+"'");
		queryAdvisor.addAutoFillCriteria(expenseSubType, "ext.expense_type = '"+expenseSubType+"'");
		queryAdvisor.addVariable("orgId", orgId);
		queryAdvisor.addVariable("financialYear", financialYear);
		queryAdvisor.addVariable("quarterName", quarterName);
		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addOrderByClause("exp.expense_date desc, ext.sort_order");

		return selectAsDataSet(queryAdvisor, "query.UsrExpense.getExpenseDataSetByCriteria");
	}

	public DataSet getExpensePerformanceDataSet(String orgCategory, String orgId, String financialYear, String quarterName) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();

		queryAdvisor.addVariable("orgCategory", orgCategory);
		queryAdvisor.addVariable("orgId", orgId);
		queryAdvisor.addVariable("financialYear", financialYear);
		queryAdvisor.addVariable("quarterName", quarterName);

		return selectAsDataSet(queryAdvisor, "query.UsrExpense.getExpensePerformanceDataSet");
	}
}