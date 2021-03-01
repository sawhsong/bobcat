/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_BANK_ACCNT - Bank account info by user
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrBankAccnt;

import project.conf.resource.ormapper.dto.oracle.UsrBankAccnt;
import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface UsrBankAccntDao extends IDao {
	public int insertOrUpdate(DataSet bankAccntsDataSetToSave, String loggedinUserId) throws Exception;
	public int insert(UsrBankAccnt usrBankAccnt) throws Exception;
	public int update(String bankAccntId, UsrBankAccnt usrBankAccnt) throws Exception;
	public int delete(String bankAccntIds[]) throws Exception;
	public int delete(String bankAccntId) throws Exception;
	public int deleteByUserId(String userId) throws Exception;

	public DataSet getDataSetBySearchCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getDataSetByBankAccntId(String bankAccntId) throws Exception;
	public DataSet getDataSetByUserId(String userId) throws Exception;
	public DataSet getDataSetForSearchCriteriaByUserId(String userId) throws Exception;
	public DataSet getBankStatementAllocationStatusForDashboard(QueryAdvisor queryAdvisor) throws Exception;
}