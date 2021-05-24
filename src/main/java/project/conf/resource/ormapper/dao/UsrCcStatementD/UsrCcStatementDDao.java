/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_CC_STATEMENT_D - Credit card statement details
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrCcStatementD;

import project.conf.resource.ormapper.dto.oracle.UsrCcStatement;
import zebra.base.IDao;
import zebra.data.DataSet;

public interface UsrCcStatementDDao extends IDao {
	public int insert(UsrCcStatement usrCcStatement, DataSet ccFileData) throws Exception;
	public int deleteByCcStatementId(String ccStatementId) throws Exception;

	public DataSet getDataSetByCcStatementId(String ccStatementId) throws Exception;
}