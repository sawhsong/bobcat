/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_EXPENSE - Expense Entry (Expenditure data entry for all organisation category)
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrExpense;

import project.conf.resource.ormapper.dto.oracle.UsrExpense;
import zebra.base.Dto;
import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface UsrExpenseDao extends IDao {
	public int insert(Dto dto) throws Exception;
	public int update(String expenseId, Dto dto) throws Exception;
	public int exeCompleteByExpenseIds(String expenseIds[]) throws Exception;
	public int deleteByExpenseIds(String expenseIds[]) throws Exception;
	public UsrExpense getExpenseById(String expenseId) throws Exception;
	public DataSet getExpenseSummaryDataSet(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getExpenseDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getExpenseDataSetByExpenseIdForUpdate(String expenseId) throws Exception;
	public DataSet getExpensePerformanceDataSet(String orgCategory, String orgId, String financialYear, String quarterName) throws Exception;
}