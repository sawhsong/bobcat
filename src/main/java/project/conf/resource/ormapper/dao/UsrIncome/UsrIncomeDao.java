/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_INCOME - Income Entry (Sales Income and Other Income for Org Category A, Other Income for Org Category B/C/D)
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrIncome;

import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface UsrIncomeDao extends IDao {
	public DataSet getSalesIncomeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getOtherIncomeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getIncomeSummaryDataSet(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getIncomePerformanceDataSet(String orgCategory, String orgId, String financialYear, String quarterName) throws Exception;
}