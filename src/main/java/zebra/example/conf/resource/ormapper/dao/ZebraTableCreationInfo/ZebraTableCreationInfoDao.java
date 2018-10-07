/**************************************************************************************************
 * Framework Generated DAO Source
 * - ZEBRA_TABLE_CREATION_INFO - Table info to be created
 *************************************************************************************************/
package zebra.example.conf.resource.ormapper.dao.ZebraTableCreationInfo;

import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface ZebraTableCreationInfoDao extends IDao {
	/**
	 * Get all columns as DataSet with like-condition by table name
	 * @param queryAdvisor
	 * @return
	 * @throws Exception
	 */
	public DataSet getAllLikeTableNameAsDataSet(QueryAdvisor queryAdvisor) throws Exception;
}