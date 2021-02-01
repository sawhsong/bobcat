/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_BANK_ACCNT - Bank account info by user
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrBankAccnt;

import zebra.base.IDao;
import zebra.data.DataSet;

public interface UsrBankAccntDao extends IDao {
	public int insert(DataSet bankAccntsDataSetToSave, String loggedinUserId) throws Exception;
	public int update(DataSet bankAccntsDataSetToSave, String loggedinUserId) throws Exception;
	public int deleteByUserId(String userId) throws Exception;

	public DataSet getDataSetByUserId(String userId) throws Exception;
}