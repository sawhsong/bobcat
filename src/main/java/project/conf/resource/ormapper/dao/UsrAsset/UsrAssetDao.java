/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_ASSET - Asset Entry (Asset data entry for all organisation category)
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrAsset;

import project.conf.resource.ormapper.dto.oracle.UsrAsset;
import zebra.base.Dto;
import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface UsrAssetDao extends IDao {
	public int insert(Dto dto, DataSet fileDataSet) throws Exception;
	public int update(String assetId, Dto dto, DataSet fileDataSet) throws Exception;
	public DataSet getAssetSummaryDataSet(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getAssetDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getAssetDataSetByAssetIdForUpdate(String assetId) throws Exception;
	public UsrAsset getAssetById(String assetId) throws Exception;
}