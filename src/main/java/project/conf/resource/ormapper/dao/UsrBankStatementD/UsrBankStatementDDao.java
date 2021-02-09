/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_BANK_STATEMENT_D - Bank statement details
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrBankStatementD;

import project.conf.resource.ormapper.dto.oracle.UsrBankStatement;
import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface UsrBankStatementDDao extends IDao {
	public int insert(UsrBankStatement usrBankStatement, DataSet bankFileData) throws Exception;
	public int deleteByBankStatementId(String bankStatementId) throws Exception;

	public DataSet getDataSetByBankStatementId(String bankStatementId) throws Exception;
	public DataSet getDataSetByFileDataForDupCheck(QueryAdvisor queryAdvisor) throws Exception;
}