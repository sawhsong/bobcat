/**************************************************************************************************
 * Framework Generated DAO Source
 * - SYS_ASSET_TYPE - Asset Entry Type
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysAssetType;

import project.conf.resource.ormapper.dto.oracle.SysAssetType;
import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface SysAssetTypeDao extends IDao {
	public int insert(SysAssetType sysAssetType) throws Exception;
	public int updateWithKey(SysAssetType sysAssetType, String assetTypeId, String assetTypeCode) throws Exception;
	public int deleteByOrgCategory(String orgCategory) throws Exception;
	public DataSet getAssetTypeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getAssetTypeDataSetByOrgCategory(String orgCategory) throws Exception;
	public SysAssetType getAssetTypeByKeys(String assetTypeId, String assetTypeCode) throws Exception;
	public DataSet getMaxSortOrderDataSetByOrgCategory(String orgCategory) throws Exception;
}