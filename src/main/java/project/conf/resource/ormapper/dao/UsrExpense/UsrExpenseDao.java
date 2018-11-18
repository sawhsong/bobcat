/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_EXPENSE - Expense Entry (Expenditure data entry for all organisation category)
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrExpense;

import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface UsrExpenseDao extends IDao {
	public DataSet getExpenseSummaryDataSet(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getExpenseDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
}