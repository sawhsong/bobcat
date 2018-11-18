/**************************************************************************************************
 * Framework Generated DAO Source
 * - SYS_ASSET_TYPE - Asset Entry Type
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysAssetType;

import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface SysAssetTypeDao extends IDao {
	public DataSet getAssetTypeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getAssetTypeDataSetByOrgCategory(String orgCategory) throws Exception;
}