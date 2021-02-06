/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_BANK_STATEMENT - Bank statement master info which is uploaded by user
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrBankStatement;

import project.conf.resource.ormapper.dto.oracle.UsrBankStatement;
import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface UsrBankStatementDao extends IDao {
	public int insert(UsrBankStatement usrBankStatement, DataSet bankFileData) throws Exception;
	public int delete(String bankStatementIds[]) throws Exception;
	public int delete(String bankStatementId) throws Exception;

	public DataSet getDataSetBySearchCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public UsrBankStatement getByBankStatementId(String bankStatementId) throws Exception;
	public DataSet getDataSetByBankStatementId(String bankStatementId) throws Exception;
}