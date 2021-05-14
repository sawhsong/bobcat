/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_CASH_EXPENSE - Cash expenses
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrCashExpense;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dto.oracle.UsrCashExpense;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class UsrCashExpenseHDaoImpl extends BaseHDao implements UsrCashExpenseDao {
	public int insert(UsrCashExpense usrCashExpens) throws Exception {
		return insertWithSQLQuery(usrCashExpens);
	}

	public int update(String cashExpenseId, UsrCashExpense usrCashExpens) throws Exception {
		int result = 0;
		QueryAdvisor queryAdvisor = new QueryAdvisor();

		queryAdvisor.addWhereClause("cash_expense_id = '"+cashExpenseId+"'");

		result += updateColumns(queryAdvisor, usrCashExpens);

		return result;
	}

	public int delete(String cashExpenseIds[]) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		int result = 0;
		String idsForDel = "";

		if (!(cashExpenseIds == null || cashExpenseIds.length == 0)) {
			for (String id : cashExpenseIds) {
				idsForDel += CommonUtil.isBlank(idsForDel) ? "'"+id+"'" : ",'"+id+"'";
			}
			queryAdvisor.addWhereClause("cash_expense_id in ("+idsForDel+")");
			result = deleteWithSQLQuery(queryAdvisor, new UsrCashExpense());
		}
		return result;
	}

	public int delete(String cashExpenseId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("cash_expense_id = '"+cashExpenseId+"'");
		return deleteWithSQLQuery(queryAdvisor, new UsrCashExpense());
	}

	public DataSet getDataSetBySearchCriteria(QueryAdvisor queryAdvisor) throws Exception {
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String userId = (String)queryAdvisor.getObject("userId");
		String financialYear = (String)queryAdvisor.getObject("financialYear");
		String quarterName = (String)queryAdvisor.getObject("quarterName");
		String fromDate = (String)queryAdvisor.getObject("fromDate");
		String toDate = (String)queryAdvisor.getObject("toDate");

		queryAdvisor.addVariable("userId", userId);
		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addAutoFillCriteria(financialYear, "period_year = '"+financialYear+"'");
		queryAdvisor.addAutoFillCriteria(quarterName, "quarter_name = '"+quarterName+"'");

		if (CommonUtil.isNotBlank(fromDate)) {
			queryAdvisor.addWhereClause("trunc(uce.proc_date) >= trunc(to_date('"+fromDate+"', '"+dateFormat+"'))");
		}

		if (CommonUtil.isNotBlank(toDate)) {
			queryAdvisor.addWhereClause("trunc(uce.proc_date) <= trunc(to_date('"+toDate+"', '"+dateFormat+"'))");
		}

		queryAdvisor.addOrderByClause("uce.proc_date desc, uce.main_category, nvl(uce.update_date, uce.insert_date) desc");

		return selectAsDataSet(queryAdvisor, "query.UsrCashExpense.getDataSetBySearchCriteria");
	}

	public DataSet getDataSetByCashExpenseId(String cashExpenseId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();

		queryAdvisor.addWhereClause("cash_expense_id = '"+cashExpenseId+"'");

		return selectAllAsDataSet(queryAdvisor, new UsrCashExpense());
	}
}