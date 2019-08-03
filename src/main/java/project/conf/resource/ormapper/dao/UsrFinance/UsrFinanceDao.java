/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_FINANCE - Financial Loan Related Data Entry (Repayment / Borrowing / Lending)
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrFinance;

import project.conf.resource.ormapper.dto.oracle.UsrFinance;
import zebra.base.Dto;
import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface UsrFinanceDao extends IDao {
	public int insert(Dto dto) throws Exception;
	public int update(String financeId, Dto dto) throws Exception;
	public int exeCompleteByFinanceIds(String financeIds[]) throws Exception;
	public int deleteByFinanceIds(String financeIds[]) throws Exception;
	public DataSet getRepaymentSummaryDataSet(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getBorrowingSummaryDataSet(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getLendingSummaryDataSet(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getRepaymentDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getBorrowingDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getLendingDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getFinanceDataSetByFinanceIdForUpdate(String financeId) throws Exception;
	public UsrFinance getFinanceById(String financeId) throws Exception;
}