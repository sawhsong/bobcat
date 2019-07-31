/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_EXPENSE - Expense Entry (Expenditure data entry for all organisation category)
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrExpense;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dto.oracle.UsrExpense;
import zebra.base.Dto;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.ConfigUtil;

public class UsrExpenseHDaoImpl extends BaseHDao implements UsrExpenseDao {
	public int insert(Dto dto) throws Exception {
		return insertWithSQLQuery(dto);
	}

	public int update(String expenseId, Dto dto) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("expense_id = '"+expenseId+"'");
		return updateWithSQLQuery(queryAdvisor, dto);
	}

	public int exeCompleteByExpenseIds(String expenseIds[]) throws Exception {
		QueryAdvisor queryAdvisor;
		UsrExpense usrExpense;
		int result = 0;

		for (int i=0; i<expenseIds.length; i++) {
			usrExpense = new UsrExpense();
			queryAdvisor = new QueryAdvisor();

			usrExpense.addUpdateColumn("is_completed", "Y");
			queryAdvisor.addWhereClause("expense_id = '"+expenseIds[i]+"'");

			result += updateColumns(queryAdvisor, usrExpense);
		}

		return result;
	}

	public int deleteByExpenseIds(String expenseIds[]) throws Exception {
		QueryAdvisor queryAdvisor;
		int result = 0;

		for (int i=0; i<expenseIds.length; i++) {
			queryAdvisor = new QueryAdvisor();
			queryAdvisor.addWhereClause("expense_id = '"+expenseIds[i]+"'");

			result += deleteWithSQLQuery(queryAdvisor, new UsrExpense());
		}

		return result;
	}

	public UsrExpense getExpenseById(String expenseId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();

		queryAdvisor.addWhereClause("expense_id = '"+expenseId+"'");
		return (UsrExpense)selectAllToDto(queryAdvisor, new UsrExpense());
	}

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

	public DataSet getExpenseDataSetByExpenseIdForUpdate(String expenseId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		String dateFormat = ConfigUtil.getProperty("format.date.java");

		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addWhereClause("exp.expense_id = '"+expenseId+"'");
		queryAdvisor.addOrderByClause("exp.expense_date desc, ext.sort_order");

		return selectAsDataSet(queryAdvisor, "query.UsrExpense.getExpenseDataSetByExpenseIdForUpdate");
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