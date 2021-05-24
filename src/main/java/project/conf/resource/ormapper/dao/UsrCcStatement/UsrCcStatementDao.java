/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_CC_STATEMENT - Credit card statement master info which is uploaded by user
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrCcStatement;

import project.conf.resource.ormapper.dto.oracle.UsrCcStatement;
import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface UsrCcStatementDao extends IDao {
	public int insert(UsrCcStatement usrCcStatement, DataSet ccFileData) throws Exception;
	public int delete(String ccStatementIds[]) throws Exception;
	public int delete(String ccStatementId) throws Exception;

	public DataSet getDataSetBySearchCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public UsrCcStatement getByCcStatementId(String ccStatementId) throws Exception;
	public DataSet getDataSetByCcStatementId(String ccStatementId) throws Exception;
}