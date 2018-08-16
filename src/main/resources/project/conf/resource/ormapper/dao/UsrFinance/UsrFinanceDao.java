/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_FINANCE - Financial Loan Related Data Entry (Repayment / Borrowing / Lending)
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrFinance;

import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface UsrFinanceDao extends IDao {
	public DataSet getRepaymentSummaryDataSet(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getBorrowingSummaryDataSet(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getLendingSummaryDataSet(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getRepaymentDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getBorrowingDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getLendingDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
}