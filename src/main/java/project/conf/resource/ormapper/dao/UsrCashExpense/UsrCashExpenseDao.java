/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_CASH_EXPENSE - Cash expenses
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrCashExpense;

import project.conf.resource.ormapper.dto.oracle.UsrCashExpense;
import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface UsrCashExpenseDao extends IDao {
	public int insert(UsrCashExpense usrCashExpens) throws Exception;
	public int update(String cashExpenseId, UsrCashExpense usrCashExpens) throws Exception;
	public int delete(String cashExpenseId) throws Exception;
	public int delete(String cashExpenseIds[]) throws Exception;

	public DataSet getDataSetBySearchCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getDataSetByCashExpenseId(String cashExpenseId) throws Exception;
}