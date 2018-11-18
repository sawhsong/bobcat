/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_ASSET - Asset Entry (Asset data entry for all organisation category)
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrAsset;

import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface UsrAssetDao extends IDao {
	public DataSet getAssetSummaryDataSet(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getAssetDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
}