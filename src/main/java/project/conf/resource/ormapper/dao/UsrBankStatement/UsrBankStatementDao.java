/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_BANK_STATEMENT - Bank statement master info which is uploaded by user
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrBankStatement;

import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface UsrBankStatementDao extends IDao {
	public DataSet getDataSetBySearchCriteria(QueryAdvisor queryAdvisor) throws Exception;
}